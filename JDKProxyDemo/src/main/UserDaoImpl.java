
package main;   

/**  
* 创建时间：2018年12月6日 下午3:47:36  

* 项目名称：JDKProxy  
* @author homrls  
* @version 1.0   
* @since JDK 1.8.0_21  
* 文件名称：UserDaoImpl.java  
* 类说明：  
*/

public class UserDaoImpl implements UserDao{

	@Override
	public void save() {
		//假设执行了各种操作
		System.out.println("保存对象的一些操作");
		System.out.println("保存成功");
	}

}
  


