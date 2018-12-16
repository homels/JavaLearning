package test;

import java.util.ArrayList;
import java.util.List;

import cn.chd.hombernateFactory.HomSession;
import cn.chd.hombernateFactory.HombernateFactory;

/** 
* @author : homels
* @date 创建时间：2018年6月28日 下午9:37:45
* @ClassName: HomTest
* @Description: TODO 
* @version 
*/
public class HomTest {

	public static void main(String[] args) {
		//HomConnection工厂类
		HombernateFactory hbf = HombernateFactory.getInstance();
		//通过工厂类创建session实例
		HomSession session = hbf.getNewSession();
		//pojo类
		HomUser user = new HomUser();
		/*
		 * 测试插入数据  ,这个功能待完善，id自增还不行
		*/
//		user.setAge(1);
//		user.setName("测试");
//		session.save(user);
		
		/*
		 * 测试查询数据    这部分测试已通过
		*/
		//用于接受查询结果
		List<HomUser> list = new ArrayList<HomUser>();
		//查询语句，这个最好放到里面，下次改
		//String sql = "SELECT * FROM USER";
		try {
			//执行查询，通过session调用query方法，传入SQL语句、对应的pojo类信息、待操作数据库
			list = session.query("", HomUser.class,"default");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("------------------------------");
		//输出查询结果
		for(HomUser u:list){
			System.out.println(u.getName()+u.getAge());
		}
	}

}
