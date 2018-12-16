package cn.chd.utils;
import java.text.SimpleDateFormat;

/**
* ����ʱ�䣺2018��10��8�� ����4:57:34  

* ��Ŀ���ƣ�ͼ��ʶ��ͣ��ϵͳ    
* @author  
* @version 1.0   
* @since JDK 1.8.0_21  
* �ļ����ƣ�Utils.java  
* ��˵����  ��Ŀ���ù�����
*/

public class Utils {
	/**
	 * 
	 * double ���ش�ͣ��ʱ�䵽ȡ��ʱ��֮���Сʱ��������һСʱ�Ĳ��֣�һ�ɰ�0.5Сʱ����
	 * �� 8��00-9��00Ϊһ��Сʱ��8��00��9��01Ϊ1.5Сʱ��8��00-9��59Ϊ1.5Сʱ
	 * TODO �������ݵĸ�ʽΪ��20180101151234��14λ����ʾ2018��1��1��15��12��34��ͣ���� ��������ʱ�䣬����Сʱ��
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
	 * double  ���ش�ͣ��ʱ�䵽ȡ��ʱ��֮��ķ���������ֱ�Ӻ��Բ���
	 * TODO   �����ʽͬ��
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
	 * double[]  ����������
	 * TODO  ��double��С��������
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
	 * String  cut����ַ���
	 * TODO    �����ʽΪ20180101151234��14λ����ʾ2018��1��1��15��12��34��ͣ������ȡ������ֻ���������Ϣ��ǰ8λ��
	 */
	public static String cutString(String s) {
		String result = new String();
		//result=s.substring(0, 7);
		String year = s.substring(0,4);
		String month = s.substring(4,6);
		String day = s.substring(6,8);
		result = year+"��"+month+"��"+day+"��";
		return result;
	}
	
}
  


