package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	public void closeConn(Connection conn) {
		try {
//			System.out.println("关闭数据库连接");
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Connection openConnection() {
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/kq1612?useUnicode=true&characterEncoding=utf-8";
		String username = "root";
		String password = "gcl";
		try {
//			System.out.println("打开数据库连接");
			Class.forName(driver);
			return DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
