package cn.chd.utils;
import java.text.SimpleDateFormat;

/**
* 创建时间：2018年10月8日 下午4:57:34  

* 项目名称：图像识别停车系统    
* @author  
* @version 1.0   
* @since JDK 1.8.0_21  
* 文件名称：Utils.java  
* 类说明：  项目常用工具类
*/

public class Utils {
	/**
	 * 
	 * double 返回从停车时间到取车时间之间的小时数，不足一小时的部分，一律按0.5小时计算
	 * 如 8：00-9：00为一个小时，8：00到9：01为1.5小时，8：00-9：59为1.5小时
	 * TODO 输入数据的格式为（20180101151234，14位，表示2018年1月1日15点12分34秒停车） 输入两个时间，返回小时数
	 */
	public static double calIntervalHour(String startTime,String endTime) throws java.text.ParseException{
		SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String sTime = simpleFormat.format(simpleFormat.parse(startTime));
		String eTime = simpleFormat.format(simpleFormat.parse(endTime));
		long start = simpleFormat.parse(sTime).getTime();
		long end = simpleFormat.parse(eTime).getTime();
		double result=0.0;
		if(Integer.parseInt(startTime.substring(10,14)) - Integer.parseInt(endTime.substring(10,14)) == 0){
			int hours = (int) ((start - end)/(1000 * 60 * 60 ));
			result = (double) hours;}
		else{
			int hours = (int) ((start - end)/(1000 * 60 * 60 ));
			result = (double) hours + 0.5;}
		return result;
	}
	/**
	 * 
	 * double  返回从停车时间到取车时间之间的分钟数，秒直接忽略不计
	 * TODO   输入格式同上
	 */
	public static double calIntervalMinute(String startTime,String endTime) throws java.text.ParseException{
		SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String sTime = simpleFormat.format(simpleFormat.parse(startTime));
		String eTime = simpleFormat.format(simpleFormat.parse(endTime));
		long start = simpleFormat.parse(sTime).getTime();
		long end = simpleFormat.parse(eTime).getTime();
		double result = (double) ((start - end)/(1000 * 60));
		return result;
	}


	/**
	 * double[]  排序后的数组
	 * TODO  将double从小到大排序
	 */
	public static double[] Sort(double[] a) {
		//double[] result = new double[a.length];
		double t = 0;
		for (int i = 0; i < a.length - 1; i++)
			for (int j = 0; j < a.length - 1 - i; j++)
				if (a[j] > a[j + 1]) {
					t = a[j];
					a[j] = a[j + 1];
					a[j + 1] = t;
				}
		return a;
	}
	/**
	 * String  cut后的字符串
	 * TODO    输入格式为20180101151234，14位，表示2018年1月1日15点12分34秒停车（或取车），只输出日期信息（前8位）
	 */
	public static String cutString(String s) {
		String result = new String();
		//result=s.substring(0, 7);
		String year = s.substring(0,4);
		String month = s.substring(4,6);
		String day = s.substring(6,8);
		result = year+"年"+month+"月"+day+"日";
		return result;
	}
	
}
  


