package com.song.c3p0.db.xml;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/** 
* @author : homels
* @date 创建时间：2018年5月25日 下午4:44:21
* @ClassName: C3P0XmlSimplifyMain
* @Description: TODO 
* @version 
*/
public class C3P0XmlSimplifyMain {
	 public static void main(String[] args) {
	        Connection conn = null;  
	        PreparedStatement ps = null;  
	        ResultSet rs = null;  
	        try {  
	            conn = C3P0XmlSimplify.getInstance().getConnection();  
	         //   String sql = "insert into user values(?,?)";
	            String sql1 = "SELECT * FROM user";
	       //     ps = conn.prepareStatement(sql);
	       //     ps.setInt(1, 2);
	      //      ps.setString(2, "b");
	      //      int result = ps.executeUpdate();
	      //      if(result>0){
	     //       	System.out.println("done");
	      //      }
	     //       else{
	      //      	System.out.println("no");
	      //      }
	     //       ps.close();
	            ps = conn.prepareStatement(sql1);
	            rs = ps.executeQuery();
	            while(rs.next()){
	            	String name = rs.getString("name");
	            	System.out.println(name);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();  
	        }finally{
	            C3P0XmlSimplify.releaseSources(conn, ps, rs);  
	        }  
	    }  
}
