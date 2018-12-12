
package cn.chd.webservice;

import javax.jws.WebService;

import cn.chd.dao.DaoUtil;

/**  
* 创建时间：2018年12月12日 下午4:46:22  

* 项目名称：WebServiceTest1  
* @author homrls  
* @version 1.0   
* @since JDK 1.8.0_21  
* 文件名称：Method.java  
* 类说明：  
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
  


