
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
 * 创建时间：2018年10月11日 下午4:32:18
 * 
 * 项目名称：Depot
 * 
 * @author homrls
 * @version 1.0
 * @since JDK 1.8.0_21 文件名称：ShowByDateFrame.java 类说明：
 */

public class ShowByDateFrame implements ActionListener {
	private List<Record> rList = new ArrayList<Record>();
	private JButton export = new JButton("导出");
	private MyConnection connection = new MyConnection();
	private JFrame frame;
	private DatePicker dp = new DatePicker();
	private DatePicker dp1 = new DatePicker();
	// private JTextField date1 = new JTextField();
	private JLabel text = new JLabel("选择查询日期");
	private JButton sure = new JButton("查询");
	// 显示查询结果
	private JTextArea showArea = new JTextArea();

	public ShowByDateFrame() {
		frame = new JFrame("汇总信息");
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
		// 格式
		String DefaultFormat = "yyyy-MM-dd";
		// 当前时间
		Date date = new Date();
		// 字体
		Font font = new Font("Times New Roman", Font.BOLD, 14);

		Dimension dimension = new Dimension(177, 24);

		int[] hilightDays = { 1, 3, 5, 7 };

		int[] disabledDays = { 4, 6, 5, 9 };
		// 构造方法（初始时间，时间显示格式，字体，控件大小）
		datepick = new DatePicker(date, DefaultFormat, font, dimension);

		datepick.setLocation(137, 83);// 设置起始位置
		/*
		 * //也可用setBounds()直接设置大小与位置 datepick.setBounds(137, 83, 177, 24);
		 */
		// 设置一个月份中需要高亮显示的日子
		datepick.setHightlightdays(hilightDays, Color.red);
		// 设置一个月份中不需要的日子，呈灰色显示
		datepick.setDisableddays(disabledDays);
		// 设置国家
		datepick.setLocale(Locale.CHINA);
		// 设置时钟面板可见
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
			showArea.append("车牌号"+"\t");
			showArea.append("停车时间"+"\t\t");
			showArea.append("取车时间"+"\t\t");
			showArea.append("收费"+"(元)"+"\t");
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
			JOptionPane.showMessageDialog(null, "导出成功", "notice", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}