package cn.chd.hombernate.connectionpool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import cn.chd.hombernate.util.ConnectionPoolPropertiesManager;

/** 
* @author : homels
* @date ����ʱ�䣺2018��6��26�� ����8:59:27
* @ClassName: ConnectionManager
* @Description: ����������ӳ�
* @version 
*/
public class ConnectionManager {
	//����ģʽ
	private static ConnectionManager connectionManager = null;
	
	//���ڴ�Ÿ����ӳص������ַ���
	private Set<String> drivers = new HashSet<String>();
	
	//���ÿ�����ݿ��Ӧ�����ӳ�
	private ConcurrentHashMap<String, IConnectionPool> pools = 
			new ConcurrentHashMap<String, IConnectionPool>();
	
	//�ڹ��칤��ʱ����ͨ��createPools�������е����ӳ�
	private ConnectionManager(){
		createPools();
	}
	public synchronized static ConnectionManager getInstance(){
		if(connectionManager==null){
			connectionManager = new ConnectionManager();
		}
		return connectionManager;
	}
	private void createPools(){
		//����nodeName���ȷ�������������ݿ����ӳ�
		String nodeNames = ConnectionPoolPropertiesManager.getPropertiy("nodename");
		//�������ӳ������ö��ŷֿ����ֱ𴴽�
		for(String nodeName:nodeNames.split(",")){
			DBPropertyBean dbPropertyBean = new DBPropertyBean();
			//����nodeName
			dbPropertyBean.setNodeName(nodeName);
			
			//�������ڼ�����ӳصĸ��������Ƿ�Ϸ�
			String url = ConnectionPoolPropertiesManager.getPropertiy(nodeName + ".url");
			//���url
			if(url==null){
				System.err.println(nodeName+"�ڵ�������ַ���Ϊ�գ����������ļ�");
				continue;
			}
			dbPropertyBean.setUrl(url);
			
			String driver = ConnectionPoolPropertiesManager.getPropertiy(nodeName + ".driver");
			//�������
			if(driver==null){
				System.err.println("�ڵ��driver����Ϊ�գ����������ļ�");
				continue;
			}
			dbPropertyBean.setDirverName(driver);
			
			String user = ConnectionPoolPropertiesManager.getPropertiy(nodeName+".user");
			if(user==null){
				System.err.println("�û�������ȷ");
				continue;
			}
			dbPropertyBean.setUserName(user);
			
			String password = ConnectionPoolPropertiesManager.getPropertiy(nodeName+".password");
			if(password==null){
				System.err.println("�������");
				continue;
			}
			dbPropertyBean.setPassword(password);
			
			String minconnections = ConnectionPoolPropertiesManager.getPropertiy(nodeName+".minconnections");
			if(minconnections==null){
				System.err.println("δ������С������");
				continue;
			}
			dbPropertyBean.setMinConnrctions(Integer.parseInt(minconnections));
			
			String initconnections = ConnectionPoolPropertiesManager.getPropertiy(nodeName+".initconnections");
			if(initconnections==null){
				System.err.println("δ���ó�ʼ������");
				continue;
			}
			dbPropertyBean.setInitConnection(Integer.parseInt(initconnections));
			
			String maxconnections = ConnectionPoolPropertiesManager.getPropertiy(nodeName+".maxconnections");
			if(maxconnections==null){
				System.err.println("δ�������������");
				continue;
			}
			dbPropertyBean.setMaxConnections(Integer.parseInt(maxconnections));
			
			String conninterval = ConnectionPoolPropertiesManager.getPropertiy(nodeName+".conninterval");
			if(conninterval==null){
				System.err.println("δ����");
				continue;
			}
			dbPropertyBean.setInitConnection(Integer.parseInt(initconnections));
			
			String timeout = ConnectionPoolPropertiesManager.getPropertiy(nodeName+".timeout");
			if(timeout==null){
				System.err.println("δ����");
				continue;
			}
			dbPropertyBean.setTimeout(Integer.parseInt(timeout));
			
			//��������������ǰ�ȼ���Ƿ��Ѿ���������һ����
			if(!drivers.contains(dbPropertyBean.getDirverName())){
                try {
                    Class.forName(dbPropertyBean.getDirverName());
                   // System.out.println("����JDBC����"+dbPropertyBean.getDirverName()+"�ɹ�");
                    //���سɹ��Ժ����set������
                    drivers.add(dbPropertyBean.getDirverName());
                } catch (ClassNotFoundException e) {
                    System.out.println("δ�ҵ�JDBC����" + dbPropertyBean.getDirverName() + "����������ذ�");
                    e.printStackTrace();
                }
			}
			//�������ӳأ����������ӳصķ����������
            IConnectionPool cp = ConnectionPool.CreateConnectionPool(dbPropertyBean);
            if (cp != null) {
            	//���ӳغͽڵ�����nodename�� ��ֵ��
                pools.put(nodeName, cp);
                //��ʱ���������ӳ�
                cp.checkPool();
               // System.out.println("����" + nodeName + "���ݿ����ӳسɹ�");
            } else {
            	System.out.println("����" + nodeName + "���ݿ����ӳ�ʧ��");
            }
		}
	}

	/**
	 * ��ָ�����ӳ��л�ȡ��������,IConnectionPool��һ���ӿ�
	 * 
	 * @param poolNameҪ��ȡ���ӵ����ӳ�����
	 * @return ���ӳ��е�һ���������ӻ�null
	 */
	public Connection getConnection(String poolName) {
		//pools����DB.property���������������ӳأ���getȡ����Ӧ�����ӳ�
		IConnectionPool pool = pools.get(poolName);
		//System.out.println(pool);
		//�õ�������ӳص�һ������
		return pool.getConnection();
	}

	/**
	 * ����ָ�����ӳص�����
	 * 
	 * @param poolName���ӳ�����
	 * @param connҪ���յ�����
	 */
	public void closeConnection(String poolName, Connection conn) throws SQLException {
		IConnectionPool pool = pools.get(poolName);
		if (pool != null) {
			try {
				//�������ͷ�
				pool.releaseConn(conn);
			} catch (SQLException e) {
				//log.error("����" + poolName + "���е�����ʧ�ܡ�");
				throw new SQLException(e);
			}
		} else {
			System.out.println("�Ҳ���" + poolName + "���ӳأ��޷�����");
		}
	}
	/**
	 * �ر��������ӣ���������
	 */
	public void destroy() {
		for (Map.Entry<String, IConnectionPool> poolEntry : pools.entrySet()) {
			IConnectionPool pool = poolEntry.getValue();
			pool.destroy();
		}
	}
}
