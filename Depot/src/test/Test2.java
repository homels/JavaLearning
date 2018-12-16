
package test;

import org.bytedeco.javacpp.opencv_core.Mat;
import org.bytedeco.javacpp.opencv_imgcodecs;

import cc.eguid.charsocr.core.CharsRecognise;

/**  
* 创建时间：2018年10月24日 上午10:06:01  

* 项目名称：vlpr4j-master  
* @author homrls  
* @version 1.0   
* @since JDK 1.8.0_21  
* 文件名称：Test2.java  
* 类说明：  
*/

public class Test2 {
	static CharsRecognise cr = new CharsRecognise();
	public static void main(String[] args) {
		for(int i = 0;i<20;i++) {
			String imgPath = "res/image/test_image/aaa.png";
			Mat plate = opencv_imgcodecs.imread(imgPath);
			System.out.println(cr.charsRecognise(plate));
		}

	}
}
  