
package cn.chd.webservice;   

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
/**  
* 创建时间：2018年12月12日 下午4:48:48  

* 项目名称：WebServiceTest1  
* @author homrls  
* @version 1.0   
* @since JDK 1.8.0_21  
* 文件名称：Method.java  
* 类说明：  
*/
@WebService
@SOAPBinding(style = Style.RPC)
public interface Method {
	
	void insert();
	
	void update();
}
  


