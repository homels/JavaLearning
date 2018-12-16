package cn.chd.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import cn.chd.utils.*;



/**
* 创建时间：2018年10月8日 下午4:40:39  

* 项目名称：图像识别停车系统  
* @author  李英豪
* @version 1.0   
* @since JDK 1.8.0_21  
* 文件名称：MyConnection.java  
* 类说明：  封装 增删改查 操作
*/

public class MyConnection {
	//这个类是数据库连接类
	private JdbcUtils jdbc = new JdbcUtils();
	private Connection conn=null;
	private PreparedStatement st = null;
	private ResultSet rs = null;
	//private PreparedStatement st = null;
	/**
	 * 构造方法做成私有的，利用工厂模式来生成MyConnection（这些先不用写，最后统一处理，先写下面实现）
	 */
	public MyConnection() {
		try{
			conn=jdbc.getConection();
		}catch (Exception e) {
			System.out.print("SQLException");
			jdbc.releace(conn, st, rs);
		} finally {
			//jdbc.releace(conn, st, rs);
		}
	}
	
	
	/*
	 * 这是上一个课设的一个这种函数的例子，可以按照这个形式写下面那些要补充的函数，把具体的语句和逻辑替换一下就可以
	public Teacher find(String teacherid, String password) {
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		JdbcUtils jdbc = new JdbcUtils();
		try {
			conn = jdbc.getConection();
			if (conn == null) {
				System.out.println("meiyoubbb");
			}
			String sql = "select * from teacher where ID=? and passwd=?;";
			st = conn.prepareStatement(sql);
			st.setString(1, teacherid);
			st.setString(2, password);
			rs = st.executeQuery();
			if (rs.next()) {
				Teacher teacher = new Teacher();
				teacher.setId(rs.getInt("ID"));
				teacher.setTeacherid(rs.getString("ID"));
				teacher.setTeachername(rs.getString("name"));
				teacher.setTeacherpassword(rs.getString("passwd"));
				teacher.setTeacherfrom("xx学院");
				teacher.setTeacherphone("131xxxxxxxx");
				teacher.setTeachersex("男");
				return teacher;
			}
			return null;
		} catch (Exception e) {
			throw new DaoException(e);
		} finally {
			jdbc.releace(conn, st, rs);
		}
	}
	**/
	/**
	 *  荣立宏完成
	 * void  
	 * TODO 在取车的时候向停车表里插入 车牌号、开始时间、结束时间
	 */
	public void insertIntoTemporary(String number,String startTime,String endTime) {
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		JdbcUtils jdbc = new JdbcUtils();
		try {
			conn = jdbc.getConection();
			if (conn == null) {
				System.out.println("连接数据库失败！");
			}
			String sql = "insert into park_temporary(CarNum,StartTime,EndTime) values(?,?,?)";
			st = conn.prepareStatement(sql);
			st.setString(1, number);
			st.setString(2, startTime);
			st.setString(3,endTime);
			int ac = st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbc.releace(conn, st, rs);
		}
	}
	/**
	 * 荣立宏完成
	 * void
	 * TODO 在停车的时候向停车表里插入 车牌号、开始时间                 //临时表
	 */
	public void insertIntoTemporary(String number,String startTime) {
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		JdbcUtils jdbc = new JdbcUtils();
		try {
			conn = jdbc.getConection();
			if (conn == null) {
				System.out.println("连接数据库失败！");
			}
			String sql = "insert into park_temporary(CarNum,StartTime) values(?,?)";
			st = conn.prepareStatement(sql);
			st.setString(1, number);
			st.setString(2, startTime);
			//st.setString(3,endTime);
			int ac = st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbc.releace(conn, st, rs);
		}
	}
	/**
	 * 荣立宏完成
	 * void
	 * TODO 在取车的时候向停车总表里插入 车牌号、开始、结束、费用
	 */
	public void insertIntoForever(String number,String startTime,String endTime,double cost){
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		JdbcUtils jdbc = new JdbcUtils();
		try {
			conn = jdbc.getConection();
			if (conn == null) {
				System.out.println("连接数据库失败！");
			}
			String sql = "insert into park_forever(CarNum,StartTime,EndTime,Fee) values(?,?,?,?)";
			st = conn.prepareStatement(sql);
			st.setString(1, number);
			st.setString(2, startTime);
			st.setString(3, endTime);
			st.setDouble(4, cost);
			int ac = st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbc.releace(conn, st, rs);
		}
	}
	/**
	 * 孔维健完成
	 * void
	 * TODO 在取车完以后把停车表里的对应数据删掉（根据车牌号删）
	 */
	public void deleteIntoTemporary(String number) {
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		JdbcUtils jdbc = new JdbcUtils();
		try {
			conn = jdbc.getConection();
			if (conn == null) {
				System.out.println("连接数据库失败！");
			}
			String sql = "delete from park_temporary where CarNum=?";
			st = conn.prepareStatement(sql);
			st.setString(1, number);
//			st.setString(2, startTime);
//			st.setString(3,endTime);
			int ac = st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbc.releace(conn, st, rs);
		}
	}
	/**
	 * 孔维健完成
	 * boolean   表里存在返回true，不存在返回false
	 * TODO  读到一个车牌号时，在停车表里检查这个车牌号是否存在，存在证明是取车，不存在证明是停车
	 */
	public boolean isExistIntoTemporary(String number) {
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		JdbcUtils jdbc = new JdbcUtils();
		try {
			conn = jdbc.getConection();
			if (conn == null) {
				System.out.println("连接数据库失败！");
			}
			String sql = "select * from park_temporary where CarNum=?";
			st = conn.prepareStatement(sql);
			st.setString(1, number);
			rs = st.executeQuery();
			if(rs.next())
				return true;
			else
				return false;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbc.releace(conn, st, rs);
		}
		return false;
	}

	/**
	 * 李英豪完成
	 * List<String>  返回查询到的停车记录数组
	 * TODO  根据输入的车牌号，在停车总表中查询这个车牌号的所有停车记录,各字段之间用“sqlit”隔开，
	 * 如：返回值为“陕A12G45sqlit20180101100000sqlit20180101120000split10”表示返回车牌号为“陕A12G45”停车时间为“20180101100000”
	 * 取车时间为“20180101120000”收费为“10”
	 */
	public List<String> selectByNumber(String number) {
		List<String> res=new LinkedList<String>();
		String sql="select * from park_forever where CarNum = ?";
		try{
			st = conn.prepareStatement(sql);
			st.setString(1, number);
			rs = st.executeQuery();
			while(rs.next()){
				String CarNum=rs.getString("CarNum");
				String StartTime=rs.getString("StartTime");
				String EneTime=rs.getString("EndTime");
				String Fee=rs.getString("Fee");
				String temp=CarNum+"split"+StartTime+"split"+EneTime+"split"+Fee;
				res.add(temp);
			}
		}catch (SQLException e) {
			//异常
		}finally {
			//异常处理
		}
		return res;
	}
	/**
	 * 李英豪完成
	 * List<String>
	 * TODO  根据输入的起始时间和结束时间，返回停车记录数组，格式同上
	 */
	public List<String> selectByDate(String start,String end){
		List<String> res=new LinkedList<String>();
		String sql="select * from park_forever where StartTime>=? and EndTime<=?;";//严格在此时间段？？？and是否可以改为or？
		try{
			st = conn.prepareStatement(sql);
			st.setString(1, start);
			st.setString(2, end);
			rs = st.executeQuery();
			while(rs.next()){
				String CarNum=rs.getString("CarNum");
				String StartTime=rs.getString("StartTime");
				String EneTime=rs.getString("EndTime");
				String Fee=rs.getString("Fee");
				String temp=CarNum+"split"+StartTime+"split"+EneTime+"split"+Fee;
				res.add(temp);
			}
		}catch (SQLException e) {
			//异常
		}finally {
			//异常处理
		}
		return res;
	}
	/**
	 * 李英豪完成
	 * List<String>
	 * TODO  查询费用在（down，up）之间的停车记录，格式同上
	 */
	public List<String> selectByCost(double down,double up){
		List<String> res=new LinkedList<String>();
		String sql="select * from park_forever where Fee>=? and Fee<=?;";
		try{
			st = conn.prepareStatement(sql);
			st.setDouble(1,down);
			st.setDouble(2, up);
			rs = st.executeQuery();
			while(rs.next()){
				String CarNum=rs.getString("CarNum");
				String StartTime=rs.getString("StartTime");
				String EneTime=rs.getString("EndTime");
				String Fee=rs.getString("Fee");
				String temp=CarNum+"split"+StartTime+"split"+EneTime+"split"+Fee;
				res.add(temp);
			}
		}catch (SQLException e) {
			//异常
		}finally {
			//异常处理
		}
		return res;
	}
	
	public String getStratTimeByTemporary(String number) {
		List<String> res=new LinkedList<String>();
		String sql="select * from park_temporary where CarNum = ?";
		try{
			st = conn.prepareStatement(sql);
			st.setString(1, number);
			rs = st.executeQuery();
			while(rs.next()){
				String StartTime=rs.getString("StartTime");
				res.add(StartTime);
			}
		}catch (SQLException e) {
			//异常
		}finally {
			
		}
		return res.get(0);
	}

//////test///////
	public static void main(String[] args){
		List<String> s=new LinkedList<String>();
		try{
			MyConnection test=new MyConnection();
			s=test.selectByCost(0,100);
			for(String temp:s){
				System.out.print(temp);
			}
			s=test.selectByDate("20180101100000","20180101200000");
			for(String temp:s){
				System.out.print(temp);
			}
			s=test.selectByNumber("陕A12G45");
			for(String temp:s){
				System.out.print(temp);
			}
		}catch (Exception e) {
			//异常
		}finally {
			//异常处理
		}



	}
}
  


