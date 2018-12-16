
package cn.chd.frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.bytedeco.javacpp.opencv_imgcodecs;
import org.bytedeco.javacpp.opencv_core.Mat;

import cn.chd.recognition.Distinguish;
import cn.chd.recognition.Recognition;

/**
 * 创建时间：2018年10月8日 下午7:34:38
 * 
 * 项目名称：Depot
 * 
 * @author homrls
 * @version 1.0
 * @since JDK 1.8.0_21 文件名称：RecognitionFrame.java 类说明： 主界面
 */

public class RecognitionFrame implements ActionListener {
	private boolean flag = false;
	private Recognition recognition;
	private String carNumber = new String();
	private String carNumber1 = new String();
	private Mat image = new Mat();
	private Distinguish distinguish = new Distinguish();
	private String path = new String();
	private JFrame frame;
	// ok按钮
	private JButton sure = new JButton("执行");
	// 显示图片的label
	private JLabel label = new JLabel();
	// 文件选择器
	private JFileChooser chooser;
	// 设置收费标准的label
	private JLabel label1 = new JLabel();
	// 显示信息的label
	private JLabel label2 = new JLabel();
	// 提示消息
	private JLabel[] notice = { new JLabel("入库"), new JLabel("出库"), new JLabel("手动校正无误后确认：") };
	private JLabel input = new JLabel("输入费率：");
	private JLabel input1 = new JLabel("（元/小时）");
//	private JButton bt_input = new JButton("xx");
	// 输入费率的text框
	private JTextField jt_input = new JTextField();
	private JButton bt_change = new JButton("采用");
	private JButton bt_change1 = new JButton("采用");
	private JTextField jt_ensure = new JTextField();
	private JTextField jt_ensure1 = new JTextField();

	private JLabel l1 = new JLabel("车牌号为:");
	private JLabel r1 = new JLabel("车牌号为:");
	private JLabel l2 = new JLabel("状态为：");
	private JLabel r2 = new JLabel("状态为：");

	// private JLabel lf = new JLabel("费用为:");
	public RecognitionFrame() {
		frame = new JFrame("识别");
		frame.setLayout(null);
		frame.setSize(800, 900);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		label.setBounds(30, 30, 400, 400);
		frame.add(label);
		sure.setBounds(30, 780, 100, 30);
		frame.add(sure);
		label1.setBounds(30, 510, 350, 240);
		label1.setBorder(BorderFactory.createLineBorder(Color.red));
		frame.add(label1);
		label2.setBounds(450, 250, 350, 200);
		Font font = new Font("宋体", Font.PLAIN, 18);
		bt_change.setBounds(320, 450, 100, 30);
		bt_change.addActionListener(this);
		bt_change.setActionCommand("change");
		frame.add(bt_change);
		bt_change1.setBounds(620, 450, 100, 30);
		bt_change1.addActionListener(this);
		bt_change1.setActionCommand("change1");
		frame.add(bt_change1);
		jt_input.setText("5");
		input.setBounds(410, 600, 100, 30);
		frame.add(input);
		jt_input.setBounds(480, 600, 100, 30);
		frame.add(jt_input);
		input1.setBounds(580, 600, 100, 30);
		frame.add(input1);

		l1.setBounds(40, 540, 220, 30);
		frame.add(l1);
		r1.setBounds(410, 540, 100, 30);
		frame.add(r1);
		l2.setBounds(40, 570, 220, 30);
		frame.add(l2);
		r2.setBounds(410, 570, 100, 30);
		frame.add(r2);
		// lf.setBounds(410, 600, 100, 30);

		jt_ensure.setBounds(210, 450, 100, 30);
		frame.add(jt_ensure);
		jt_ensure1.setBounds(490, 450, 100, 30);
		frame.add(jt_ensure1);
		notice[0].setBounds(40, 510, 50, 30);
		notice[1].setBounds(410, 510, 50, 30);
		notice[2].setBounds(30, 450, 180, 30);
		frame.add(notice[0]);
		frame.add(notice[1]);
		frame.add(notice[2]);
		// 下面设置一个块，边框是蓝的
		label2.setBounds(400, 510, 350, 240);
		label2.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		frame.add(label2);
		chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File("."));
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		JMenu menu = new JMenu("菜单");
		JMenu menu2 = new JMenu("查询");
		menuBar.add(menu);
		menuBar.add(menu2);
		JMenuItem openItem = new JMenuItem("打开图片");
		JMenuItem showBynumberItem = new JMenuItem("按车牌号查询");
		JMenuItem showByDateItem = new JMenuItem("按日期查询");
		JMenuItem showByCoastItem = new JMenuItem("按收费查询");
		menu.add(openItem);
		menu.addSeparator();
		menu2.add(showBynumberItem);
		menu2.add(showByDateItem);
		menu2.add(showByCoastItem);
		showBynumberItem.addActionListener(this);
		showByDateItem.addActionListener(this);
		showByCoastItem.addActionListener(this);
		openItem.addActionListener(this);
		openItem.setActionCommand("open");
		showBynumberItem.setActionCommand("showBynumber");
		showByDateItem.setActionCommand("showByDate");
		showByCoastItem.setActionCommand("showByCost");
		frame.setVisible(true);

		sure.addActionListener(this);
		sure.setActionCommand("sure");
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getActionCommand().equals("open")) {
			int result = chooser.showOpenDialog(null);
			if (result == JFileChooser.APPROVE_OPTION) {
				String name = chooser.getSelectedFile().getPath();
				System.out.println(name);
				image = opencv_imgcodecs.imread(name);
				File f = new File(name);
				path = name;
				try {
					// text1.setText("读 取 到 的 车 牌 号 为：");
					ImageIcon icon = new ImageIcon(ImageIO.read(new File(name)));
					icon.setImage(icon.getImage().getScaledInstance(400, 400, Image.SCALE_DEFAULT));
					label.setIcon(icon);
					carNumber = distinguish.DistinguishNumber(path, image);
					//////////////////////////////////////////////////////////
					carNumber1 = distinguish.DistinguishNumber2(path, image);
					// text1.append(carNumber);
					jt_ensure.setText(carNumber);
					jt_ensure1.setText(carNumber1);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else if (event.getActionCommand().equals("showBynumber")) {
			ShowByNumberFrame showFrame = new ShowByNumberFrame();
		} else if (event.getActionCommand().equals("showByDate")) {
			ShowByDateFrame showFrame = new ShowByDateFrame();
		} else if (event.getActionCommand().equals("showByCost")) {
			ShowByCostFrame showFrame = new ShowByCostFrame();
		} else if (event.getActionCommand().equals("sure")) {
			String temp = jt_input.getText();
			double fee = Double.parseDouble(temp);
			recognition = new Recognition(carNumber, fee);
			// 确定函数
			if (flag) {
				String n = recognition.goOut();
				JOptionPane.showMessageDialog(null, "收费" + n + "元", "notice", JOptionPane.INFORMATION_MESSAGE);
			} else {
				recognition.goIn();
				JOptionPane.showMessageDialog(null, "请入库", "notice", JOptionPane.INFORMATION_MESSAGE);
			}
		} else if (event.getActionCommand().equals("change")) {
			carNumber = jt_ensure.getText();
			recognition = new Recognition(carNumber, 0.0);
			System.out.println(recognition.checkTemp());
			// 存车false 取车true
			if (recognition.checkTemp()) {
				flag = true;
				notice[1].setEnabled(true);
				notice[0].setEnabled(false);
				r1.setEnabled(true);
				r2.setEnabled(true);
				r1.setText("车牌号为：" + carNumber);
				r2.setText("状态为：取车");
				// r1 = new JLabel("车牌号为："+carNumber);
				// r2 = new JLabel("状态为：取车");
				l1.setEnabled(false);
				l2.setEnabled(false);
				// lf.setEnabled(true);
			} else {
				flag = false;
				notice[0].setEnabled(true);
				notice[1].setEnabled(false);
				l1.setEnabled(true);
				l2.setEnabled(true);
				l1.setText("车牌号为：" + carNumber);
				l2.setText("状态为：存车");
				// l1 = new JLabel("车牌号为："+carNumber);
				// l2 = new JLabel("状态为：存车");
				r1.setEnabled(false);
				r2.setEnabled(false);
				// lf.setEnabled(false);
				input.setEnabled(false);
			}
		} else if (event.getActionCommand().equals("change1")) {
			carNumber = jt_ensure1.getText();
			recognition = new Recognition(carNumber, 0.0);
			System.out.println(recognition.checkTemp());
			// 存车false 取车true
			if (recognition.checkTemp()) {
				flag = true;
				notice[1].setEnabled(true);
				notice[0].setEnabled(false);
				r1.setEnabled(true);
				r2.setEnabled(true);
				r1.setText("车牌号为：" + carNumber);
				r2.setText("状态为：取车");
				// r1 = new JLabel("车牌号为："+carNumber);
				// r2 = new JLabel("状态为：取车");
				l1.setEnabled(false);
				l2.setEnabled(false);
				// lf.setEnabled(true);
			} else {
				flag = false;
				notice[0].setEnabled(true);
				notice[1].setEnabled(false);
				l1.setEnabled(true);
				l2.setEnabled(true);
				l1.setText("车牌号为：" + carNumber);
				l2.setText("状态为：存车");
				// l1 = new JLabel("车牌号为："+carNumber);
				// l2 = new JLabel("状态为：存车");
				r1.setEnabled(false);
				r2.setEnabled(false);
				// lf.setEnabled(false);
				input.setEnabled(false);
			}
		}
	}
}
