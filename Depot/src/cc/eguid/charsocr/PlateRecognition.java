package cc.eguid.charsocr;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.SampleModel;
import java.math.BigDecimal;
import java.util.Vector;

import org.bytedeco.javacpp.opencv_imgcodecs;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacpp.opencv_core.CvType;
import org.bytedeco.javacpp.opencv_core.CvTypeInfo;
import org.bytedeco.javacpp.opencv_core.Mat;

import cc.eguid.charsocr.core.CharsRecognise;
import cc.eguid.charsocr.core.PlateDetect;

/**
 * 车牌识别
 * 
 * @author eguid
 *
 */
public class PlateRecognition {
	static PlateDetect plateDetect = null;
	static CharsRecognise cr = null;
	static {
		plateDetect = new PlateDetect();
		plateDetect.setPDLifemode(true);
		cr = new CharsRecognise();
	}

	/**
	 * 单个车牌识别
	 * 
	 * @param mat
	 * @return
	 */
	public String plateRecognise(Mat mat) {
		//单车牌检测，只可能有一个地方有车牌
		//matVector用来保存那个地方
		Vector<Mat> matVector = new Vector<Mat>(1);
		//返回0证明有图像里检测到了车牌
		if (0 == plateDetect.plateDetect(mat, matVector)) {
			//matVector是车牌的区域
			if (matVector.size() > 0) {
				opencv_imgcodecs.imwrite("tmp/tttest.jpg", matVector.get(0));
				//把图片区域传过去
				return cr.charsRecognise(matVector.get(0));
			}
		}
		return null;
	}

	/**
	 * 多车牌识别
	 * 
	 * @param mat
	 * @return
	 */
	public String[] mutiPlateRecognise(Mat mat) {
		PlateDetect plateDetect = new PlateDetect();
		plateDetect.setPDLifemode(true);
		Vector<Mat> matVector = new Vector<Mat>(10);
		if (0 == plateDetect.plateDetect(mat, matVector)) {
			CharsRecognise cr = new CharsRecognise();
			String[] results = new String[matVector.size()];
			for (int i = 0; i < matVector.size(); ++i) {
				String result = cr.charsRecognise(matVector.get(i));
				results[i] = result;
			}
			return results;
		}
		return null;
	}

	/**
	 * 单个车牌识别
	 * 
	 * @param mat
	 * @return
	 */
	public  String plateRecognise(String imgPath) {
		Mat src = opencv_imgcodecs.imread(imgPath);
		return plateRecognise(src);
	}

	/**
	 * 多车牌识别
	 * 
	 * @param mat
	 * @return
	 */
	public  String[] mutiPlateRecognise(String imgPath) {
		Mat src = opencv_imgcodecs.imread(imgPath);
		return mutiPlateRecognise(src);
	}
}
