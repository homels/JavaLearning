
package cn.chd.config;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 * ����ʱ�䣺2018��12��13�� ����3:55:24
 * 
 * ��Ŀ���ƣ�Daodemo
 * 
 * @author homrls
 * @version 1.0
 * @since JDK 1.8.0_21 �ļ����ƣ�DaoMethod.java ��˵���� �ṩCURD����
 */

public class DaoMethod {
	public DaoConfig config;

	public DaoMethod(String s) {
		config = new DaoConfig(s);
	}

	/**
	 * void TODO �����������,objΪҪ����Ķ���clazzӦΪ Object.class
	 */
	public void save(Object obj, Class clazz) {
		Session session = config.getSession();
		if (session != null) {
			Transaction transaction = session.getTransaction();
			// ��������
			transaction.begin();
			try {
				obj = (clazz.cast(obj));
				session.save(obj);
				// �ύ����
				transaction.commit();
			} catch (Exception e) {
				// ��������ʱ�ع�
				transaction.rollback();
			} finally {
				session.close();
			}
		} else {
			System.err.println("�������ʱ�Ŀ�ָ��(NullPoint when Insert)" + this.getClass().getName() + "<"
					+ Thread.currentThread().getStackTrace()[1].getMethodName() + "> at line 31)");
		}
	}

	/**
	 * List<Object> TODO ������ѯ����
	 */
	public List select(Class clazz) {
		Session session = config.getSession();
		List list = null;
		if (session != null) {
			Transaction transaction = session.getTransaction();
			// ��������
			transaction.begin();
			try {
				Criteria crtiteria = session.createCriteria(clazz);
				// list -�� select * from user;
				list = crtiteria.list();
				// �ύ����
				transaction.commit();
			} catch (Exception e) {
				// ��������ʱ�ع�
				transaction.rollback();
			} finally {
				session.close();
			}
		} else {
			System.err.println("��ѯ����ʱ�Ŀ�ָ��(NullPoint when Insert)" + this.getClass().getName() + "<"
					+ Thread.currentThread().getStackTrace()[1].getMethodName() + "> at line 57)");
		}
		return list;
	}

	/**
	 * void TODO ����ɾ��ָ����¼
	 */
	public void delete(Object obj, Class clazz) {
		Session session = config.getSession();
		if (session != null) {
			Transaction transaction = session.getTransaction();
			// ��������
			transaction.begin();
			try {
				obj = (clazz.cast(obj));
				session.delete(obj);
				// �ύ����
				transaction.commit();
			} catch (Exception e) {
				// ��������ʱ�ع�
				transaction.rollback();
			} finally {
				session.close();
			}
		} else {
			System.err.println("ɾ������ʱ�Ŀ�ָ��(NullPoint when Insert)" + this.getClass().getName() + "<"
					+ Thread.currentThread().getStackTrace()[1].getMethodName() + "> at line 86)");
		}
	}

	/**
	 * void TODO �������²���
	 */
	public void update(Object obj, Class clazz) {
		Session session = config.getSession();
		if (session != null) {
			Transaction transaction = session.getTransaction();
			// ��������
			transaction.begin();
			try {
				obj = (clazz.cast(obj));
				session.update(obj);
				// �ύ����
				transaction.commit();
			} catch (Exception e) {
				// ��������ʱ�ع�
				transaction.rollback();
			} finally {
				session.close();
			}
		} else {
			System.err.println("���²���ʱ�Ŀ�ָ��(NullPoint when Insert)" + this.getClass().getName() + "<"
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
			System.err.println("��ѯ����ʱ�Ŀ�ָ��(NullPoint when Insert)" + this.getClass().getName() + "<"
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
