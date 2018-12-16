package cc.eguid.charsocr.core;

import static org.bytedeco.javacpp.opencv_core.*;
import static org.bytedeco.javacpp.opencv_imgproc.resize;

import java.util.Vector;

import org.bytedeco.javacpp.opencv_imgcodecs;
import org.bytedeco.javacpp.opencv_core.Mat;
import org.bytedeco.javacpp.opencv_core.Rect;
import org.bytedeco.javacpp.opencv_core.Size;
import org.bytedeco.javacpp.opencv_ml.SVM;

/**
 * 车牌判断
 * @author eguid
 *
 */
public class PlateJudge {

    public PlateJudge() {
        loadModel();
    }

    public void loadModel() {
        loadModel(path);
    }

    public void loadModel(String s) {
        svm.clear();
        svm=SVM.loadSVM(s, "svm");
    }
    
    /**
     * 对单幅图像进行SVM判断
     * 
     * @param inMat
     * @return
     */
    public int plateJudge(Mat inMat) {
    	opencv_imgcodecs.imwrite("my/test.jpg", inMat);
    	//Features自己定义的类，特性类  features里存的是inMat的样本特征，包括水平直方图、垂直直方图、小图像
        Mat features = this.features.getHistogramFeatures(inMat);
      //  opencv_imgcodecs.imwrite("my/debug_morphology.jpg", features);
        // 通过直方图均衡化后的彩色图进行预测
        Mat p = features.reshape(1, 1);
        p.convertTo(p, CV_32FC1);

        float ret = svm.predict(p);
       
        return (int) ret;
    }

    /**
     * 对多幅图像进行SVM判断  有多副“疑似车牌”，用SVM筛选
     * 
     * @param inVec
     * @param resultVec
     * @return
     */
    public int plateJudge(Vector<Mat> inVec, Vector<Mat> resultVec) {

        for (int j = 0; j < inVec.size(); j++) {
            Mat inMat = inVec.get(j);
            opencv_imgcodecs.imwrite("my/"+j+""+".jpg", inMat);
            if (1 == plateJudge(inMat)) {
                resultVec.add(inMat);
            } else { 
            	// 再取中间部分判断一次
                int w = inMat.cols();
                int h = inMat.rows();
                Mat tmpDes = inMat.clone();
                Mat tmpMat = new Mat(inMat, new Rect((int) (w * 0.05), (int) (h * 0.1), (int) (w * 0.9),
                        (int) (h * 0.8)));
                resize(tmpMat, tmpDes, new Size(inMat.size()));
                if (plateJudge(tmpDes) == 1) {
                    resultVec.add(inMat);
                }
            }
        }
        return 0;
    }

    public void setModelPath(String path) {
        this.path = path;
    }

    public final String getModelPath() {
        return path;
    }
   
    private SVM svm = SVM.create();

    /**
     * EasyPR的getFeatures回调函数, 用于从车牌的image生成svm的训练特征features
     */
    private SVMCallback features = new Features();

    /**
     * 模型存储路径
     */
    private String path = "res/model/svm.xml";
}
