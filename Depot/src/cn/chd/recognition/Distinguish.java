
package cn.chd.recognition;

import org.bytedeco.javacpp.opencv_imgcodecs;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

import org.bytedeco.javacpp.opencv_core.Mat;

import cc.eguid.charsocr.PlateRecognition;
import cn.chd.wucha.Adjust;

/**  
* 创建时间：2018年10月24日 下午3:53:39  

* 项目名称：Depot  
* @author homrls  
* @version 1.0   
* @since JDK 1.8.0_21  
* 文件名称：Distinguish.java  
* 类说明：  
*/

public class Distinguish {
	private PlateRecognition pr = new PlateRecognition();
	public String DistinguishNumber(String path,Mat mat) {
		String result = "";
		int sum = 1;
		//进行20次
		for (int i = sum; i > 0; i--) {
			//String imgPath = "res/image/test_image/eAEB506.jpg";
			Mat src = opencv_imgcodecs.imread(path);
			opencv_imgcodecs.imwrite("tmp/xingbuxnig.jpg", src);
			//开始识别
			//System.out.println("开始识别");
			String ret = pr.plateRecognise(src);
			//输出一次识别结果
			if(ret==null||Adjust.isAdjust(ret)) {
//				System.out.println(path);
				ret = Adjust.AdjustResult(path);
			}
			//System.out.println("结果为"+ret);
			result = ret;
		}
		return result;
	}
	public String DistinguishNumber2(String path,Mat mat) {
		String result = "";
		//int sum = 20;
		//进行20次
        try {
        	//System.out.print(System.getProperty("user.dir"));
            Process process = Runtime.getRuntime().exec("py -3 "+System.getProperty("user.dir")+"\\src\\cn\\chd\\recognition\\PlateRecongntion.py " + path);
            InputStreamReader ir = new InputStreamReader(process.getInputStream());            
            LineNumberReader input = new LineNumberReader(ir);
            result = input.readLine();
            input.close();
            ir.close();
        } catch (IOException e) {
            //logger.error("调用python脚本并读取结果时出错：" + e.getMessage());
        }
//        System.out.print("res: "+result);
        return result;
	}
}
  