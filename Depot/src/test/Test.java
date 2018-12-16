
package test;

import org.bytedeco.javacpp.opencv_imgcodecs;
import org.bytedeco.javacpp.opencv_core.Mat;

import cc.eguid.charsocr.PlateRecognition;
import cn.chd.wucha.Adjust;

/**  
* 创建时间：2018年10月23日 下午4:18:56  

* 项目名称：vlpr4j-master  
* @author homrls  
* @version 1.0   
* @since JDK 1.8.0_21  
* 文件名称：Test.java  
* 类说明：  
*/

public class Test {
	static PlateRecognition pr = new PlateRecognition();
	public static void main(String[] args) {
		int sum = 20;
		//进行20次
		for (int i = sum; i > 0; i--) {
			String imgPath = "res/image/test_image/eAEB506.jpg";
			Mat src = opencv_imgcodecs.imread(imgPath);
			//开始识别
			System.out.println("开始识别");
			String ret = pr.plateRecognise(src);
			//输出一次识别结果
			if(Adjust.isAdjust(ret)) {
				ret = Adjust.AdjustResult(imgPath);
			}
			System.out.println("结果为"+ret);
		}
	}
}
