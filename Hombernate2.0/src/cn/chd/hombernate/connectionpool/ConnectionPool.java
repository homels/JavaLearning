package cn.chd.hombernate.connectionpool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


/** 
* @author : homels
* @date ����ʱ�䣺2018��6��27�� ����9:29:43
* @ClassName: ConnectionPool
* @Description: ���ݿ����ӳ� 
* @version 
*/
public class ConnectionPool implements IConnectionPool{
	//������ӳص�����
	private DBPropertyBean propertyBean = null;
	//���ӳ��Ƿ����
	private Boolean isActive = false;
	//��������
	private LinkedList<Connection> freeConnections = new LinkedList<Connection>();
	//��Ծ����
	private LinkedList<Connection> activeConnections = new LinkedList<Connection>();
	
	//��ǰ�߳�����ʹ�õ�����
	private ThreadLocal<Connection> currentConnection = new ThreadLocal<Connection>();
	
	//���������ӳ�
	private ConnectionPool(){
		
	}
	//�����ø������Ե����ݿ����ӳ������࣬����һ�����ӳ�
	public static ConnectionPool CreateConnectionPool(DBPropertyBean propertyBean){
		ConnectionPool connPool = new ConnectionPool();
		connPool.propertyBean = propertyBean;
		//�������ӳ�ʱ����һ����ʼ�����������ճ�ʼ����������connection
		for(int i = 0;i<connPool.propertyBean.getInitConnection();i++){
			Connection conn = connPool.newConnection();
			//�������ɵ�connection������������б���
			connPool.freeConnections.add(conn);
		}
		//��ʼ����ɺ󣬽������ӳ���Ϊ���õ�
		connPool.isActive = true;
		return connPool;
	}
	//������connection
	private Connection newConnection() {
		Connection conn = null;
		//ͨ��propertyBean�ĸ���������½�����
		if(this.propertyBean != null){
			try {
				conn = DriverManager.getConnection(this.propertyBean.getUrl(), 
						this.propertyBean.getUserName(),
						this.propertyBean.getPassword());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return conn;
	}
	
	//������ӳ����һ��connection�Ƿ���Ч
	private Boolean isValidConnection(Connection conn) throws SQLException{
		if(conn==null || conn.isClosed()){
			return false;
		}
		return true;
	}
	
	public int getActiveNum(){
		return this.activeConnections.size();
	}
	public int getFree(){
		return this.freeConnections.size();
	}
	
	/**
	 * �����ӳ���õ�һ������
	 * @return Connection
	 */
	@Override
	public synchronized Connection getConnection(){
		//Ҫ���ص����ӳ�
		Connection conn = null;
		//��ǰ��������Ŀû�е������������
		if(this.getActiveNum()<this.propertyBean.getMaxConnections()){
			//���п������ӣ�����һ������
			if(this.getFree()>0){
				//�ӿ����б���ȡһ������
				conn = this.freeConnections.pollFirst();
				//�������ݿ��������þ��˻ᳬʱ�رգ������Ҫ���ӳز��û��Ʊ�֤ÿ����������Ӷ�����Ч���õġ�
				try{
					//���������ӻ��Ƿ����
					if(this.isValidConnection(conn)){
						//�����Ծ�����б�ͬʱ����ǰconnection����Ϊ���connection
						this.activeConnections.add(conn);
						currentConnection.set(conn);
					} else {
						conn = getConnection();
					}
				} catch(SQLException e){
					e.printStackTrace();
				}
			} else {
				//û�п�������
				conn = this.newConnection();
				this.activeConnections.add(conn);
			}
		} else {
			//�������ӳ�����������������Եȴ�һ��ʱ��
			long startTime = System.currentTimeMillis();
			try{
				//�ȴ����Ӽ����ʱ��
				this.wait(this.propertyBean.getInitConnection());
			} catch (InterruptedException e){
				e.printStackTrace();
			}
			
			//����timeout˵��������
			if(this.propertyBean.getTimeout()!=0){	
				if(System.currentTimeMillis() - startTime>this.propertyBean.getTimeout()){
					return null;
				}
				//������������С�ڳ�ʱʱ�䣬����һ��
				conn = this.getConnection();
			}
		}
		return conn;
	}
	@Override
	public Connection getCurrentConnection() {
		Connection conn = currentConnection.get();
		try{
			if(isValidConnection(conn)){
				conn = this.getConnection();
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return conn;
	}
	@Override
	public synchronized void releaseConn(Connection conn) throws SQLException {
		this.activeConnections.remove(conn);
		this.currentConnection.remove();
		try{
			if(isValidConnection(conn)){
				this.freeConnections.add(conn);
			}
			else{
				this.freeConnections.add(this.newConnection());
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		this.notifyAll();
	}
	@Override
	public synchronized void destroy() {
		for(Connection conn:this.freeConnections){
			try{
				if(isValidConnection(conn)){
					conn.close();
				}
			} catch(SQLException e){
				e.printStackTrace();
			}
		}
		for(Connection conn:this.activeConnections){
			try{
				if(isValidConnection(conn)){
					conn.close();
				}
			} catch(SQLException e){
				e.printStackTrace();
			}
		}
		this.isActive = false;
		this.freeConnections.clear();
		this.activeConnections.clear();
	}
	@Override
	public boolean isActive() {
		return this.isActive;
	}
	
	//�ڴ������ӳسɹ��󣬵����������������������ӳ�������������
	@Override
	public void checkPool() {
		final String nodeName = this.propertyBean.getNodeName();
		//�����ʱ��û��ʵ���ô���23333��������һֱ�����̨���״̬ ,�����ã����ڹ۲����ӳ�״̬�Ƿ�����
		ScheduledExecutorService ses = Executors.newScheduledThreadPool(2);
		ses.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				System.out.println(nodeName+"����������:"+getFree());
				System.out.println(nodeName+"�������"+getActiveNum());
			}
		}, 3, 1, TimeUnit.SECONDS);
		//ά�����ӳ���С������
		ses.scheduleAtFixedRate(new CheckFreepools(this), 1, 5, TimeUnit.SECONDS);
	}
	//�������ӳ���
	class CheckFreepools extends TimerTask{
		private ConnectionPool conpool = null;
		public CheckFreepools(ConnectionPool cp){
			this.conpool = cp;
		}
		//����connection��ʱ��û�����ӻ�ʧЧ��������Ҫ��̬���
		@Override
		public void run() {
			//ֻ�е�ǰ���ӳ���Ȼ����ʱ���Ž��м���
			if(this.conpool!=null && this.conpool.isActive()){
				//��ǰ���ӳ���������
				int poolstotalnum = conpool.getFree()+
						conpool.getActiveNum();
				//subnum����0��˵����ǰ���ӳص�������С����С������
				int subnum  = conpool.propertyBean.getMinConnrctions()-
						poolstotalnum;
				if(subnum>0){
					//System.out.println(conpool.propertyBean.getNodeName()+
						//	"ɨ�貢ά�ֿ��г��е���С���������貹��" + subnum + "������");
					//��������С������
					for(int i = 0;i<subnum;i++){
						try{
							//���������б�
							this.conpool.freeConnections.add(conpool.newConnection());
						} catch (Exception e){
							e.printStackTrace();
						}
					}
				}
			}
		}
	}
}
