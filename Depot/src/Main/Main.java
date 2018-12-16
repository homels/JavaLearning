
package Main;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;

import cn.chd.frame.RecognitionFrame;

/**  
* ����ʱ�䣺2018��10��8�� ����7:45:57  

* ��Ŀ���ƣ�Depot  
* @author homrls  
* @version 1.0   
* @since JDK 1.8.0_21  
* �ļ����ƣ�Main.java  
* ��˵����  
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
