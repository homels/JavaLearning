package cn.chd.hombernateFactory;

import java.util.ArrayList;
import java.util.List;

import cn.chd.hombernate.sql.HomConnection;
import test.HomUser;

/** 
* @author : homels
* @date ����ʱ�䣺2018��6��26�� ����8:43:43
* @ClassName: HomSession
* @Description: session�����װ��HomConnection��ʵ�ʲ�����HomConnection���� 
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
		System.out.println("�������");
		conn.close();
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List query(String sql,Class clazz,String dbName) throws Exception{
		sql = "SELECT * FROM USER";
		//��Ų�ѯ���
		List<HomUser> result = new ArrayList<HomUser>();
		//�Զ��������࣬�����װ��Connection
		HomConnection conn = new HomConnection();
	//	System.out.println(sql);
//		conn.connect();
		//�ʹ����dbName���ݿ⽨������
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
	
	//���ɾ���ڶ�Ӧ��HomConnection���滹û�����ӿڣ�ֻ���ȱ�ʾһ��
	public void delete(int id,String dbName){
		
	}
	public void update(String sql,String daName){
		
	}
}
