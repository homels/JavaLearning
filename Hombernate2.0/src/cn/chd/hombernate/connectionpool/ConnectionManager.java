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
* @date 创建时间：2018年6月26日 下午8:59:27
* @ClassName: ConnectionManager
* @Description: 管理各个连接池
* @version 
*/
public class ConnectionManager {
	//工厂模式
	private static ConnectionManager connectionManager = null;
	
	//用于存放各连接池的驱动字符串
	private Set<String> drivers = new HashSet<String>();
	
	//存放每个数据库对应的连接池
	private ConcurrentHashMap<String, IConnectionPool> pools = 
			new ConcurrentHashMap<String, IConnectionPool>();
	
	//在构造工厂时，就通过createPools构造所有的连接池
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
		//读出nodeName项，即确定包含几个数据库连接池
		String nodeNames = ConnectionPoolPropertiesManager.getPropertiy("nodename");
		//将各连接池名字用逗号分开，分别创建
		for(String nodeName:nodeNames.split(",")){
			DBPropertyBean dbPropertyBean = new DBPropertyBean();
			//设置nodeName
			dbPropertyBean.setNodeName(nodeName);
			
			//以下用于检查连接池的各项属性是否合法
			String url = ConnectionPoolPropertiesManager.getPropertiy(nodeName + ".url");
			//检查url
			if(url==null){
				System.err.println(nodeName+"节点的连接字符串为空，请检查配置文件");
				continue;
			}
			dbPropertyBean.setUrl(url);
			
			String driver = ConnectionPoolPropertiesManager.getPropertiy(nodeName + ".driver");
			//检查驱动
			if(driver==null){
				System.err.println("节点的driver驱动为空，请检查配置文件");
				continue;
			}
			dbPropertyBean.setDirverName(driver);
			
			String user = ConnectionPoolPropertiesManager.getPropertiy(nodeName+".user");
			if(user==null){
				System.err.println("用户名不正确");
				continue;
			}
			dbPropertyBean.setUserName(user);
			
			String password = ConnectionPoolPropertiesManager.getPropertiy(nodeName+".password");
			if(password==null){
				System.err.println("密码错了");
				continue;
			}
			dbPropertyBean.setPassword(password);
			
			String minconnections = ConnectionPoolPropertiesManager.getPropertiy(nodeName+".minconnections");
			if(minconnections==null){
				System.err.println("未设置最小链接数");
				continue;
			}
			dbPropertyBean.setMinConnrctions(Integer.parseInt(minconnections));
			
			String initconnections = ConnectionPoolPropertiesManager.getPropertiy(nodeName+".initconnections");
			if(initconnections==null){
				System.err.println("未设置初始链接数");
				continue;
			}
			dbPropertyBean.setInitConnection(Integer.parseInt(initconnections));
			
			String maxconnections = ConnectionPoolPropertiesManager.getPropertiy(nodeName+".maxconnections");
			if(maxconnections==null){
				System.err.println("未设置最大链接数");
				continue;
			}
			dbPropertyBean.setMaxConnections(Integer.parseInt(maxconnections));
			
			String conninterval = ConnectionPoolPropertiesManager.getPropertiy(nodeName+".conninterval");
			if(conninterval==null){
				System.err.println("未设置");
				continue;
			}
			dbPropertyBean.setInitConnection(Integer.parseInt(initconnections));
			
			String timeout = ConnectionPoolPropertiesManager.getPropertiy(nodeName+".timeout");
			if(timeout==null){
				System.err.println("未设置");
				continue;
			}
			dbPropertyBean.setTimeout(Integer.parseInt(timeout));
			
			//创建驱动，创建前先检查是否已经创建过这一驱动
			if(!drivers.contains(dbPropertyBean.getDirverName())){
                try {
                    Class.forName(dbPropertyBean.getDirverName());
                   // System.out.println("加载JDBC驱动"+dbPropertyBean.getDirverName()+"成功");
                    //加载成功以后放入set集合中
                    drivers.add(dbPropertyBean.getDirverName());
                } catch (ClassNotFoundException e) {
                    System.out.println("未找到JDBC驱动" + dbPropertyBean.getDirverName() + "，请引入相关包");
                    e.printStackTrace();
                }
			}
			//创建连接池，并调用连接池的方法检查自身
            IConnectionPool cp = ConnectionPool.CreateConnectionPool(dbPropertyBean);
            if (cp != null) {
            	//连接池和节点名（nodename） 键值对
                pools.put(nodeName, cp);
                //定时检查这个连接池
                cp.checkPool();
               // System.out.println("创建" + nodeName + "数据库连接池成功");
            } else {
            	System.out.println("创建" + nodeName + "数据库连接池失败");
            }
		}
	}

	/**
	 * 从指定连接池中获取可用连接,IConnectionPool是一个接口
	 * 
	 * @param poolName要获取连接的连接池名称
	 * @return 连接池中的一个可用连接或null
	 */
	public Connection getConnection(String poolName) {
		//pools里存放DB.property里声明的所有连接池，用get取出对应的连接池
		IConnectionPool pool = pools.get(poolName);
		//System.out.println(pool);
		//得到这个连接池的一个连接
		return pool.getConnection();
	}

	/**
	 * 回收指定连接池的连接
	 * 
	 * @param poolName连接池名称
	 * @param conn要回收的连接
	 */
	public void closeConnection(String poolName, Connection conn) throws SQLException {
		IConnectionPool pool = pools.get(poolName);
		if (pool != null) {
			try {
				//将链接释放
				pool.releaseConn(conn);
			} catch (SQLException e) {
				//log.error("回收" + poolName + "池中的连接失败。");
				throw new SQLException(e);
			}
		} else {
			System.out.println("找不到" + poolName + "连接池，无法回收");
		}
	}
	/**
	 * 关闭所有连接，撤销工厂
	 */
	public void destroy() {
		for (Map.Entry<String, IConnectionPool> poolEntry : pools.entrySet()) {
			IConnectionPool pool = poolEntry.getValue();
			pool.destroy();
		}
	}
}
