
package cn.chd.webservice;   

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
/**  
* ����ʱ�䣺2018��12��12�� ����4:48:48  

* ��Ŀ���ƣ�WebServiceTest1  
* @author homrls  
* @version 1.0   
* @since JDK 1.8.0_21  
* �ļ����ƣ�Method.java  
* ��˵����  
*/
@WebService
@SOAPBinding(style = Style.RPC)
public interface Method {
	
	void insert();
	
	void update();
}
  


