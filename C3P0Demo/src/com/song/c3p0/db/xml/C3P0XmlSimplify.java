package com.song.c3p0.db.xml;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/** 
* @author : homels
* @date 创建时间：2018年5月25日 下午4:43:07
* @ClassName: C3P0XmlSimplify
* @Description: TODO 
* @version 
*/
public class C3P0XmlSimplify {
	private static C3P0XmlSimplify c3P0XmlSimplify;

	private ComboPooledDataSource cpds;
	
	static{
		c3P0XmlSimplify = new C3P0XmlSimplify();
	}
	public C3P0XmlSimplify() {
		cpds = new ComboPooledDataSource("mysqlConnection");
	}
	public static C3P0XmlSimplify getInstance(){
		return c3P0XmlSimplify;
	}
	
	public Connection getConnection(){
		Connection conn = null;
		try {
			conn = cpds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	public static void releaseSources(Connection conn, PreparedStatement ps, ResultSet rs){
		if(null != rs){
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if(null != ps){
			try {
				ps.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(null != conn){
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
