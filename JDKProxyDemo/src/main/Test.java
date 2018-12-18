
package main;   

/**  
* 创建时间：2018年12月6日 下午3:52:59  

* 项目名称：JDKProxy  
* @author homrls  
* @version 1.0   
* @since JDK 1.8.0_21  
* 文件名称：Test.java  
* 类说明： 详解见博客： https://www.cnblogs.com/linzhong/p/7234051.html
*/

public class Test {
	public static void main(String[] args) {
		//目标对象
		UserDao target = new UserDaoImpl();
		System.out.println(target.getClass());
		
		//目标对象的代理
		UserDao proxy = (UserDao) new ProxyFactory(target).getProxyInstance();
		System.out.println(proxy.getClass());
		
		proxy.save();
	}
}
  


