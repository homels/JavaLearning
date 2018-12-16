
package cn.chd.frame;   

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;
/**  
* ����ʱ�䣺2018��10��9�� ����2:09:37  

* ��Ŀ���ƣ�Depot  
* @author homrls  
* @version 1.0   
* @since JDK 1.8.0_21  
* �ļ����ƣ�AutoResize.java  
* ��˵����  �Զ�������С��JPanel
*/
 
public class AutoResize extends JPanel {
    BufferedImage image;
 
    public AutoResize() {
    	try {
			this.image = ImageIO.read(new File("C:\\Users\\Lenovo\\Desktop\\����ʶ�����ͼƬ��(237��������Ƭ)(�ļ������ǳ��ƺ�)\\��A28076.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
    	//AutoResize()
    }
    public AutoResize(String name) {
    	try {
			this.image = ImageIO.read(new File(name));
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    public AutoResize(BufferedImage image) {
        this.image = image;
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                            RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        int w = getWidth();
        int h = getHeight();
        int iw = image.getWidth();
        int ih = image.getHeight();
        double xScale = (double)w/iw;
        double yScale = (double)h/ih;
        double scale = Math.min(xScale, yScale);   
        int width = (int)(scale*iw);
        int height = (int)(scale*ih);
        int x = (w - width)/2;
        int y = (h - height)/2;
        g2.drawImage(image, x, y, width, height, this);
    }
 
//    public static void main(String[] args) throws IOException {
//        String path = &quot;images/bison.jpg&quot;;
//        BufferedImage image = ImageIO.read(new File(path));
//        AutoResize test = new AutoResize(image);
//        JFrame f = new JFrame();
//        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        f.add(test);
//        f.setSize(400,400);
//        f.setLocation(200,200);
//        f.setVisible(true);
//    }
}