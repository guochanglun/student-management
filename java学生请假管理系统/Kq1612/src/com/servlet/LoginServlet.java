package com.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.util.DBUtil;

public class LoginServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public LoginServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		String sql =  "select * from users where username='" + username
					+ "' and password='" + password + "' ";

		HttpSession session = request.getSession();
		System.out.println(sql);
		DBUtil util = new DBUtil();
		Connection conn = util.openConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			int index = 0;
			while (rs.next()) {
				session.setAttribute("id", rs.getInt("id"));
				session.setAttribute("yx", rs.getInt("yx"));
				session.setAttribute("bj", rs.getInt("bj"));
				session.setAttribute("realname", rs.getString("realname"));
				session.setAttribute("roles", rs.getString("roles"));
				index++;
				break;
			}
			if (index > 0) {
				response.sendRedirect("main.jsp");
			} else {
				String sql2 =  "select * from stu where username='" + username
				+ "' and password='" + password + "' ";

				PreparedStatement pstmt2 = conn.prepareStatement(sql2);
				ResultSet rs2 = pstmt2.executeQuery();
				while (rs2.next()) {
					session.setAttribute("id", rs2.getInt("id"));
					session.setAttribute("yx", rs2.getInt("yx"));
					session.setAttribute("bj", rs2.getInt("bj"));
					session.setAttribute("realname", rs2.getString("realname"));
					session.setAttribute("roles", rs2.getString("roles"));
					index++;
					break;
				}
				if (index > 0) {
					response.sendRedirect("main.jsp");
				} else {
					response.sendRedirect("login.jsp");
				}
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
