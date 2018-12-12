
package cn.chd.publish;

import javax.xml.ws.Endpoint;

import cn.chd.webservice.MethodImpl;

/**  
* 创建时间：2018年12月12日 下午4:50:38  

* 项目名称：WebServiceTest1  
* @author homrls  
* @version 1.0   
* @since JDK 1.8.0_21  
* 文件名称：Service.java  
* 类说明：  
*/

public class Service {
	public static void main(String[] args) {
		String url = "http://localhost:9093/WebServiceTest1";
		Endpoint.publish(url, new MethodImpl());
		System.out.println("服务开启");
	}
}
  


