
package test;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

/**
 * ����ʱ�䣺2018��10��28�� ����10:22:27
 * 
 * ��Ŀ���ƣ�Depot
 * 
 * @author homrls
 * @version 1.0
 * @since JDK 1.8.0_21 �ļ����ƣ�Test3.java ��˵����
 */

public class Test3 {
	public static void main(String[] args) throws RowsExceededException, WriteException, IOException {

		// 1. ����Excel��·��
		String filePath = "C:\\Users\\Lenovo\\Desktop\\aa.xls";
		WritableWorkbook wwb = null;

		try {
			wwb = Workbook.createWorkbook(new File(filePath));
		} catch (Exception e) {
			e.printStackTrace();
		}

		// ����Excel���"ѧ��"���������
		WritableSheet sheet = wwb.createSheet("ѧ��", 0);// ����rwb.getSheet(0)��ȡ��һ������
		try {
			// 2. �������ݿ�ļ��д���
			Connection con = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			String url = "jdbc:mysql://localhost:3306/ParkingChargeSystem?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT&useSSL=false";
			String sql = "select * from park_forever";
			con = DriverManager.getConnection(url, "root", "268514");
			ps = con.prepareStatement(sql);// SQLԤ����
			rs = ps.executeQuery();
			// ResultSet�����ݿ��е����ݣ�����ת��ΪList����
			List<Record> list = new ArrayList<Record>();
			while (rs.next()) {
				Record stu = new Record();
				stu.setCarNumber(rs.getString("carNum"));
				stu.setStartTime(rs.getString("startTime"));
				stu.setEndTime(rs.getString("EndTime"));
				stu.setFee(rs.getDouble("Fee"));
				list.add(stu);
			}
			ps.close();
			con.close();
			for (int i = 0; i < list.size(); i++) {
				// Number��Ӧ���ݿ��int��������
				sheet.addCell(new Label(0, i, list.get(i).getCarNumber()));
				// Label��Ӧ���ݿ�String��������
				sheet.addCell(new Label(1, i, list.get(i).getStartTime()));
				sheet.addCell(new Label(2, i, list.get(i).getEndTime()));
			}
			wwb.write();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			wwb.close();
		}

	}

}
