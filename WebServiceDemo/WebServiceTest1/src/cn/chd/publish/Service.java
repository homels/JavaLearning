
package cn.chd.publish;

import javax.xml.ws.Endpoint;

import cn.chd.webservice.MethodImpl;

/**  
* ����ʱ�䣺2018��12��12�� ����4:50:38  

* ��Ŀ���ƣ�WebServiceTest1  
* @author homrls  
* @version 1.0   
* @since JDK 1.8.0_21  
* �ļ����ƣ�Service.java  
* ��˵����  
*/

public class Service {
	public static void main(String[] args) {
		String url = "http://localhost:9093/WebServiceTest1";
		Endpoint.publish(url, new MethodImpl());
		System.out.println("������");
	}
}
  


