
package cn.chd.webservice;

import javax.jws.WebService;

import cn.chd.dao.DaoUtil;

/**  
* ����ʱ�䣺2018��12��12�� ����4:46:22  

* ��Ŀ���ƣ�WebServiceTest1  
* @author homrls  
* @version 1.0   
* @since JDK 1.8.0_21  
* �ļ����ƣ�Method.java  
* ��˵����  
*/
@WebService(endpointInterface="cn.chd.webservice.Method")
public class MethodImpl implements Method{
	DaoUtil dao = new DaoUtil();
	public void insert() {
		dao.insert();
	}
	public void update() {
		dao.update();
	}
}
  


