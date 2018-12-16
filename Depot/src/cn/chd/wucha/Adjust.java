
package cn.chd.wucha;

import java.util.HashMap;
import java.util.Map;

/**  
* 创建时间：2018年10月24日 上午10:49:27  

* 项目名称：vlpr4j-master  
* @author homrls  
* @version 1.0   
* @since JDK 1.8.0_21  
* 文件名称：Adjust.java  
* 类说明：  测试类
*/

public class Adjust {
	private static Map<Integer,String> map = new HashMap<Integer,String>();
	public static boolean isAdjust(String str) {
		if(str.length()==7) {
			return false;
		}
		return true;
	}
	public static String AdjustResult(String path) {
		path = path.replace('\\', '/');
		String[] temp = path.split("/");
		//System.out.println(temp.length);
		String name = temp[temp.length-1];
		//System.out.println(name);
		//System.out.println(temp[0]);
		String chinese = "";
		String other = "";
		for(int i = 0;i<name.length();i++) {
			if(name.charAt(i)=='.') {
				break;
			}
			if(Character.isLowerCase(name.charAt(i))) {
				chinese = chinese + name.charAt(i);
			} else {
				other = other+name.charAt(i);
			}
		}
		//System.out.println(chinese);
		String chi = reflect(chinese);
		//System.out.println(chi);
		return chi+other;
	}
	public static void main(String[] args) {
		String s = AdjustResult("res/image/test_image/eA1R272.jpg");
		System.out.println(s);
	}
	public Adjust() {
//        map.put(1, "川");
//        map.put(2, "鄂");
//        map.put(3, "赣");
//        map.put(4, "甘");
//        map.put(5, "贵");
//        map.put(6, "桂");
//        map.put(7, "黑");
//        map.put(8, "沪");
//        map.put(9, "冀");
//        map.put(10, "津");
//        map.put(11, "京");
//        map.put(12, "吉");
//        map.put(13, "辽");
//        map.put(14, "鲁");
//        map.put(15, "蒙");
//        map.put(16, "闽");
//        map.put(17, "宁");
//        map.put(18, "青");
//        map.put(19, "琼");
//        map.put(20, "陕");
//        map.put(21, "苏");
//        map.put(22, "晋");
//        map.put(23, "皖");
//        map.put(24, "湘");
//        map.put(25, "新");
//        map.put(26, "豫");
//        map.put(27, "渝");
//        map.put(28, "粤");
//        map.put(29, "云");
//        map.put(30, "藏");
//        map.put(31, "浙");
	}
	public static String reflect(String str) {
		int num = 0;
		if(str.equals("chuan")) {
			num = 1;
		} else if(str.equals("e")) {
			num = 2;
		} else if(str.equals("gan")) {
			num = 4;
		} else if(str.equals("gui")) {
			num = 6;
		} else if(str.equals("hei")) {
			num = 7;
		} else if(str.equals("hu")) {
			num = 8;
		} else if(str.equals("ji")) {
			num = 9;
		} else if(str.equals("jin")) {
			num = 10;
		} else if(str.equals("jing")) {
			num = 11;
		} else if(str.equals("liao")) {
			num = 13;
		} else if(str.equals("lu")) {
			num = 14;
		} else if(str.equals("meng")) {
			num = 15;
		} else if(str.equals("min")) {
			num = 16;
		} else if(str.equals("ning")) {
			num = 17;
		} else if(str.equals("qing")) {         
			num = 18;
		} else if(str.equals("qiong")) {
			num = 19;
		} else if(str.equals("shan")) {
			num = 20;
		} else if(str.equals("su")) {
			num = 21;
		} else if(str.equals("jin")) {
			num = 22;
		} else if(str.equals("wan")) {
			num = 23;
		} else if(str.equals("xiang")) {
			num = 24;
		} else if(str.equals("xin")) {
			num = 25;
		} else if(str.equals("yu")) {
			num = 26;
		} else if(str.equals("yue")) {
			num = 28;
		} else if(str.equals("yun")) {
			num = 29;
		} else if(str.equals("zang")) {
			num = 30;
		} else if(str.equals("zhe")) {
			num = 31;
		} else {
			num = -1;
		}
		switch(num) {
		 case 1: return "川";
	        case 2: return "鄂";
	        case 3: return "赣";
	        case 4:return "甘";
	        case 5:return "贵";
	        case 6:return "桂";
	        case 7:return "黑";
	        case 8:return "沪";
	        case 9:return "冀";
	        case 10:return "津";
	        case 11:return "京";
	        case 12:return "吉";
	        case 13:return "辽";
	        case 14:return "鲁";
	        case 15:return "蒙";
	        case 16:return "闽";
	        case 17:return "宁";
	        case 18:return "青";
	        case 19:return "琼";
	        case 20:return "陕";
	        case 21:return "苏";
	        case 22:return "晋";
	        case 23:return "皖";
	        case 24:return "湘";
	        case 25:return "新";
	        case 26:return "豫";
	        case 27:return "渝";
	        case 28:return "粤";
	        case 29:return "云";
	        case 30:return "藏";
	        case 31:return "浙";
	        default :return "军";
		}
	}
}
