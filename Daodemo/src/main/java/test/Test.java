
package test;

import java.util.Calendar;

import cn.chd.bank.BankMethod;
import cn.chd.bank.DaoConfig;
import pojo.Bill;
import pojo.ICCard;
import pojo.User;

/**  
* ����ʱ�䣺2018��12��18�� ����3:27:58  

* ��Ŀ���ƣ�Daodemo  
* @author homrls  
* @version 1.0   
* @since JDK 1.8.0_21  
* �ļ����ƣ�Test.java  
* ��˵����  
*/

public class Test {
	public static void main(String[] args) {
//		BankMethod banMethod = new BankMethod("mysql");
//		System.out.println(banMethod.select(User.class));
//		System.out.println(banMethod.select(Bill.class));
//		System.out.println(banMethod.select(ICCard.class));
//		System.out.println(banMethod.checkUserIslegal("user1", "123456"));
//		banMethod.addICCard(1, "621754892145324", "123456");
//		banMethod.deleteICCard(1, "621754892145324", "123456");
//		System.out.println(banMethod.queryBalance(1, "621754899562147"));
//		
//		/***********************************************************/
//		BankMethod banMethod1 = new BankMethod();
//		DaoConfig config = new DaoConfig("mysql");
//		banMethod1.setConfig(config);
////		System.out.println(banMethod1.select(User.class));
//		Calendar cal = Calendar.getInstance();
//		String year = cal.get(Calendar.YEAR) + "";
//		String month = cal.get(Calendar.MONTH) + "";
//		String day = cal.get(Calendar.DATE) + "";
//		System.out.println(year+"-"+month+"-"+day);
		BankMethod bankMethod = new BankMethod();
		DaoConfig config = new DaoConfig("mysql");
		bankMethod.setConfig(config);
//		System.out.println(bankMethod.addICCard(6, "621754892145324", "123456"));
//		System.out.println(bankMethod.deleteICCard(6, "621754892145324", "123456"));

		
		System.out.println(bankMethod.transfer("621785361234499", "621785364215499", 1));
		
//		Calendar cal = Calendar.getInstance();
//		String year = cal.get(Calendar.YEAR) + "";
//		String month = cal.get(Calendar.MONTH)+1 + "";
//		String day = cal.get(Calendar.DATE) + "";
//		String hour = cal.get(Calendar.HOUR_OF_DAY) + "";
//		String minute = cal.get(Calendar.MINUTE) + "";
//		String date = year+"-"+month+"-"+day+"  "+hour+":"+minute;
//		System.out.println(date);
		
	}
}
  


