	
package cn.chd.bank;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import pojo.Bill;
import pojo.ICCard;
import pojo.User;

/**
 * ����ʱ�䣺2018��12��15�� ����3:37:43
 * 
 * ��Ŀ���ƣ�Daodemo
 * 
 * @author homrls
 * @version 1.0
 * @since JDK 1.8.0_21 �ļ����ƣ�BankMethod.java ��˵����
 */

public class BankMethod {

	public DaoConfig config;
	public BankMethod() {
		
	}
	public BankMethod(String s) {
		config = new DaoConfig(s);
	}

	public DaoConfig getConfig() {
		return config;
	}
	public void setConfig(DaoConfig config) {
		this.config = config;
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
				// e.printStackTrace();
				// ��������ʱ�ع�
				transaction.rollback();
			} finally {
				session.close();
			}
		} else {
			System.err.println("��ѯ����ʱ�Ŀ�ָ��(NullPoint when Insert)" + this.getClass().getName() + "<"
					+ Thread.currentThread().getStackTrace()[1].getMethodName() + "> at line 73)");
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
					+ Thread.currentThread().getStackTrace()[1].getMethodName() + "> at line 103)");
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
					+ Thread.currentThread().getStackTrace()[1].getMethodName() + "> at line 129)");
		}
	}

	/**
	 * List TODO select * from clazz where fieldName between low and high
	 */
	public List select(Class clazz, String fieldName, int low, int high) {
		Session session = config.getSession();
		List list = null;
		if (session != null) {
			Transaction transaction = session.beginTransaction();
			try {
				transaction.begin();
				Criteria crtiteria = session.createCriteria(clazz);
				crtiteria.add(Restrictions.between(fieldName, low, high));
				list = crtiteria.list();
				transaction.commit();
			} catch (Exception e) {
				transaction.rollback();
			} finally {
				session.close();
			}
		} else {
			System.err.println("��ѯ����ʱ�Ŀ�ָ��(NullPoint when Insert)" + this.getClass().getName() + "<"
					+ Thread.currentThread().getStackTrace()[1].getMethodName() + "> at line 155");
		}
		return list;
	}

	/**
	 * boolean TODO ��������½���û��Ƿ�Ϸ�
	 */
	public boolean checkUserIslegal(String name, String passwd) {
		List<User> userList = select(User.class);
		for (User user : userList) {
			if (user.getUserName().equals(name)) {
				if (user.getPassWord().equals(passwd)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * boolean TODO ע���û�
	 */
	public boolean registerUser(String name, String passwd1, String passwd2) {
		if (!passwd1.equals(passwd2)) {
			System.out.println("�������벻һ��");
			// �������벻һ��
			return false;
		}
		User user = new User();
		user.setUserName(name);
		user.setPassWord(passwd1);
		save(user, User.class);
		return true;
	}

	/**
	 * boolean TODO �û������п�
	 */
	public boolean addICCard(int userId, String ICNumber, String ICPassword) {
		Session session = config.getSession();
		if (session != null) {
			Transaction transaction = session.beginTransaction();
			try {
				transaction.begin();
				List<ICCard> list = select(ICCard.class);
				System.out.println("size:"+list.size());
				for (ICCard card : list) {
					System.out.println(card.getNumber()+" "+card.getPassword());
					if (card.getNumber().equals(ICNumber) && card.getPassword().equals(ICPassword)) {
						if (card.getUserId() == 0) {
							ICCard tempCard = new ICCard();
							tempCard.setId(card.getId());
							tempCard.setBalance(card.getBalance());
							tempCard.setNumber(ICNumber);
							tempCard.setUserId(userId);
							tempCard.setPassword(ICPassword);
							update(tempCard, ICCard.class);
						} else {
							//������п����Ѿ�������˰���
							System.out.println("���ѱ�����û���");
							return false;
						}
					} else {
						//û�в鵽������п���
						System.out.println("�鲻�����п���Ϣ");
					}
				}
				transaction.commit();
			} catch (Exception e) {
				transaction.rollback();
			} finally {
				session.close();
			}
		} else {
			//sessionΪ�յ����
			System.err.println("��ָ��(NullPoint when Insert)" + this.getClass().getName() + "<"
					+ Thread.currentThread().getStackTrace()[1].getMethodName() + "> at line 212");
			return false;
		}
		return true;
	}

	/**
	 * boolean TODO �û�ɾ�����п�
	 */
	public boolean deleteICCard(int userId,String number,String password) {
		Session session = config.getSession();
		int flag = 0;
		if(session != null) {
			Transaction transaction  = session.beginTransaction();
			try {
				transaction.begin();
				List<ICCard> list = select(ICCard.class);
				for(ICCard card : list) {
					if(card.getNumber().equals(number) && card.getPassword().equals(password)) {
						if(card.getUserId() == userId ) {
							ICCard tempCard = new ICCard();
							tempCard.setBalance(card.getBalance());
							tempCard.setId(card.getId());
							tempCard.setNumber(number);
							tempCard.setPassword(password);
							tempCard.setUserId(0);
							update(tempCard,ICCard.class);
							flag = 1;
						} else {
							//����������˻��󶨵�
							System.out.println("���Ǹ��û������п�");
							return false;
						}
					} else {
						//�������򲻴�������˺�
						System.out.println("�������/���˺Ų�����");
					}
				}
				//
				transaction.commit();
			} catch (Exception e) {
				transaction.rollback();
				return false;
			} finally {
				session.close();
			}
		} else {
			//sessionΪ��
			System.err.println("��ָ��(NullPoint when Insert)" + this.getClass().getName() + "<"
					+ Thread.currentThread().getStackTrace()[1].getMethodName() + "> at line 258");
			return false;
		}
		if(flag == 1)
			return true;
		else
			return false;
	}

	/**
	 * List<ICCard> TODO ��ʾ�û���IC�������п���Ϣ
	 */
	public List<ICCard> selectICCard(int userId) {
		List<ICCard> result = new ArrayList<ICCard>();
		Session session = config.getSession();
		if(session != null) {
			Transaction transaction  = session.beginTransaction();
			try {
				transaction.begin();
				//
				List<ICCard> list = select(ICCard.class);
				for(ICCard card : list) {
					if(card.getUserId() == userId) {
						result.add(card);
					}
				}
				transaction.commit();
			} catch (Exception e) {
				transaction.rollback();
				return null;
			} finally {
				session.close();
			}
		} else {
			//sessionΪ��
			System.err.println("��ָ��(NullPoint when Insert)" + this.getClass().getName() + "<"
					+ Thread.currentThread().getStackTrace()[1].getMethodName() + "> at line 307");
		}
		return result;
	}

	/**
	 * double TODO ��ѯĳ�û�ĳһ���ض�������� �� ��Ҫ���ж��û��Ƿ������ſ�
	 */
	public double queryBalance(int userId, String number) {
		double result = 0.0;
		Session session = config.getSession();
		if(session != null) {
			Transaction transaction  = session.beginTransaction();
			try {
				transaction.begin();
				//
				List<ICCard> list = select(ICCard.class);
				for(ICCard card : list) {
					if(card.getNumber().equals(number)) {
						if(card.getUserId() == userId) {
							result = card.getBalance();
						} else {
							//���ſ���������
							System.out.println("���û�û���������п�");
						}
					} else {
						//û�����ſ�
						System.out.println("��IC��������");
					}
				}
				transaction.commit();
			} catch (Exception e) {
				transaction.rollback();
				return -1;
			} finally {
				session.close();
			}
		} else {
			//sessionΪ��
			System.err.println("��ָ��(NullPoint when Insert)" + this.getClass().getName() + "<"
					+ Thread.currentThread().getStackTrace()[1].getMethodName() + "> at line 339");
		}
		return result;
	}

	/**
	 * boolean TODO ת�� userFrom:ת������п��ţ� userTo:ת�������п���
	 */
	public boolean transfer(String userFrom, String userTo, double balance) {
		Session session = config.getSession();
		if(session != null) {
			Transaction transaction  = session.beginTransaction();
			try {
				transaction.begin();
				List<ICCard> list = select(ICCard.class);
				ICCard from = null;
				ICCard to = null;
				//����˺��Ƿ���ڡ�����Ƿ����
				for(ICCard card : list) {
					if(card.getNumber().equals(userFrom)) {
						from = card;
					}
					if(card.getNumber().equals(userTo)) {
						to = card;
						if(card.getBalance() < balance) {
							//����
							System.out.println("����");
							transaction.rollback();
							return false;
						}
					}
				}
				if(from == null||to == null) {
					//�˺Ų�����
					System.out.println("�˺Ų�����");
					transaction.rollback();
					return false;
				}
				from.setBalance(from.getBalance()+balance);
				to.setBalance(to.getBalance()-balance);
				update(from, ICCard.class);
				update(to,ICCard.class);
				Bill bill = new Bill();
				//��ȡ��ǰʱ��
				Calendar cal = Calendar.getInstance();
				String year = cal.get(Calendar.YEAR) + "";
				String month = cal.get(Calendar.MONTH)+1 + "";
				String day = cal.get(Calendar.DATE) + "";
				String hour = cal.get(Calendar.HOUR_OF_DAY) + "";
				String minute = cal.get(Calendar.MINUTE) + "";
				String date = year+"-"+month+"-"+day+"  "+hour+":"+minute;
				//��������ת�˼�¼
				bill.setDate(date);
				bill.setIcId(from.getNumber());
				bill.setIc2Id(to.getNumber());
				//0 ��ת�룬1��ת��
				bill.setInOrOut(0);
				bill.setRemaining(from.getBalance());
				bill.setSum(balance);
				save(bill,Bill.class);
				bill.setIcId(to.getNumber());
				bill.setIc2Id(from.getNumber());
				bill.setInOrOut(1);
				bill.setSum(balance);
				bill.setRemaining(to.getBalance());
				save(bill,Bill.class);
				transaction.commit();
			} catch (Exception e) {
				transaction.rollback();
			} finally {
				session.close();
			}
		} else {
			//sessionΪ��
			System.err.println("��ָ��(NullPoint when Insert)" + this.getClass().getName() + "<"
					+ Thread.currentThread().getStackTrace()[1].getMethodName() + "> at line 378");
			return false;
		}
		return true;
	}

	/**
	 * List<Bill> TODO �鿴�˵�
	 */
	public List<Bill> showBill(int userId, String number) {
		List<Bill> result = new ArrayList<Bill>() ; 
		Session session = config.getSession();
		if(session != null) {
			Transaction transaction  = session.beginTransaction();
			try {
				transaction.begin();
				//
				List<Bill> list = select(Bill.class);
				for(Bill bill : list) {
					if(bill.getIcId().equals(number)) {
						result.add(bill);
					}
				}
				result = list;
				transaction.commit();
			} catch (Exception e) {
				transaction.rollback();
				return null;
			} finally {
				session.close();
			}
		} else {
			//sessionΪ��
			System.err.println("��ָ��(NullPoint when Insert)" + this.getClass().getName() + "<"
					+ Thread.currentThread().getStackTrace()[1].getMethodName() + "> at line 455");
			return null;
		}
		return result;
	}
}
