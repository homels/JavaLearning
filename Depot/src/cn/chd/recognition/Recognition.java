
package cn.chd.recognition;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.bytedeco.javacpp.opencv_core.Mat;

import cn.chd.dao.MyConnection;
import cn.chd.utils.Utils;

/**  
* 创建时间：2018年10月8日 下午7:36:05  

* 项目名称：Depot  
* @author homrls  
* @version 1.0   
* @since JDK 1.8.0_21  
* 文件名称：Recognition.java  
* 类说明：  逻辑判断
*/

public class Recognition {
	MyConnection connection = new MyConnection();
	private String number;
	private double fee;
	private SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
	public Recognition(String number,double fee) {
		this.number = number;
		this.fee = fee;
		//checkTemp();
	}
	public boolean checkTemp() {
		if(connection.isExistIntoTemporary(number)) {
			//取车
//			System.out.println("取车");
//			connection.insertIntoForever(number, startTime, endTime, cost);
//			connection.deleteIntoTemporary(number);
			return true;
		} else {
			//存车
//			System.out.println("存车");
			//goIn();
			//connection.insertIntoTemporary(number, startTime);
			return false;
		}
	}
	public String distinguishNumber(Mat mat) {
		return "";
	}
	public void goIn() {
		String startTime = df.format(new Date());
		connection.insertIntoTemporary(number, startTime);
		//System.out.println("goIn");
	}
	public String goOut() {
		String endTime = df.format(new Date());
		String startTime = connection.getStratTimeByTemporary(number);
		//计算花费
		double cost = 0.0;
		try {
			cost = fee*Utils.calIntervalHour(startTime, endTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		connection.insertIntoForever(number, startTime, endTime, cost);
		//System.out.println(number);
		connection.deleteIntoTemporary(number);
		return cost+"";
	}
}