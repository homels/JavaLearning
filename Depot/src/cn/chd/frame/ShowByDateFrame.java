
package cn.chd.frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.eltima.components.ui.DatePicker;

import cn.chd.Export.Export;
import cn.chd.Export.Record;
import cn.chd.dao.MyConnection;
import cn.chd.utils.Utils;
import jxl.write.WriteException;

/**
 * ����ʱ�䣺2018��10��11�� ����4:32:18
 * 
 * ��Ŀ���ƣ�Depot
 * 
 * @author homrls
 * @version 1.0
 * @since JDK 1.8.0_21 �ļ����ƣ�ShowByDateFrame.java ��˵����
 */

public class ShowByDateFrame implements ActionListener {
	private List<Record> rList = new ArrayList<Record>();
	private JButton export = new JButton("����");
	private MyConnection connection = new MyConnection();
	private JFrame frame;
	private DatePicker dp = new DatePicker();
	private DatePicker dp1 = new DatePicker();
	// private JTextField date1 = new JTextField();
	private JLabel text = new JLabel("ѡ���ѯ����");
	private JButton sure = new JButton("��ѯ");
	// ��ʾ��ѯ���
	private JTextArea showArea = new JTextArea();

	public ShowByDateFrame() {
		frame = new JFrame("������Ϣ");
		frame.setLayout(null);
		frame.setSize(900, 900);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(1);
		dp = getDatePicker();
		dp.setBounds(120, 50, 150, 40);
		frame.add(dp);
		dp1 = getDatePicker();
		dp1.setBounds(300, 50, 150, 40);
		frame.add(dp1);
		text.setBounds(20, 50, 100, 40);
		frame.add(text);
		sure.setBounds(470, 50, 100, 40);
		frame.add(sure);
		showArea.setBounds(80, 120, 650, 700);
		frame.add(showArea);
		sure.addActionListener(this);
		sure.setActionCommand("sure");
		showArea.setEditable(false);
		export.setBounds(670, 50, 100, 40);
		frame.add(export);
		export.addActionListener(this);
		export.setActionCommand("export");
	}

	private static DatePicker getDatePicker() {
		final DatePicker datepick;
		// ��ʽ
		String DefaultFormat = "yyyy-MM-dd";
		// ��ǰʱ��
		Date date = new Date();
		// ����
		Font font = new Font("Times New Roman", Font.BOLD, 14);

		Dimension dimension = new Dimension(177, 24);

		int[] hilightDays = { 1, 3, 5, 7 };

		int[] disabledDays = { 4, 6, 5, 9 };
		// ���췽������ʼʱ�䣬ʱ����ʾ��ʽ�����壬�ؼ���С��
		datepick = new DatePicker(date, DefaultFormat, font, dimension);

		datepick.setLocation(137, 83);// ������ʼλ��
		/*
		 * //Ҳ����setBounds()ֱ�����ô�С��λ�� datepick.setBounds(137, 83, 177, 24);
		 */
		// ����һ���·�����Ҫ������ʾ������
		datepick.setHightlightdays(hilightDays, Color.red);
		// ����һ���·��в���Ҫ�����ӣ��ʻ�ɫ��ʾ
		datepick.setDisableddays(disabledDays);
		// ���ù���
		datepick.setLocale(Locale.CHINA);
		// ����ʱ�����ɼ�
		// datepick.setTimePanleVisible(true);
		return datepick;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getActionCommand().equals("sure")) {
			showArea.setText("");
			//System.out.println(dp.getValue());
			//System.out.println(dp.getText());
			//System.out.println("1");
			String time = dp.getText();
			String time1 = dp1.getText();
			//2018-10-24
			time = time.replaceAll("-", "");
			time = time+"000000";
			time1 = time1.replaceAll("-", "");
			time1 = time1+"000000";
			List<String> list = new ArrayList<String>();
			list = connection.selectByDate(time, time1);
			showArea.append("���ƺ�"+"\t");
			showArea.append("ͣ��ʱ��"+"\t\t");
			showArea.append("ȡ��ʱ��"+"\t\t");
			showArea.append("�շ�"+"(Ԫ)"+"\t");
			showArea.append("\r\n");
			for(int i = 0;i<list.size();i++) {
				String[] temp = list.get(i).split("split");
				Record r = new Record();
				r.setCarNumber(temp[0]);
				r.setStartTime(temp[1]);
				r.setEndTime(temp[2]);
				r.setFee(Double.parseDouble(temp[3]));
				rList.add(r);
				System.out.println(temp[1]);
				for(int j = 0;j<temp.length;j++) {
					if(j==1||j==2) {
						temp[j] = Utils.cutString(temp[j]);
					}
					showArea.append(temp[j]);
					showArea.append("\t");
				}
				showArea.append("\r\n");
			} 
		} else if(arg0.getActionCommand().equals("export")) {
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