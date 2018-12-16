package cn.chd.hombernate.sql;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cn.chd.hombernate.connectionpool.ConnectionManager;
import cn.chd.hombernate.pojo.HomMapping;
import cn.chd.hombernate.pojo.Hompojo;


/** 
* @author : homels
* @date 创建时间：2018年6月28日 下午4:20:08
* @ClassName: HomConnection
* @Description: HomConnection里有连接池管理类 
* @version 
*/
public class HomConnection {
	private ConnectionManager cm = null;
	private Connection conn;
	
	
	public HomConnection(){
		
	}
	public HomConnection(Connection conn){
		this.conn = conn;
	}
	
	//用连接池连接数据库
	public void connect(String dbName){
		//连接类，使用工厂模式定义
		cm = ConnectionManager.getInstance();
		conn = cm.getConnection(dbName);
		
	}
	//链接数据库,这个链接要改成连接池的链接
	public void connect(String ip,int port,String catalog,
			String username,String password) throws SQLException{
		String urlfmt = "jdbc:mysql://%s:%d/%s?useUnicode=true&characterEncoding=UTF-8";
		String connectionUrl = String.format(urlfmt, ip,port,catalog);
		conn = DriverManager.getConnection(connectionUrl,username,password);
	}
	
	public void connect (String connectionUrl,
			String username,
			String password) throws Exception
	{
		conn = DriverManager.getConnection(connectionUrl, username, password);		
	}
	
	public void close(){
		try {
			conn.close();
			cm.closeConnection("default", conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void save(Object pojo){
		@SuppressWarnings("rawtypes")
		Class clazz = pojo.getClass();
		Hompojo hp = HomMapping.i.findClass(clazz.getName());
		System.out.println(hp.properties.size());
		
	}
	
	@SuppressWarnings("rawtypes")
	public List executeQuery(String sql,Class clazz)throws Exception {
		//下两句执行查询
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		//得到结果集的metaData
		ResultSetMetaData rsmd = rs.getMetaData();
		//查询结果的列数
		int numColumns = rsmd.getColumnCount();
		//每列的类型（用int表示）
		int[] types = new int[numColumns];
		//每列的列名
		String[] labels = new String[numColumns];
		for(int i = 0;i<numColumns;i++){
			int columnIndex = i + 1;
			//依次存入各个值
			types[i] = rsmd.getColumnType(columnIndex);
			labels[i] = rsmd.getColumnLabel(columnIndex);
			//System.out.println(labels[i]);
		}
		//找到要查询的表对应的pojo类，并取出各种信息
		Hompojo hp = HomMapping.i.findClass(clazz.getName());
		//取出这个类的get、set方法
		Method[] setters = hp.findSetters(labels);
		System.out.println(setters[1]);
		//System.out.println(setters[0]);
	//	Method[] getters = hp.findGetters(labels);
		
//		System.out.println(hp.name);
//		System.out.println(hp.table);
//		Hompojo.Property j = new Hompojo.Property();
//		j = hp.properties.get(0);
//		System.out.println(j.name);
		
		//存放pojo对象
		List<Object> rows = new ArrayList<Object>();
		while(rs.next()){
			Object pojo = clazz.newInstance(); 
			//System.out.println(pojo.getClass());
			rows.add(pojo);
			for(int i = 0;i<numColumns;i++){
				int columnIndex = i+1;
				String columnValue = rs.getString(columnIndex);
				//System.out.println(columnValue);
				try{
					//System.out.println(pojo.getClass()+" "+setters[i]+" "+types[i]+" "+columnValue);
					HomReflect.map(pojo, setters[i], types[i], columnValue);
					//System.out.println(pojo);
				} catch(Exception e){
					
				}
			}
		}
		return rows;
	}
}
