
package main;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**  
* 创建时间：2018年12月6日 下午3:48:46  

* 项目名称：JDKProxy  
* @author homrls  
* @version 1.0   
* @since JDK 1.8.0_21  
* 文件名称：ProxyFactory.java  
* 类说明：  详解见博客： https://www.cnblogs.com/linzhong/p/7234051.html
*/

public class ProxyFactory {
	//维护的目标对象
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
						System.out.println("前面的一些操作");
						Object returnValue = method.invoke(target, args);
						System.out.println("后面的一些操作");
						return returnValue;
					}
				});
	}
}
  


