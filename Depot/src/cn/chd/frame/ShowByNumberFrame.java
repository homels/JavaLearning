
package cn.chd.frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.tree.ExpandVetoException;

import cn.chd.Export.Export;
import cn.chd.Export.Record;
import cn.chd.dao.MyConnection;
import cn.chd.utils.Utils;
import jxl.write.WriteException;

/**
 * ����ʱ�䣺2018��10��8�� ����7:34:54
 * 
 * ��Ŀ���ƣ�Depot
 * 
 * @author homrls
 * @version 1.0
 * @since JDK 1.8.0_21 �ļ����ƣ�ShowFrame.java ��˵����
 */

public class ShowByNumberFrame implements ActionListener {

	private List<Record> rList = new ArrayList<Record>();
	
	private MyConnection connection = new MyConnection();

	private JFrame frame;
	private JLabel text = new JLabel("���복�ƺ�");
	// �����
	private JTextField input = new JTextField();
	private JButton sure = new JButton("��ѯ");
	// ��ʾ��ѯ���
	private JTextArea showArea = new JTextArea();

	private JButton export = new JButton("����");
	public ShowByNumberFrame() {
		frame = new JFrame("������Ϣ");
		frame.setLayout(null);
		frame.setSize(900, 900);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(1);
		text.setBounds(20, 50, 100, 40);
		frame.add(text);
		sure.setBounds(270, 50, 100, 40);
		frame.add(sure);
		sure.addActionListener(this);
		sure.setActionCommand("sure");
		showArea.setBounds(80, 120, 650, 700);
		frame.add(showArea);
		input.setBounds(120, 50, 100, 40);
		frame.add(input);
		showArea.setEditable(false);
		export.setBounds(470, 50, 100, 40);
		frame.add(export);
		export.addActionListener(this);
		export.setActionCommand("export");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("sure")) {
			// ��ѯ����
			List<String> list = new ArrayList<String>();
			list = connection.selectByNumber(input.getText());
//			System.out.println(list.size());
//			System.out.println(list.get(0));
			showArea.append("���ƺ�" + "\t");
			showArea.append("ͣ��ʱ��" + "\t\t");
			showArea.append("ȡ��ʱ��" + "\t\t");
			showArea.append("�շ�" + "(Ԫ)" + "\t");
			showArea.append("\r\n");
			for (int i = 0; i < list.size(); i++) {
				String[] temp = list.get(i).split("split");
				Record r = new Record();
				r.setCarNumber(temp[0]);
				r.setStartTime(temp[1]);
				r.setEndTime(temp[2]);
				r.setFee(Double.parseDouble(temp[3]));
				rList.add(r);
				System.out.println(temp[1]);
				for (int j = 0; j < temp.length; j++) {
					if(j==1||j==2) {
						temp[j] = Utils.cutString(temp[j]);
					}
					showArea.append(temp[j]);
					showArea.append("\t");
				}
				showArea.append("\r\n");
			}
		} else if(e.getActionCommand().equals("export")) {
			Export export = new Export(rList);
			try {
				export.exportToExcel();
			} catch (WriteException | IOException e1) {
				e1.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, "�����ɹ�", "notice", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
