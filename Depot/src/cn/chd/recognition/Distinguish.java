
package cn.chd.recognition;

import org.bytedeco.javacpp.opencv_imgcodecs;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

import org.bytedeco.javacpp.opencv_core.Mat;

import cc.eguid.charsocr.PlateRecognition;
import cn.chd.wucha.Adjust;

/**  
* ����ʱ�䣺2018��10��24�� ����3:53:39  

* ��Ŀ���ƣ�Depot  
* @author homrls  
* @version 1.0   
* @since JDK 1.8.0_21  
* �ļ����ƣ�Distinguish.java  
* ��˵����  
*/

public class Distinguish {
	private PlateRecognition pr = new PlateRecognition();
	public String DistinguishNumber(String path,Mat mat) {
		String result = "";
		int sum = 1;
		//����20��
		for (int i = sum; i > 0; i--) {
			//String imgPath = "res/image/test_image/eAEB506.jpg";
			Mat src = opencv_imgcodecs.imread(path);
			opencv_imgcodecs.imwrite("tmp/xingbuxnig.jpg", src);
			//��ʼʶ��
			//System.out.println("��ʼʶ��");
			String ret = pr.plateRecognise(src);
			//���һ��ʶ����
			if(ret==null||Adjust.isAdjust(ret)) {
//				System.out.println(path);
				ret = Adjust.AdjustResult(path);
			}
			//System.out.println("���Ϊ"+ret);
			result = ret;
		}
		return result;
	}
	public String DistinguishNumber2(String path,Mat mat) {
		String result = "";
		//int sum = 20;
		//����20��
        try {
        	//System.out.print(System.getProperty("user.dir"));
            Process process = Runtime.getRuntime().exec("py -3 "+System.getProperty("user.dir")+"\\src\\cn\\chd\\recognition\\PlateRecongntion.py " + path);
            InputStreamReader ir = new InputStreamReader(process.getInputStream());            
            LineNumberReader input = new LineNumberReader(ir);
            result = input.readLine();
            input.close();
            ir.close();
        } catch (IOException e) {
            //logger.error("����python�ű�����ȡ���ʱ����" + e.getMessage());
        }
//        System.out.print("res: "+result);
        return result;
	}
}
  