
package cn.chd.config;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**  
* 创建时间：2018年12月13日 下午3:45:25  

* 项目名称：Daodemo  
* @author homrls  
* @version 1.0   
* @since JDK 1.8.0_21  
* 文件名称：DaoConfig.java  
* 类说明：  配置hibernate
*/

public class DaoConfig {
	private Configuration configuration;
	private SessionFactory factory;
	DaoConfig(String driverName){
		String fileName = chooseDriver(driverName);
		try {
			configuration = new Configuration();
			configuration.configure(fileName);
			factory = configuration.buildSessionFactory();
		} catch(HibernateException e) {
			System.err.println("不支持的数据库驱动(unsupported database driver at "
					+this.getClass().getName()+
					Thread.currentThread().getStackTrace()[1].getMethodName()+" at line 29)");
		}
	}
	private String chooseDriver(String driverName) {
		//MYSQL SQLSEVER ACCESS
		//选定配置文件
		return driverName.toLowerCase()+".cfg.xml";
	}
	public Session getSession() {
		if(factory != null) {
			return factory.openSession();
		}
		return null;
	}
}
  


