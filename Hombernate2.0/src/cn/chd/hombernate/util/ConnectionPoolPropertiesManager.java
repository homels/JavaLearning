package cn.chd.hombernate.util;

import java.io.IOException;
import java.util.Properties;


/** 
* @author : homels
* @date 创建时间：2018年6月26日 下午8:11:46
* @ClassName: ConnectionPoolPropertiesManager
* @Description: 读取properties文件的配置信息，用于管理连接池的属性
* @version 
*/
public class ConnectionPoolPropertiesManager {
	//封装一个propertise用于读取保存各项属性
	private static Properties pro = new Properties();
	//屏蔽掉新建实例的过程
	private ConnectionPoolPropertiesManager(){}
	//静态代码块加载DB.properties
	static{
		try{
			//读取DB.properties，存放到pro
			pro.load(ConnectionPoolPropertiesManager.class.getClassLoader().getResourceAsStream("DB.properties"));
		} catch(IOException e){
			e.printStackTrace();
		}
	}
	
	//返回某一项属性的值
	public static String getPropertiy(String key){
		return pro.getProperty(key);
	}
}
