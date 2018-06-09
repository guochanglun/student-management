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

import com.bean.Bj;
import com.bean.Yx;
import com.util.DBUtil;
import com.util.Page;

public class BjServlet extends HttpServlet {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor of the object.
	 */
	public BjServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
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
		try {
			if (mode != null && mode.equals("list")) {
				String names = request.getParameter("names");
				String sql = " select * from bj where 1=1 ";
				String sql2 = "select count(*) from bj ";
				if (names != null && !names.equals("")) {
					sql += " and names like '%" + names + "%'";
					sql2 += " where names like '%" + names + "%'";
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

				List<Bj> list = new ArrayList<Bj>();
				while (rs.next()) {
					Bj bean = new Bj();
					bean.setId(rs.getInt("id"));
					bean.setYxid(rs.getInt("yxid"));
					bean.setNames(rs.getString("names"));
					list.add(bean);
				}
				Page page = new Page();
				page.setPageNo(pageNo);
				page.setTotlePage(totlePage);
				page.setTotle(totle);
				page.setList(list);
				request.setAttribute("page", page);
				request.getRequestDispatcher("bjlist.jsp").forward(
						request, response);
			}
			if (mode != null && mode.equals("add")) {
				String names = request.getParameter("names");
				String yxid = request.getParameter("yxid");
				String sqlUpdate = " insert into bj(yxid,names) values("+yxid+",'"+names+"')";

				Statement stmt = conn.createStatement();
				stmt.executeUpdate(sqlUpdate);

				String sql = " select * from bj where 1=1 ";
				String sql2 = "select count(*) from bj ";
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

				List<Bj> list = new ArrayList<Bj>();
				while (rs.next()) {
					Bj bean = new Bj();
					bean.setId(rs.getInt("id"));
					bean.setYxid(rs.getInt("yxid"));
					bean.setNames(rs.getString("names"));
					list.add(bean);
				}
				Page page = new Page();
				page.setPageNo(pageNo);
				page.setTotlePage(totlePage);
				page.setTotle(totle);
				page.setList(list);
				request.setAttribute("page", page);
				request.getRequestDispatcher("bjlist.jsp").forward(
						request, response);

			}

			if (mode != null && mode.equals("deletes")) {

				String id = request.getParameter("id");

				String sqlDel = "delete from  bj where id=" + id;
				Statement stmt = conn.createStatement();
				stmt.executeUpdate(sqlDel);

				String sql = " select * from bj where 1=1 ";
				String sql2 = "select count(*) from bj ";
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

				List<Bj> list = new ArrayList<Bj>();
				while (rs.next()) {
					Bj bean = new Bj();
					bean.setId(rs.getInt("id"));
					bean.setYxid(rs.getInt("yxid"));
					bean.setNames(rs.getString("names"));
					list.add(bean);
				}
				Page page = new Page();
				page.setPageNo(pageNo);
				page.setTotlePage(totlePage);
				page.setTotle(totle);
				page.setList(list);
				request.setAttribute("page", page);
				request.getRequestDispatcher("bjlist.jsp").forward(
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
