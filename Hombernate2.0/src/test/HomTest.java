package test;

import java.util.ArrayList;
import java.util.List;

import cn.chd.hombernateFactory.HomSession;
import cn.chd.hombernateFactory.HombernateFactory;

/** 
* @author : homels
* @date ����ʱ�䣺2018��6��28�� ����9:37:45
* @ClassName: HomTest
* @Description: TODO 
* @version 
*/
public class HomTest {

	public static void main(String[] args) {
		//HomConnection������
		HombernateFactory hbf = HombernateFactory.getInstance();
		//ͨ�������ഴ��sessionʵ��
		HomSession session = hbf.getNewSession();
		//pojo��
		HomUser user = new HomUser();
		/*
		 * ���Բ�������  ,������ܴ����ƣ�id����������
		*/
//		user.setAge(1);
//		user.setName("����");
//		session.save(user);
		
		/*
		 * ���Բ�ѯ����    �ⲿ�ֲ�����ͨ��
		*/
		//���ڽ��ܲ�ѯ���
		List<HomUser> list = new ArrayList<HomUser>();
		//��ѯ��䣬�����÷ŵ����棬�´θ�
		//String sql = "SELECT * FROM USER";
		try {
			//ִ�в�ѯ��ͨ��session����query����������SQL��䡢��Ӧ��pojo����Ϣ�����������ݿ�
			list = session.query("", HomUser.class,"default");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("------------------------------");
		//�����ѯ���
		for(HomUser u:list){
			System.out.println(u.getName()+u.getAge());
		}
	}

}
