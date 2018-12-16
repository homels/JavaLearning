
package Main;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;

import cn.chd.frame.RecognitionFrame;

/**  
* 创建时间：2018年10月8日 下午7:45:57  

* 项目名称：Depot  
* @author homrls  
* @version 1.0   
* @since JDK 1.8.0_21  
* 文件名称：Main.java  
* 类说明：  
*/

public class Main {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame.setDefaultLookAndFeelDecorated(true);
				JDialog.setDefaultLookAndFeelDecorated(true);
				try {
					UIManager
							.setLookAndFeel(new org.jvnet.substance.skin.SubstanceDustCoffeeLookAndFeel());
				} catch (Exception e) {
					e.printStackTrace();
				}
				RecognitionFrame rf  = new RecognitionFrame();
			}
		});

	}
}
