package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.Qj;
import com.bean.Stu;
import com.util.DBUtil;
import com.util.Page;

/**
 * 获取请假列表
 * @author wzk
 *
 */
public class QjServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public QjServlet() {
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
		HttpSession session = request.getSession();
		String uid=session.getAttribute("id").toString();
		try {
			// 获取请假列表
			if (mode != null && mode.equals("list")) {
				String names = request.getParameter("names");
				String sql = "select * from qj where 1=1 and sid= "+uid;
				String sql2 = "select count(*) from qj where 1=1 and sid= "+uid;
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

				List<Qj> list = new ArrayList<Qj>();
				while (rs.next()) {
					
					Qj bean = new Qj();
					bean.setId(rs.getInt("id"));
					bean.setYx(rs.getInt("yx"));
					bean.setBj(rs.getInt("bj"));
					bean.setSid(rs.getInt("sid"));
					bean.setStates(rs.getString("states"));
					bean.setBtimes(rs.getString("btimes"));
					bean.setEtimes(rs.getString("etimes"));
					bean.setDescs(rs.getString("descs"));
					bean.setUrl(rs.getString("url"));
					bean.setS1(rs.getString("s1"));
					bean.setS2(rs.getString("s2"));
					bean.setS3(rs.getString("s3"));
					bean.setS4(rs.getString("s4"));
					bean.setResult(rs.getString("result"));
					list.add(bean);
				}
				Page page = new Page();
				page.setPageNo(pageNo);
				page.setTotlePage(totlePage);
				page.setTotle(totle);
				page.setList(list);
				request.setAttribute("page", page);
				request.getRequestDispatcher("qjlist.jsp").forward(
						request, response);
			}
			// 
			if (mode != null && mode.equals("slist1")) {
				String bjid=session.getAttribute("bj").toString();
				String names = request.getParameter("names");
				String sql = " select * from qj where 1=1 and s1='待审核' and bj="+bjid;
				String sql2 = "select count(*) from qj where 1=1 and s1='待审核' and bj="+bjid;
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

				List<Qj> list = new ArrayList<Qj>();
				while (rs.next()) {
					
					Qj bean = new Qj();
					bean.setId(rs.getInt("id"));
					bean.setYx(rs.getInt("yx"));
					bean.setBj(rs.getInt("bj"));
					bean.setSid(rs.getInt("sid"));
					bean.setStates(rs.getString("states"));
					bean.setBtimes(rs.getString("btimes"));
					bean.setEtimes(rs.getString("etimes"));
					bean.setDescs(rs.getString("descs"));
					bean.setUrl(rs.getString("url"));
					bean.setS1(rs.getString("s1"));
					bean.setS2(rs.getString("s2"));
					bean.setS3(rs.getString("s3"));
					bean.setS4(rs.getString("s4"));
					list.add(bean);
				}
				Page page = new Page();
				page.setPageNo(pageNo);
				page.setTotlePage(totlePage);
				page.setTotle(totle);
				page.setList(list);
				request.setAttribute("page", page);
				request.getRequestDispatcher("qjlist1.jsp").forward(
						request, response);
			}
			
			if (mode != null && mode.equals("sh1")) {
				String bjid=session.getAttribute("bj").toString();
				String id = request.getParameter("id");
				String states = request.getParameter("states");
				if(states.equals("1")){
					String sqlupdate = "update  qj set s1='通过' where id=" + id;
					Statement stmt = conn.createStatement();
					stmt.executeUpdate(sqlupdate);
				}else{
					String sqlupdate = "update  qj set s1='不通过' where id=" + id;
					Statement stmt = conn.createStatement();
					stmt.executeUpdate(sqlupdate);
				}
				
				
				String sql = " select * from qj where 1=1 and s1='待审核' and bj="+bjid;
				String sql2 = "select count(*) from qj where 1=1 and s1='待审核' and bj="+bjid;
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

				List<Qj> list = new ArrayList<Qj>();
				while (rs.next()) {
					
					Qj bean = new Qj();
					bean.setId(rs.getInt("id"));
					bean.setYx(rs.getInt("yx"));
					bean.setBj(rs.getInt("bj"));
					bean.setSid(rs.getInt("sid"));
					bean.setStates(rs.getString("states"));
					bean.setBtimes(rs.getString("btimes"));
					bean.setEtimes(rs.getString("etimes"));
					bean.setDescs(rs.getString("descs"));
					bean.setUrl(rs.getString("url"));
					bean.setS1(rs.getString("s1"));
					bean.setS2(rs.getString("s2"));
					bean.setS3(rs.getString("s3"));
					bean.setS4(rs.getString("s4"));
					list.add(bean);
				}
				Page page = new Page();
				page.setPageNo(pageNo);
				page.setTotlePage(totlePage);
				page.setTotle(totle);
				page.setList(list);
				request.setAttribute("page", page);
				request.getRequestDispatcher("qjlist1.jsp").forward(
						request, response);
			}
			
			if (mode != null && mode.equals("slist2")) {
				String bjid=session.getAttribute("bj").toString();
				String names = request.getParameter("names");
				String sql = " select * from qj where 1=1 and s1='通过' and s2='待审核' and bj="+bjid;
				String sql2 = "select count(*) from qj  where 1=1 and s1='通过' and s2='待审核' and bj="+bjid;
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

				List<Qj> list = new ArrayList<Qj>();
				while (rs.next()) {
					
					Qj bean = new Qj();
					bean.setId(rs.getInt("id"));
					bean.setYx(rs.getInt("yx"));
					bean.setBj(rs.getInt("bj"));
					bean.setSid(rs.getInt("sid"));
					bean.setStates(rs.getString("states"));
					bean.setBtimes(rs.getString("btimes"));
					bean.setEtimes(rs.getString("etimes"));
					bean.setDescs(rs.getString("descs"));
					bean.setUrl(rs.getString("url"));
					bean.setS1(rs.getString("s1"));
					bean.setS2(rs.getString("s2"));
					bean.setS3(rs.getString("s3"));
					bean.setS4(rs.getString("s4"));
					list.add(bean);
				}
				Page page = new Page();
				page.setPageNo(pageNo);
				page.setTotlePage(totlePage);
				page.setTotle(totle);
				page.setList(list);
				request.setAttribute("page", page);
				request.getRequestDispatcher("qjlist2.jsp").forward(
						request, response);
			}
			
			if (mode != null && mode.equals("result")) {
				String bjid=session.getAttribute("bj").toString();
				String names = request.getParameter("names");
				String sql = " select * from qj where 1=1 and s1='通过' and s2='通过' and s3='通过' and s4='通过' and bj="+bjid;
				String sql2 = "select count(*) from qj  where 1=1 and s1='通过' and s2='通过' and s3='通过' and s4='通过' and bj="+bjid;
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

				List<Qj> list = new ArrayList<Qj>();
				while (rs.next()) {
					
					Qj bean = new Qj();
					bean.setId(rs.getInt("id"));
					bean.setYx(rs.getInt("yx"));
					bean.setBj(rs.getInt("bj"));
					bean.setSid(rs.getInt("sid"));
					bean.setStates(rs.getString("states"));
					bean.setBtimes(rs.getString("btimes"));
					bean.setEtimes(rs.getString("etimes"));
					bean.setDescs(rs.getString("descs"));
					bean.setUrl(rs.getString("url"));
					bean.setS1(rs.getString("s1"));
					bean.setS2(rs.getString("s2"));
					bean.setS3(rs.getString("s3"));
					bean.setS4(rs.getString("s4"));
					bean.setResult(rs.getString("result"));
					list.add(bean);
				}
				Page page = new Page();
				page.setPageNo(pageNo);
				page.setTotlePage(totlePage);
				page.setTotle(totle);
				page.setList(list);
				request.setAttribute("page", page);
				request.getRequestDispatcher("qjresult.jsp").forward(
						request, response);
			}
			
			if (mode != null && mode.equals("sh2")) {
				String bjid=session.getAttribute("bj").toString();
				String id = request.getParameter("id");
				String states = request.getParameter("states");
				if(states.equals("1")){
					String sqlupdate = "update  qj set s2='通过' where id=" + id;
					Statement stmt = conn.createStatement();
					stmt.executeUpdate(sqlupdate);
				}else{
					String sqlupdate = "update  qj set s2='不通过' where id=" + id;
					Statement stmt = conn.createStatement();
					stmt.executeUpdate(sqlupdate);
				}
				
				
				String sql = "select * from qj where 1=1 and s1='通过'  and s2='待审核' and bj="+bjid;
				String sql2 = "select count(*) from qj where 1=1 and s1='通过'  and s2='待审核' and bj="+bjid;
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

				List<Qj> list = new ArrayList<Qj>();
				while (rs.next()) {
					
					Qj bean = new Qj();
					bean.setId(rs.getInt("id"));
					bean.setYx(rs.getInt("yx"));
					bean.setBj(rs.getInt("bj"));
					bean.setSid(rs.getInt("sid"));
					bean.setStates(rs.getString("states"));
					bean.setBtimes(rs.getString("btimes"));
					bean.setEtimes(rs.getString("etimes"));
					bean.setDescs(rs.getString("descs"));
					bean.setUrl(rs.getString("url"));
					bean.setS1(rs.getString("s1"));
					bean.setS2(rs.getString("s2"));
					bean.setS3(rs.getString("s3"));
					bean.setS4(rs.getString("s4"));
					list.add(bean);
				}
				Page page = new Page();
				page.setPageNo(pageNo);
				page.setTotlePage(totlePage);
				page.setTotle(totle);
				page.setList(list);
				request.setAttribute("page", page);
				request.getRequestDispatcher("qjlist2.jsp").forward(
						request, response);
			}
			
			
			if (mode != null && mode.equals("slist3")) {
				String bjid=session.getAttribute("bj").toString();
				String names = request.getParameter("names");
				String sql = " select * from qj where 1=1 and s1='通过' and s2='通过' and s3='待审核' and bj="+bjid;
				String sql2 = "select count(*) from qj where 1=1 and s1='通过' and s2='通过' and s3='待审核' and bj="+bjid;
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

				List<Qj> list = new ArrayList<Qj>();
				while (rs.next()) {
					
					Qj bean = new Qj();
					bean.setId(rs.getInt("id"));
					bean.setYx(rs.getInt("yx"));
					bean.setBj(rs.getInt("bj"));
					bean.setSid(rs.getInt("sid"));
					bean.setStates(rs.getString("states"));
					bean.setBtimes(rs.getString("btimes"));
					bean.setEtimes(rs.getString("etimes"));
					bean.setDescs(rs.getString("descs"));
					bean.setUrl(rs.getString("url"));
					bean.setS1(rs.getString("s1"));
					bean.setS2(rs.getString("s2"));
					bean.setS3(rs.getString("s3"));
					bean.setS4(rs.getString("s4"));
					list.add(bean);
				}
				Page page = new Page();
				page.setPageNo(pageNo);
				page.setTotlePage(totlePage);
				page.setTotle(totle);
				page.setList(list);
				request.setAttribute("page", page);
				request.getRequestDispatcher("qjlist3.jsp").forward(
						request, response);
			}
			
			if (mode != null && mode.equals("sh3")) {
				String bjid=session.getAttribute("bj").toString();
				String id = request.getParameter("id");
				String states = request.getParameter("states");
				if(states.equals("1")){
					String sqlupdate = "update  qj set s3='通过' where id=" + id;
					Statement stmt = conn.createStatement();
					stmt.executeUpdate(sqlupdate);
				}else{
					String sqlupdate = "update  qj set s3='不通过' where id=" + id;
					Statement stmt = conn.createStatement();
					stmt.executeUpdate(sqlupdate);
				}
				
				
				String sql = " select * from qj where 1=1 and s1='通过' and s2='通过'  and s3='待审核' and bj="+bjid;
				String sql2 = "select count(*) from qj where 1=1 and s1='通过' and s2='通过'  and s3='待审核' and bj="+bjid;
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

				List<Qj> list = new ArrayList<Qj>();
				while (rs.next()) {
					
					Qj bean = new Qj();
					bean.setId(rs.getInt("id"));
					bean.setYx(rs.getInt("yx"));
					bean.setBj(rs.getInt("bj"));
					bean.setSid(rs.getInt("sid"));
					bean.setStates(rs.getString("states"));
					bean.setBtimes(rs.getString("btimes"));
					bean.setEtimes(rs.getString("etimes"));
					bean.setDescs(rs.getString("descs"));
					bean.setUrl(rs.getString("url"));
					bean.setS1(rs.getString("s1"));
					bean.setS2(rs.getString("s2"));
					bean.setS3(rs.getString("s3"));
					bean.setS4(rs.getString("s4"));
					list.add(bean);
				}
				Page page = new Page();
				page.setPageNo(pageNo);
				page.setTotlePage(totlePage);
				page.setTotle(totle);
				page.setList(list);
				request.setAttribute("page", page);
				request.getRequestDispatcher("qjlist3.jsp").forward(
						request, response);
			}
			
			if (mode != null && mode.equals("slist4")) {
				String yxid=session.getAttribute("yx").toString();
				String names = request.getParameter("names");
				String sql = "select * from qj where 1=1 and s1='通过' and s2='通过' and s3='通过' and s4='待审核' and yx="+yxid;
				String sql2 = "select count(*) from qj where 1=1 and s1='通过' and s2='通过' and s3='通过' and s4='待审核' and yx="+yxid;
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

				List<Qj> list = new ArrayList<Qj>();
				while (rs.next()) {
					
					Qj bean = new Qj();
					bean.setId(rs.getInt("id"));
					bean.setYx(rs.getInt("yx"));
					bean.setBj(rs.getInt("bj"));
					bean.setSid(rs.getInt("sid"));
					bean.setStates(rs.getString("states"));
					bean.setBtimes(rs.getString("btimes"));
					bean.setEtimes(rs.getString("etimes"));
					bean.setDescs(rs.getString("descs"));
					bean.setUrl(rs.getString("url"));
					bean.setS1(rs.getString("s1"));
					bean.setS2(rs.getString("s2"));
					bean.setS3(rs.getString("s3"));
					bean.setS4(rs.getString("s4"));
					list.add(bean);
				}
				Page page = new Page();
				page.setPageNo(pageNo);
				page.setTotlePage(totlePage);
				page.setTotle(totle);
				page.setList(list);
				request.setAttribute("page", page);
				request.getRequestDispatcher("qjlist4.jsp").forward(
						request, response);
			}

			if (mode != null && mode.equals("sh4")) {
				String id = request.getParameter("id");
				String states = request.getParameter("states");
				if(states.equals("1")){
					String sqlupdate = "update  qj set states='通过', s4='通过' where id=" + id;
					Statement stmt = conn.createStatement();
					stmt.executeUpdate(sqlupdate);
				}else{
					String sqlupdate = "update  qj set  states='不通过', s4='不通过' where id=" + id;
					Statement stmt = conn.createStatement();
					stmt.executeUpdate(sqlupdate);
				}
				
				
				String yxid=session.getAttribute("yx").toString();
				String names = request.getParameter("names");
				String sql = " select * from qj where 1=1 and s1='通过' and s2='通过' and s3='通过' and s4='待审核' and yx="+yxid;
				String sql2 = "select count(*) from qj where 1=1 and s1='通过' and s2='通过' and s3='通过' and s4='待审核' and yx="+yxid;
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

				List<Qj> list = new ArrayList<Qj>();
				while (rs.next()) {
					
					Qj bean = new Qj();
					bean.setId(rs.getInt("id"));
					bean.setYx(rs.getInt("yx"));
					bean.setBj(rs.getInt("bj"));
					bean.setSid(rs.getInt("sid"));
					bean.setStates(rs.getString("states"));
					bean.setBtimes(rs.getString("btimes"));
					bean.setEtimes(rs.getString("etimes"));
					bean.setDescs(rs.getString("descs"));
					bean.setUrl(rs.getString("url"));
					bean.setS1(rs.getString("s1"));
					bean.setS2(rs.getString("s2"));
					bean.setS3(rs.getString("s3"));
					bean.setS4(rs.getString("s4"));
					list.add(bean);
				}
				Page page = new Page();
				page.setPageNo(pageNo);
				page.setTotlePage(totlePage);
				page.setTotle(totle);
				page.setList(list);
				request.setAttribute("page", page);
				request.getRequestDispatcher("qjlist4.jsp").forward(
						request, response);
			}
			
			if (mode != null && mode.equals("deletes")) {

				String id = request.getParameter("id");

				String sqlDel = "delete from  qj where id=" + id;
				Statement stmt = conn.createStatement();
				stmt.executeUpdate(sqlDel);
				String sql = " select * from qj where 1=1 and sid= "+uid;
				String sql2 = "select count(*) from qj where 1=1 and sid= "+uid;
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

				List<Qj> list = new ArrayList<Qj>();
				while (rs.next()) {
					
					Qj bean = new Qj();
					bean.setId(rs.getInt("id"));
					bean.setYx(rs.getInt("yx"));
					bean.setBj(rs.getInt("bj"));
					bean.setSid(rs.getInt("sid"));
					bean.setStates(rs.getString("states"));
					bean.setBtimes(rs.getString("btimes"));
					bean.setEtimes(rs.getString("etimes"));
					bean.setDescs(rs.getString("descs"));
					bean.setUrl(rs.getString("url"));
					bean.setS1(rs.getString("s1"));
					bean.setS2(rs.getString("s2"));
					bean.setS3(rs.getString("s3"));
					bean.setS4(rs.getString("s4"));
					list.add(bean);
				}
				Page page = new Page();
				page.setPageNo(pageNo);
				page.setTotlePage(totlePage);
				page.setTotle(totle);
				page.setList(list);
				request.setAttribute("page", page);
				request.getRequestDispatcher("qjlist.jsp").forward(
						request, response);
			}
			
			if (mode != null && mode.equals("cancel")) {

				String id = request.getParameter("id");

				String sqlDel = "update qj set result='已撤销' where id=" + id;
				Statement stmt = conn.createStatement();
				stmt.executeUpdate(sqlDel);
				String sql = " select * from qj where 1=1 and sid= "+uid;
				String sql2 = "select count(*) from qj where 1=1 and sid= "+uid;
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

				List<Qj> list = new ArrayList<Qj>();
				while (rs.next()) {
					
					Qj bean = new Qj();
					bean.setId(rs.getInt("id"));
					bean.setYx(rs.getInt("yx"));
					bean.setBj(rs.getInt("bj"));
					bean.setSid(rs.getInt("sid"));
					bean.setStates(rs.getString("states"));
					bean.setBtimes(rs.getString("btimes"));
					bean.setEtimes(rs.getString("etimes"));
					bean.setDescs(rs.getString("descs"));
					bean.setUrl(rs.getString("url"));
					bean.setS1(rs.getString("s1"));
					bean.setS2(rs.getString("s2"));
					bean.setS3(rs.getString("s3"));
					bean.setS4(rs.getString("s4"));
					bean.setResult(rs.getString("result"));
					list.add(bean);
				}
				Page page = new Page();
				page.setPageNo(pageNo);
				page.setTotlePage(totlePage);
				page.setTotle(totle);
				page.setList(list);
				request.setAttribute("page", page);
				request.getRequestDispatcher("qjlist.jsp").forward(
						request, response);
			}
			
			if (mode != null && mode.equals("tj2")) {
				String btimes = request.getParameter("btimes")==null?"":request.getParameter("btimes");
				String etimes = request.getParameter("etimes")==null?"":request.getParameter("etimes");
				long days=1;
				 DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				 try
				 {
				   Date d1 = df.parse(btimes);
				   Date d2 = df.parse(etimes);
				   long diff = d2.getTime() - d1.getTime();//这样得到的差值是微秒级别
				    days += diff / (1000 * 60 * 60 * 24); 
				 }
				 catch (Exception e)
				 {
				 } 
				String sql = " select sid,count(1) yx from qj where 1=1 and btimes>='"
					+btimes+"' and btimes<='"+etimes+"' group by sid";
				System.out.println("-----------------  " + sql);
				System.out.println("-------days----------  " + days);
				PreparedStatement pstmt1 = conn.prepareStatement(sql);
				ResultSet rs = pstmt1.executeQuery();

				List<Qj> list = new ArrayList<Qj>();
				while (rs.next()) {
					
					Qj bean = new Qj();
					int nums=rs.getInt("yx");
					bean.setYx(nums);
					bean.setSid(rs.getInt("sid"));
					
					
			  
			        // 创建一个数值格式化对象  
			  
			        NumberFormat numberFormat = NumberFormat.getInstance();  
			  
			        // 设置精确到小数点后2位  
			  
			        numberFormat.setMaximumFractionDigits(2);  
			  
			        String result = numberFormat.format((float) nums / (float) days * 100);  
			  
			        System.out.println("num1和num2的百分比为:" + result + "%"); 
					
			        bean.setS1(result+"");
					System.out.println("-------result----------  " + result);
					list.add(bean);
				}
				request.setAttribute("list", list);
				request.getRequestDispatcher("qjtj2.jsp").forward(
						request, response);
			}
			if (mode != null && mode.equals("tj")) {

				String sql = " select sid,count(1) yx from qj where 1=1 group by sid";
				System.out.println("-----------------  " + sql);
				PreparedStatement pstmt1 = conn.prepareStatement(sql);
				ResultSet rs = pstmt1.executeQuery();

				List<Qj> list = new ArrayList<Qj>();
				while (rs.next()) {
					
					Qj bean = new Qj();
					bean.setYx(rs.getInt("yx"));
					bean.setSid(rs.getInt("sid"));
					list.add(bean);
				}
				request.setAttribute("list", list);
				request.getRequestDispatcher("qjtj.jsp").forward(
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
