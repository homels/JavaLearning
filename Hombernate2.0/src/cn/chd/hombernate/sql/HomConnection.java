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
* @date ����ʱ�䣺2018��6��28�� ����4:20:08
* @ClassName: HomConnection
* @Description: HomConnection�������ӳع����� 
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
	
	//�����ӳ��������ݿ�
	public void connect(String dbName){
		//�����࣬ʹ�ù���ģʽ����
		cm = ConnectionManager.getInstance();
		conn = cm.getConnection(dbName);
		
	}
	//�������ݿ�,�������Ҫ�ĳ����ӳص�����
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
		//������ִ�в�ѯ
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		//�õ��������metaData
		ResultSetMetaData rsmd = rs.getMetaData();
		//��ѯ���������
		int numColumns = rsmd.getColumnCount();
		//ÿ�е����ͣ���int��ʾ��
		int[] types = new int[numColumns];
		//ÿ�е�����
		String[] labels = new String[numColumns];
		for(int i = 0;i<numColumns;i++){
			int columnIndex = i + 1;
			//���δ������ֵ
			types[i] = rsmd.getColumnType(columnIndex);
			labels[i] = rsmd.getColumnLabel(columnIndex);
			//System.out.println(labels[i]);
		}
		//�ҵ�Ҫ��ѯ�ı��Ӧ��pojo�࣬��ȡ��������Ϣ
		Hompojo hp = HomMapping.i.findClass(clazz.getName());
		//ȡ��������get��set����
		Method[] setters = hp.findSetters(labels);
		System.out.println(setters[1]);
		//System.out.println(setters[0]);
	//	Method[] getters = hp.findGetters(labels);
		
//		System.out.println(hp.name);
//		System.out.println(hp.table);
//		Hompojo.Property j = new Hompojo.Property();
//		j = hp.properties.get(0);
//		System.out.println(j.name);
		
		//���pojo����
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
