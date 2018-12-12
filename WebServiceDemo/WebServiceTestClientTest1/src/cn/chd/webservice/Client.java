
package cn.chd.webservice;

import javax.xml.ws.Service;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;

/**
 * 创建时间：2018年12月12日 下午4:53:57
 * 
 * 项目名称：WebServiceTestClientTest1
 * 
 * @author homrls
 * @version 1.0
 * @since JDK 1.8.0_21 文件名称：Client.java 类说明：
 */

public class Client {
	public static void main(String[] args) {
		URL url;
		try {
			url = new URL("http://localhost:9093/WebServiceTest1?wsdl");

			QName qname = new QName("http://webservice.chd.cn/", "MethodImplService");
			Service service = Service.create(url, qname);
			
			Method method = service.getPort(Method.class);
			method.insert();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
}
