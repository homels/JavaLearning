	
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
 * 创建时间：2018年12月15日 下午3:37:43
 * 
 * 项目名称：Daodemo
 * 
 * @author homrls
 * @version 1.0
 * @since JDK 1.8.0_21 文件名称：BankMethod.java 类说明：
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
				// e.printStackTrace();
				// 发生错误时回滚
				transaction.rollback();
			} finally {
				session.close();
			}
		} else {
			System.err.println("查询操作时的空指针(NullPoint when Insert)" + this.getClass().getName() + "<"
					+ Thread.currentThread().getStackTrace()[1].getMethodName() + "> at line 73)");
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
					+ Thread.currentThread().getStackTrace()[1].getMethodName() + "> at line 103)");
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
			System.err.println("查询操作时的空指针(NullPoint when Insert)" + this.getClass().getName() + "<"
					+ Thread.currentThread().getStackTrace()[1].getMethodName() + "> at line 155");
		}
		return list;
	}

	/**
	 * boolean TODO 检查请求登陆的用户是否合法
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
	 * boolean TODO 注册用户
	 */
	public boolean registerUser(String name, String passwd1, String passwd2) {
		if (!passwd1.equals(passwd2)) {
			System.out.println("两次密码不一致");
			// 两次密码不一致
			return false;
		}
		User user = new User();
		user.setUserName(name);
		user.setPassWord(passwd1);
		save(user, User.class);
		return true;
	}

	/**
	 * boolean TODO 用户绑定银行卡
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
							//这个银行卡号已经被别的人绑定了
							System.out.println("卡已被别的用户绑定");
							return false;
						}
					} else {
						//没有查到这个银行卡号
						System.out.println("查不到银行卡信息");
					}
				}
				transaction.commit();
			} catch (Exception e) {
				transaction.rollback();
			} finally {
				session.close();
			}
		} else {
			//session为空的情况
			System.err.println("空指针(NullPoint when Insert)" + this.getClass().getName() + "<"
					+ Thread.currentThread().getStackTrace()[1].getMethodName() + "> at line 212");
			return false;
		}
		return true;
	}

	/**
	 * boolean TODO 用户删除银行卡
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
							//不是他这个账户绑定的
							System.out.println("不是该用户的银行卡");
							return false;
						}
					} else {
						//密码错误或不存在这个账号
						System.out.println("密码错误/该账号不存在");
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
			//session为空
			System.err.println("空指针(NullPoint when Insert)" + this.getClass().getName() + "<"
					+ Thread.currentThread().getStackTrace()[1].getMethodName() + "> at line 258");
			return false;
		}
		if(flag == 1)
			return true;
		else
			return false;
	}

	/**
	 * List<ICCard> TODO 显示用户的IC卡和银行卡信息
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
			//session为空
			System.err.println("空指针(NullPoint when Insert)" + this.getClass().getName() + "<"
					+ Thread.currentThread().getStackTrace()[1].getMethodName() + "> at line 307");
		}
		return result;
	}

	/**
	 * double TODO 查询某用户某一张特定卡的余额 （ 需要先判断用户是否有这张卡
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
							//这张卡不是他的
							System.out.println("该用户没有这张银行卡");
						}
					} else {
						//没有这张卡
						System.out.println("该IC卡不存在");
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
			//session为空
			System.err.println("空指针(NullPoint when Insert)" + this.getClass().getName() + "<"
					+ Thread.currentThread().getStackTrace()[1].getMethodName() + "> at line 339");
		}
		return result;
	}

	/**
	 * boolean TODO 转账 userFrom:转入的银行卡号， userTo:转出的银行卡号
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
				//检查账号是否存在、金额是否充足
				for(ICCard card : list) {
					if(card.getNumber().equals(userFrom)) {
						from = card;
					}
					if(card.getNumber().equals(userTo)) {
						to = card;
						if(card.getBalance() < balance) {
							//金额不足
							System.out.println("金额不足");
							transaction.rollback();
							return false;
						}
					}
				}
				if(from == null||to == null) {
					//账号不存在
					System.out.println("账号不存在");
					transaction.rollback();
					return false;
				}
				from.setBalance(from.getBalance()+balance);
				to.setBalance(to.getBalance()-balance);
				update(from, ICCard.class);
				update(to,ICCard.class);
				Bill bill = new Bill();
				//获取当前时间
				Calendar cal = Calendar.getInstance();
				String year = cal.get(Calendar.YEAR) + "";
				String month = cal.get(Calendar.MONTH)+1 + "";
				String day = cal.get(Calendar.DATE) + "";
				String hour = cal.get(Calendar.HOUR_OF_DAY) + "";
				String minute = cal.get(Calendar.MINUTE) + "";
				String date = year+"-"+month+"-"+day+"  "+hour+":"+minute;
				//插入两条转账记录
				bill.setDate(date);
				bill.setIcId(from.getNumber());
				bill.setIc2Id(to.getNumber());
				//0 是转入，1是转出
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
			//session为空
			System.err.println("空指针(NullPoint when Insert)" + this.getClass().getName() + "<"
					+ Thread.currentThread().getStackTrace()[1].getMethodName() + "> at line 378");
			return false;
		}
		return true;
	}

	/**
	 * List<Bill> TODO 查看账单
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
			//session为空
			System.err.println("空指针(NullPoint when Insert)" + this.getClass().getName() + "<"
					+ Thread.currentThread().getStackTrace()[1].getMethodName() + "> at line 455");
			return null;
		}
		return result;
	}
}
