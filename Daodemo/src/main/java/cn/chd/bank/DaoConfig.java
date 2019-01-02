
package cn.chd.bank;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**  
* ����ʱ�䣺2018��12��13�� ����3:45:25  

* ��Ŀ���ƣ�Daodemo  
* @author homrls  
* @version 1.0   
* @since JDK 1.8.0_21  
* �ļ����ƣ�DaoConfig.java  
* ��˵����  ����hibernate
*/

public class DaoConfig {
	private Configuration configuration;
	private SessionFactory factory;
	public DaoConfig(String driverName){
		String fileName = chooseDriver(driverName);
		System.out.println(fileName);
		try {
			configuration = new Configuration();
			configuration.configure(fileName);
			factory = configuration.buildSessionFactory();
		} catch(HibernateException e) {
			e.printStackTrace();
			System.err.println("��֧�ֵ����ݿ�����(unsupported database driver at "
					+this.getClass().getName()+
					Thread.currentThread().getStackTrace()[1].getMethodName()+" at line 29)");
		}
	}
	private String chooseDriver(String driverName) {
		//MYSQL SQLSEVER ACCESS
		//ѡ�������ļ�
		return driverName.toLowerCase()+".cfg.xml";
	}
	public Session getSession() {
		if(factory != null) {
			return factory.openSession();
		}
		return null;
	}
}
  


