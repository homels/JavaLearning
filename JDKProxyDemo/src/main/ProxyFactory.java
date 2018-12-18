
package main;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**  
* ����ʱ�䣺2018��12��6�� ����3:48:46  

* ��Ŀ���ƣ�JDKProxy  
* @author homrls  
* @version 1.0   
* @since JDK 1.8.0_21  
* �ļ����ƣ�ProxyFactory.java  
* ��˵����  �������ͣ� https://www.cnblogs.com/linzhong/p/7234051.html
*/

public class ProxyFactory {
	//ά����Ŀ�����
	private Object target;
	
	public ProxyFactory(Object target) {
		this.target = target;
	}
	
	public Object getProxyInstance() {
		return Proxy.newProxyInstance(
				target.getClass().getClassLoader(), 
				target.getClass().getInterfaces(), 
				new InvocationHandler() {
					
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						System.out.println("ǰ���һЩ����");
						Object returnValue = method.invoke(target, args);
						System.out.println("�����һЩ����");
						return returnValue;
					}
				});
	}
}
  


