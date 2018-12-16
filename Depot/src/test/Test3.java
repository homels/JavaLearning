
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
 * 创建时间：2018年10月28日 上午10:22:27
 * 
 * 项目名称：Depot
 * 
 * @author homrls
 * @version 1.0
 * @since JDK 1.8.0_21 文件名称：Test3.java 类说明：
 */

public class Test3 {
	public static void main(String[] args) throws RowsExceededException, WriteException, IOException {

		// 1. 导出Excel的路径
		String filePath = "C:\\Users\\Lenovo\\Desktop\\aa.xls";
		WritableWorkbook wwb = null;

		try {
			wwb = Workbook.createWorkbook(new File(filePath));
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 创建Excel表的"学生"区域的数据
		WritableSheet sheet = wwb.createSheet("学生", 0);// 或者rwb.getSheet(0)获取第一个区域
		try {
			// 2. 连接数据库的几行代码
			Connection con = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			String url = "jdbc:mysql://localhost:3306/ParkingChargeSystem?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT&useSSL=false";
			String sql = "select * from park_forever";
			con = DriverManager.getConnection(url, "root", "268514");
			ps = con.prepareStatement(sql);// SQL预处理
			rs = ps.executeQuery();
			// ResultSet是数据库中的数据，将其转换为List类型
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
				// Number对应数据库的int类型数据
				sheet.addCell(new Label(0, i, list.get(i).getCarNumber()));
				// Label对应数据库String类型数据
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
