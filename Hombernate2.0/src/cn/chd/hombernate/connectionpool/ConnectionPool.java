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
* @date 创建时间：2018年6月27日 上午9:29:43
* @ClassName: ConnectionPool
* @Description: 数据库连接池 
* @version 
*/
public class ConnectionPool implements IConnectionPool{
	//存放连接池的属性
	private DBPropertyBean propertyBean = null;
	//连接池是否可用
	private Boolean isActive = false;
	//空闲连接
	private LinkedList<Connection> freeConnections = new LinkedList<Connection>();
	//活跃链接
	private LinkedList<Connection> activeConnections = new LinkedList<Connection>();
	
	//当前线程正在使用的链接
	private ThreadLocal<Connection> currentConnection = new ThreadLocal<Connection>();
	
	//单例的连接池
	private ConnectionPool(){
		
	}
	//传入存好各项属性的数据库连接池属性类，创建一个连接池
	public static ConnectionPool CreateConnectionPool(DBPropertyBean propertyBean){
		ConnectionPool connPool = new ConnectionPool();
		connPool.propertyBean = propertyBean;
		//创建连接池时，有一个初始连接数，按照初始连接数放入connection
		for(int i = 0;i<connPool.propertyBean.getInitConnection();i++){
			Connection conn = connPool.newConnection();
			//将新生成的connection放入空闲连接列表里
			connPool.freeConnections.add(conn);
		}
		//初始化完成后，将该连接池设为可用的
		connPool.isActive = true;
		return connPool;
	}
	//生成新connection
	private Connection newConnection() {
		Connection conn = null;
		//通过propertyBean的各项参数，新建连接
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
	
	//检测连接池里的一个connection是否有效
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
	 * 从连接池里得到一条链接
	 * @return Connection
	 */
	@Override
	public synchronized Connection getConnection(){
		//要返回的连接池
		Connection conn = null;
		//当前的连接数目没有到达最大连接数
		if(this.getActiveNum()<this.propertyBean.getMaxConnections()){
			//还有空闲链接，就拿一个出来
			if(this.getFree()>0){
				//从空闲列表中取一个出来
				conn = this.freeConnections.pollFirst();
				//由于数据库连接闲置久了会超时关闭，因此需要连接池采用机制保证每次请求的连接都是有效可用的。
				try{
					//检查这个连接还是否可用
					if(this.isValidConnection(conn)){
						//加入活跃连接列表，同时将当前connection设置为这个connection
						this.activeConnections.add(conn);
						currentConnection.set(conn);
					} else {
						conn = getConnection();
					}
				} catch(SQLException e){
					e.printStackTrace();
				}
			} else {
				//没有空闲连接
				conn = this.newConnection();
				this.activeConnections.add(conn);
			}
		} else {
			//已有连接超过最大连接数，所以等待一段时间
			long startTime = System.currentTimeMillis();
			try{
				//等待连接间隔的时间
				this.wait(this.propertyBean.getInitConnection());
			} catch (InterruptedException e){
				e.printStackTrace();
			}
			
			//大于timeout说明连不上
			if(this.propertyBean.getTimeout()!=0){	
				if(System.currentTimeMillis() - startTime>this.propertyBean.getTimeout()){
					return null;
				}
				//可重入锁，若小于超时时间，再试一次
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
	
	//在创建连接池成功后，调用这个方法，检查自身连接池连接数等问题
	@Override
	public void checkPool() {
		final String nodeName = this.propertyBean.getNodeName();
		//这个计时器没有实际用处（23333），用来一直向控制台输出状态 ,测试用，用于观察连接池状态是否正常
		ScheduledExecutorService ses = Executors.newScheduledThreadPool(2);
		ses.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				System.out.println(nodeName+"空闲链接数:"+getFree());
				System.out.println(nodeName+"活动链接数"+getActiveNum());
			}
		}, 3, 1, TimeUnit.SECONDS);
		//维持连接池最小链接数
		ses.scheduleAtFixedRate(new CheckFreepools(this), 1, 5, TimeUnit.SECONDS);
	}
	//监视连接池类
	class CheckFreepools extends TimerTask{
		private ConnectionPool conpool = null;
		public CheckFreepools(ConnectionPool cp){
			this.conpool = cp;
		}
		//由于connection长时间没有连接会失效，所以需要动态检查
		@Override
		public void run() {
			//只有当前连接池仍然可用时，才进行监视
			if(this.conpool!=null && this.conpool.isActive()){
				//当前连接池链接总数
				int poolstotalnum = conpool.getFree()+
						conpool.getActiveNum();
				//subnum大于0，说明当前连接池的连接数小于最小链接数
				int subnum  = conpool.propertyBean.getMinConnrctions()-
						poolstotalnum;
				if(subnum>0){
					//System.out.println(conpool.propertyBean.getNodeName()+
						//	"扫描并维持空闲池中的最小连接数，需补充" + subnum + "个连接");
					//补充至最小连接数
					for(int i = 0;i<subnum;i++){
						try{
							//加至空闲列表
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
