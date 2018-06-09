package com.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ValueBean {
	
	
	public static int getPageNum(){
		
		return 20;
	}
	public static String getValueByYxId(int id){
		DBUtil util= new DBUtil();
		Connection conn = util.openConnection();
		ResultSet rs = null;
		String sql="select names from yx where id="+id;
		String result="";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			result=rs.getString("names");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}
		return result;
	}
	
	public static String getBjNameById(int id){
		DBUtil util= new DBUtil();
		Connection conn = util.openConnection();
		ResultSet rs = null;
		String sql="select names from bj where id="+id;
		String result="";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			result=rs.getString("names");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}
		return result;
	}
	
	public static String getStuNameById(int id){
		DBUtil util= new DBUtil();
		Connection conn = util.openConnection();
		ResultSet rs = null;
		String sql="select realname from stu where id="+id;
		String result="";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			result=rs.getString("realname");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}
		return result;
	}
	
	public static String getStuNoById(int id){
		DBUtil util= new DBUtil();
		Connection conn = util.openConnection();
		ResultSet rs = null;
		String sql="select realname from stu where id="+id;
		String result="";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			result=rs.getString("realname");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}
		return result;
	}
	
	
	
}
