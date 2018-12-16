package cn.chd.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import cn.chd.utils.*;



/**
* ����ʱ�䣺2018��10��8�� ����4:40:39  

* ��Ŀ���ƣ�ͼ��ʶ��ͣ��ϵͳ  
* @author  ��Ӣ��
* @version 1.0   
* @since JDK 1.8.0_21  
* �ļ����ƣ�MyConnection.java  
* ��˵����  ��װ ��ɾ�Ĳ� ����
*/

public class MyConnection {
	//����������ݿ�������
	private JdbcUtils jdbc = new JdbcUtils();
	private Connection conn=null;
	private PreparedStatement st = null;
	private ResultSet rs = null;
	//private PreparedStatement st = null;
	/**
	 * ���췽������˽�еģ����ù���ģʽ������MyConnection����Щ�Ȳ���д�����ͳһ������д����ʵ�֣�
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
	 * ������һ�������һ�����ֺ��������ӣ����԰��������ʽд������ЩҪ����ĺ������Ѿ���������߼��滻һ�¾Ϳ���
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
				teacher.setTeacherfrom("xxѧԺ");
				teacher.setTeacherphone("131xxxxxxxx");
				teacher.setTeachersex("��");
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
	 *  ���������
	 * void  
	 * TODO ��ȡ����ʱ����ͣ��������� ���ƺš���ʼʱ�䡢����ʱ��
	 */
	public void insertIntoTemporary(String number,String startTime,String endTime) {
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		JdbcUtils jdbc = new JdbcUtils();
		try {
			conn = jdbc.getConection();
			if (conn == null) {
				System.out.println("�������ݿ�ʧ�ܣ�");
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
	 * ���������
	 * void
	 * TODO ��ͣ����ʱ����ͣ��������� ���ƺš���ʼʱ��                 //��ʱ��
	 */
	public void insertIntoTemporary(String number,String startTime) {
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		JdbcUtils jdbc = new JdbcUtils();
		try {
			conn = jdbc.getConection();
			if (conn == null) {
				System.out.println("�������ݿ�ʧ�ܣ�");
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
	 * ���������
	 * void
	 * TODO ��ȡ����ʱ����ͣ���ܱ������ ���ƺš���ʼ������������
	 */
	public void insertIntoForever(String number,String startTime,String endTime,double cost){
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		JdbcUtils jdbc = new JdbcUtils();
		try {
			conn = jdbc.getConection();
			if (conn == null) {
				System.out.println("�������ݿ�ʧ�ܣ�");
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
	 * ��ά�����
	 * void
	 * TODO ��ȡ�����Ժ��ͣ������Ķ�Ӧ����ɾ�������ݳ��ƺ�ɾ��
	 */
	public void deleteIntoTemporary(String number) {
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		JdbcUtils jdbc = new JdbcUtils();
		try {
			conn = jdbc.getConection();
			if (conn == null) {
				System.out.println("�������ݿ�ʧ�ܣ�");
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
	 * ��ά�����
	 * boolean   ������ڷ���true�������ڷ���false
	 * TODO  ����һ�����ƺ�ʱ����ͣ��������������ƺ��Ƿ���ڣ�����֤����ȡ����������֤����ͣ��
	 */
	public boolean isExistIntoTemporary(String number) {
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		JdbcUtils jdbc = new JdbcUtils();
		try {
			conn = jdbc.getConection();
			if (conn == null) {
				System.out.println("�������ݿ�ʧ�ܣ�");
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
	 * ��Ӣ�����
	 * List<String>  ���ز�ѯ����ͣ����¼����
	 * TODO  ��������ĳ��ƺţ���ͣ���ܱ��в�ѯ������ƺŵ�����ͣ����¼,���ֶ�֮���á�sqlit��������
	 * �磺����ֵΪ����A12G45sqlit20180101100000sqlit20180101120000split10����ʾ���س��ƺ�Ϊ����A12G45��ͣ��ʱ��Ϊ��20180101100000��
	 * ȡ��ʱ��Ϊ��20180101120000���շ�Ϊ��10��
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
			//�쳣
		}finally {
			//�쳣����
		}
		return res;
	}
	/**
	 * ��Ӣ�����
	 * List<String>
	 * TODO  �����������ʼʱ��ͽ���ʱ�䣬����ͣ����¼���飬��ʽͬ��
	 */
	public List<String> selectByDate(String start,String end){
		List<String> res=new LinkedList<String>();
		String sql="select * from park_forever where StartTime>=? and EndTime<=?;";//�ϸ��ڴ�ʱ��Σ�����and�Ƿ���Ը�Ϊor��
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
			//�쳣
		}finally {
			//�쳣����
		}
		return res;
	}
	/**
	 * ��Ӣ�����
	 * List<String>
	 * TODO  ��ѯ�����ڣ�down��up��֮���ͣ����¼����ʽͬ��
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
			//�쳣
		}finally {
			//�쳣����
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
			//�쳣
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
			s=test.selectByNumber("��A12G45");
			for(String temp:s){
				System.out.print(temp);
			}
		}catch (Exception e) {
			//�쳣
		}finally {
			//�쳣����
		}



	}
}
  


