
package cn.chd.recognition;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.bytedeco.javacpp.opencv_core.Mat;

import cn.chd.dao.MyConnection;
import cn.chd.utils.Utils;

/**  
* ����ʱ�䣺2018��10��8�� ����7:36:05  

* ��Ŀ���ƣ�Depot  
* @author homrls  
* @version 1.0   
* @since JDK 1.8.0_21  
* �ļ����ƣ�Recognition.java  
* ��˵����  �߼��ж�
*/

public class Recognition {
	MyConnection connection = new MyConnection();
	private String number;
	private double fee;
	private SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//�������ڸ�ʽ
	public Recognition(String number,double fee) {
		this.number = number;
		this.fee = fee;
		//checkTemp();
	}
	public boolean checkTemp() {
		if(connection.isExistIntoTemporary(number)) {
			//ȡ��
//			System.out.println("ȡ��");
//			connection.insertIntoForever(number, startTime, endTime, cost);
//			connection.deleteIntoTemporary(number);
			return true;
		} else {
			//�泵
//			System.out.println("�泵");
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
		//���㻨��
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