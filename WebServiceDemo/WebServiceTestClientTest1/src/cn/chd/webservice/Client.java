
package cn.chd.webservice;

import javax.xml.ws.Service;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;

/**
 * ����ʱ�䣺2018��12��12�� ����4:53:57
 * 
 * ��Ŀ���ƣ�WebServiceTestClientTest1
 * 
 * @author homrls
 * @version 1.0
 * @since JDK 1.8.0_21 �ļ����ƣ�Client.java ��˵����
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
