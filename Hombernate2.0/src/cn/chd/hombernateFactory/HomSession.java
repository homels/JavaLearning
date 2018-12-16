package cn.chd.hombernateFactory;

import java.util.ArrayList;
import java.util.List;

import cn.chd.hombernate.sql.HomConnection;
import test.HomUser;

/** 
* @author : homels
* @date 创建时间：2018年6月26日 下午8:43:43
* @ClassName: HomSession
* @Description: session类里封装了HomConnection，实际操作由HomConnection进行 
* @version 
*/
public class HomSession {
	protected HomSession(){
		
	}
	static{
		
	}
	public void save(Object obj){
		HomConnection conn = new HomConnection();
		conn.connect("default");
		conn.save(obj);
		System.out.println("插入完成");
		conn.close();
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List query(String sql,Class clazz,String dbName) throws Exception{
		sql = "SELECT * FROM USER";
		//存放查询结果
		List<HomUser> result = new ArrayList<HomUser>();
		//自定义连接类，里面封装了Connection
		HomConnection conn = new HomConnection();
	//	System.out.println(sql);
//		conn.connect();
		//和传入的dbName数据库建立连接
		conn.connect(dbName);
		//conn.connect("127.0.0.1", 3306, "learning","root","26851420114");
		result = conn.executeQuery(sql, clazz);
		//System.out.println(result.size());
		conn.close();
//		for(HomUser user:result){
//			System.out.println(user.getName()+" "+user.getAge());
//		}
		return result;
	}
	
	//查和删，在对应的HomConnection里面还没有留接口，只是先表示一下
	public void delete(int id,String dbName){
		
	}
	public void update(String sql,String daName){
		
	}
}
