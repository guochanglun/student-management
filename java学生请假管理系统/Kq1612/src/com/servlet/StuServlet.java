package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.Stu;
import com.bean.Users;
import com.util.DBUtil;
import com.util.Page;

public class StuServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public StuServlet() {
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

		request.setCharacterEncoding("UTF-8");
		String mode = request.getParameter("mode");
		String pageNoStr = request.getParameter("pageNoStr") == null ? "1"
				: request.getParameter("pageNoStr");
		int m = 0;
		int n = 30;// 
		int totle = 0;
		int pageNo = Integer.parseInt(pageNoStr);
		if (pageNo == 1) {// 
		} else {
			m = (pageNo - 1) * n;
		}
		DBUtil util = new DBUtil();
		Connection conn = util.openConnection();
		Integer xy = (Integer) request.getSession().getAttribute("yx");
		Integer bbj = (Integer) request.getSession().getAttribute("bj");
		try {
			if (mode != null && mode.equals("list")) {
				String names = request.getParameter("names");
				String sql = " select * from stu where 1=1";
				String sql2 = "select count(*) from stu where 1=1 ";
				if (names != null && !names.equals("")) {
					sql += " and realname like '%" + names + "%'";
					sql2 += " and realname like '%" + names + "%'";
				}
				sql += " limit " + m + "," + n;
				System.out.println("-----------------  " + sql);
				System.out.println("++++++++++++++++++  " + sql2);
				PreparedStatement pstmt1 = conn.prepareStatement(sql);
				PreparedStatement pstmt2 = conn.prepareStatement(sql2);
				ResultSet rs = pstmt1.executeQuery();
				ResultSet rs2 = pstmt2.executeQuery();
				rs2.next();
				totle = rs2.getInt(1);
				int totlePage = totle / n;
				int totlePageY = totle % n;
				if (totlePageY != 0) {
					totlePage = totlePage + 1;
				}

				List<Stu> list = new ArrayList<Stu>();
				while (rs.next()) {
					
					Stu bean = new Stu();
					bean.setId(rs.getInt("id"));
					bean.setYx(rs.getInt("yx"));
					bean.setBj(rs.getInt("bj"));
					bean.setUsername(rs.getString("username"));
					bean.setPassword(rs.getString("password"));
					bean.setRealname(rs.getString("realname"));
					bean.setTel(rs.getString("tel"));
					bean.setRoles(rs.getString("roles"));
					list.add(bean);
				}
				Page page = new Page();
				page.setPageNo(pageNo);
				page.setTotlePage(totlePage);
				page.setTotle(totle);
				page.setList(list);
				request.setAttribute("page", page);
				request.getRequestDispatcher("stulist.jsp").forward(
						request, response);
			}
			if (mode != null && mode.equals("add")) {
				String yx = request.getParameter("yx");
				String bj = request.getParameter("bj");
				String username = request.getParameter("username");
				String password = request.getParameter("password");
				String realname = request.getParameter("realname");
				String tel = request.getParameter("tel");
				String roles = request.getParameter("roles");
				
				
				
				String sqlUpdate = " insert into stu(yx,bj,username,password,realname,tel,roles) values("+yx+","+bj
				+",'"+username+"','"+password+"','"+realname+"','"+tel+"','"+roles+"')";

				Statement stmt = conn.createStatement();
				stmt.executeUpdate(sqlUpdate);

				String sql = " select * from stu where 1=1 ";
				String sql2 = "select count(*) from stu ";
				sql += " limit " + m + "," + n;
				System.out.println("-----------------  " + sql);
				System.out.println("++++++++++++++++++  " + sql2);
				PreparedStatement pstmt1 = conn.prepareStatement(sql);
				PreparedStatement pstmt2 = conn.prepareStatement(sql2);
				ResultSet rs = pstmt1.executeQuery();
				ResultSet rs2 = pstmt2.executeQuery();
				rs2.next();
				totle = rs2.getInt(1);
				int totlePage = totle / n;
				int totlePageY = totle % n;
				if (totlePageY != 0) {
					totlePage = totlePage + 1;
				}

				List<Stu> list = new ArrayList<Stu>();
				while (rs.next()) {
					
					Stu bean = new Stu();
					bean.setId(rs.getInt("id"));
					bean.setYx(rs.getInt("yx"));
					bean.setBj(rs.getInt("bj"));
					bean.setUsername(rs.getString("username"));
					bean.setPassword(rs.getString("password"));
					bean.setRealname(rs.getString("realname"));
					bean.setTel(rs.getString("tel"));
					bean.setRoles(rs.getString("roles"));
					list.add(bean);
				}
				Page page = new Page();
				page.setPageNo(pageNo);
				page.setTotlePage(totlePage);
				page.setTotle(totle);
				page.setList(list);
				request.setAttribute("page", page);
				request.getRequestDispatcher("stulist.jsp").forward(
						request, response);

			}

			if (mode != null && mode.equals("deletes")) {

				String id = request.getParameter("id");

				String sqlDel = "delete from  stu where id=" + id;
				Statement stmt = conn.createStatement();
				stmt.executeUpdate(sqlDel);

				String sql = " select * from stu where 1=1 ";
				String sql2 = "select count(*) from stu ";
				sql += " limit " + m + "," + n;
				System.out.println("-----------------  " + sql);
				System.out.println("++++++++++++++++++  " + sql2);
				PreparedStatement pstmt1 = conn.prepareStatement(sql);
				PreparedStatement pstmt2 = conn.prepareStatement(sql2);
				ResultSet rs = pstmt1.executeQuery();
				ResultSet rs2 = pstmt2.executeQuery();
				rs2.next();
				totle = rs2.getInt(1);
				int totlePage = totle / n;
				int totlePageY = totle % n;
				if (totlePageY != 0) {
					totlePage = totlePage + 1;
				}

				List<Stu> list = new ArrayList<Stu>();
				while (rs.next()) {
					
					Stu bean = new Stu();
					bean.setId(rs.getInt("id"));
					bean.setYx(rs.getInt("yx"));
					bean.setBj(rs.getInt("bj"));
					bean.setUsername(rs.getString("username"));
					bean.setPassword(rs.getString("password"));
					bean.setRealname(rs.getString("realname"));
					bean.setTel(rs.getString("tel"));
					bean.setRoles(rs.getString("roles"));
					list.add(bean);
				}
				Page page = new Page();
				page.setPageNo(pageNo);
				page.setTotlePage(totlePage);
				page.setTotle(totle);
				page.setList(list);
				request.setAttribute("page", page);
				request.getRequestDispatcher("stulist.jsp").forward(
						request, response);
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
