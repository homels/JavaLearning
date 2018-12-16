package cn.chd.hombernate.connectionpool;

import java.sql.Connection;
import java.sql.SQLException;

/** 
* @author : homels
* @date 创建时间：2018年6月26日 下午9:04:07
* @ClassName: IConnectionPool
* @Description: TODO 
* @version 
*/
public interface IConnectionPool {
	public Connection getConnection();
	
	public Connection getCurrentConnection();
	
	public void releaseConn(Connection conn)throws SQLException;
	
	public void destroy();
	
	public boolean isActive();
	
	public void checkPool();
	
	public int getActiveNum();
	
	public int getFree();
}
