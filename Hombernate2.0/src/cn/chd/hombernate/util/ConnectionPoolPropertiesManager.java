package cn.chd.hombernate.util;

import java.io.IOException;
import java.util.Properties;


/** 
* @author : homels
* @date ����ʱ�䣺2018��6��26�� ����8:11:46
* @ClassName: ConnectionPoolPropertiesManager
* @Description: ��ȡproperties�ļ���������Ϣ�����ڹ������ӳص�����
* @version 
*/
public class ConnectionPoolPropertiesManager {
	//��װһ��propertise���ڶ�ȡ�����������
	private static Properties pro = new Properties();
	//���ε��½�ʵ���Ĺ���
	private ConnectionPoolPropertiesManager(){}
	//��̬��������DB.properties
	static{
		try{
			//��ȡDB.properties����ŵ�pro
			pro.load(ConnectionPoolPropertiesManager.class.getClassLoader().getResourceAsStream("DB.properties"));
		} catch(IOException e){
			e.printStackTrace();
		}
	}
	
	//����ĳһ�����Ե�ֵ
	public static String getPropertiy(String key){
		return pro.getProperty(key);
	}
}
