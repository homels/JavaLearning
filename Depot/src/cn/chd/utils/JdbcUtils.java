package cn.chd.utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtils {

	public Connection getConection() throws SQLException {
		//System.out.println("得到链接");
		Connection conn = null;
		try {
		//	System.out.println("1");
			Object c = Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
//			if (c == null)
//				System.out.println("NULL");
//			else
//				System.out.println("NOT NULL" + c);
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ParkingChargeSystem?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT&useSSL=false&allowPublicKeyRetrieval=true",
					"root", "268514");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	public void releace(Connection conn, Statement st, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();

			}
		}
		if (st != null) {
			try {
				st.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
