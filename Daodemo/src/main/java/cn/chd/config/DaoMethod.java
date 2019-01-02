
package cn.chd.config;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 * 创建时间：2018年12月13日 下午3:55:24
 * 
 * 项目名称：Daodemo
 * 
 * @author homrls
 * @version 1.0
 * @since JDK 1.8.0_21 文件名称：DaoMethod.java 类说明： 提供CURD方法
 */

public class DaoMethod {
	public DaoConfig config;

	public DaoMethod(String s) {
		config = new DaoConfig(s);
	}

	/**
	 * void TODO 基本插入操作,obj为要插入的对象，clazz应为 Object.class
	 */
	public void save(Object obj, Class clazz) {
		Session session = config.getSession();
		if (session != null) {
			Transaction transaction = session.getTransaction();
			// 开启事务
			transaction.begin();
			try {
				obj = (clazz.cast(obj));
				session.save(obj);
				// 提交事务
				transaction.commit();
			} catch (Exception e) {
				// 发生错误时回滚
				transaction.rollback();
			} finally {
				session.close();
			}
		} else {
			System.err.println("插入操作时的空指针(NullPoint when Insert)" + this.getClass().getName() + "<"
					+ Thread.currentThread().getStackTrace()[1].getMethodName() + "> at line 31)");
		}
	}

	/**
	 * List<Object> TODO 基本查询操作
	 */
	public List select(Class clazz) {
		Session session = config.getSession();
		List list = null;
		if (session != null) {
			Transaction transaction = session.getTransaction();
			// 开启事务
			transaction.begin();
			try {
				Criteria crtiteria = session.createCriteria(clazz);
				// list -》 select * from user;
				list = crtiteria.list();
				// 提交事务
				transaction.commit();
			} catch (Exception e) {
				// 发生错误时回滚
				transaction.rollback();
			} finally {
				session.close();
			}
		} else {
			System.err.println("查询操作时的空指针(NullPoint when Insert)" + this.getClass().getName() + "<"
					+ Thread.currentThread().getStackTrace()[1].getMethodName() + "> at line 57)");
		}
		return list;
	}

	/**
	 * void TODO 基本删除指定记录
	 */
	public void delete(Object obj, Class clazz) {
		Session session = config.getSession();
		if (session != null) {
			Transaction transaction = session.getTransaction();
			// 开启事务
			transaction.begin();
			try {
				obj = (clazz.cast(obj));
				session.delete(obj);
				// 提交事务
				transaction.commit();
			} catch (Exception e) {
				// 发生错误时回滚
				transaction.rollback();
			} finally {
				session.close();
			}
		} else {
			System.err.println("删除操作时的空指针(NullPoint when Insert)" + this.getClass().getName() + "<"
					+ Thread.currentThread().getStackTrace()[1].getMethodName() + "> at line 86)");
		}
	}

	/**
	 * void TODO 基本更新操作
	 */
	public void update(Object obj, Class clazz) {
		Session session = config.getSession();
		if (session != null) {
			Transaction transaction = session.getTransaction();
			// 开启事务
			transaction.begin();
			try {
				obj = (clazz.cast(obj));
				session.update(obj);
				// 提交事务
				transaction.commit();
			} catch (Exception e) {
				// 发生错误时回滚
				transaction.rollback();
			} finally {
				session.close();
			}
		} else {
			System.err.println("更新操作时的空指针(NullPoint when Insert)" + this.getClass().getName() + "<"
					+ Thread.currentThread().getStackTrace()[1].getMethodName() + "> at line 112)");
		}
	}
	
	/**
	 * List
	 * TODO  select * from clazz where fieldName between low and high
	 */
	public List select(Class clazz,String fieldName,int low,int high) {
		Session session = config.getSession();
		List list = null;
		if(session != null) {
			Transaction transaction = session.beginTransaction();
			try {
				transaction.begin();
				Criteria crtiteria = session.createCriteria(clazz);
				crtiteria.add(Restrictions.between(fieldName, low, high));
				list = crtiteria.list();
				transaction.commit();
			} catch(Exception e) {
				transaction.rollback();
			} finally {
				session.close();
			}
		} else {
			System.err.println("查询操作时的空指针(NullPoint when Insert)" + this.getClass().getName() + "<"
					+ Thread.currentThread().getStackTrace()[1].getMethodName() + "> at line 140");
		}
		return list;
	}

//	public List select (Class clazz,String filedName,) {
//		
//	}
	public static void main(String[] args) {
		// User user = new User();
		// user.setName("newUser2");
//		User user = new User();
//		user.setId(2);
//		user.setName("updateuser");
//		new DaoMethod("MYSQL").update(user, User.class);
		List<User> list = new DaoMethod("MYSQL").select(User.class,"id",1,2);
		System.out.println(list.size());
	}
}
