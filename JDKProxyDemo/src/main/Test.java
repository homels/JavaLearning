
package main;   

/**  
* ����ʱ�䣺2018��12��6�� ����3:52:59  

* ��Ŀ���ƣ�JDKProxy  
* @author homrls  
* @version 1.0   
* @since JDK 1.8.0_21  
* �ļ����ƣ�Test.java  
* ��˵���� �������ͣ� https://www.cnblogs.com/linzhong/p/7234051.html
*/

public class Test {
	public static void main(String[] args) {
		//Ŀ�����
		UserDao target = new UserDaoImpl();
		System.out.println(target.getClass());
		
		//Ŀ�����Ĵ���
		UserDao proxy = (UserDao) new ProxyFactory(target).getProxyInstance();
		System.out.println(proxy.getClass());
		
		proxy.save();
	}
}
  


