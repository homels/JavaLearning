package cn.chd.hombernate.connectionpool;

import java.sql.Connection;
import java.sql.SQLException;

/** 
* @author : homels
* @date ����ʱ�䣺2018��6��26�� ����9:04:07
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
