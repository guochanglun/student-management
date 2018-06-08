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

import com.bean.Bj;
import com.bean.Kq;
import com.bean.Qj;
import com.util.DBUtil;
import com.util.Page;

public class KqServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public KqServlet() {
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
			if (mode != null && mode.equals("list")) {
				
				String bjid=session.getAttribute("bj").toString();
				
				String sql = " select * from kq where 1=1 and bj="+bjid;
				String sql2 = "select count(*) from kq where 1=1 and bj="+bjid;
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

				List<Kq> list = new ArrayList<Kq>();
				while (rs.next()) {
					
					Kq bean = new Kq();
					bean.setId(rs.getInt("id"));
					bean.setYx(rs.getInt("yx"));
					bean.setBj(rs.getInt("bj"));
					bean.setSid(rs.getInt("sid"));
					bean.setTid(rs.getString("tid"));
					bean.setKc(rs.getString("kc"));
					bean.setStates(rs.getString("states"));
					bean.setTimes(rs.getString("times"));
					bean.setDescs(rs.getString("descs"));
					bean.setZj(rs.getString("zj"));
					list.add(bean);
				}
				Page page = new Page();
				page.setPageNo(pageNo);
				page.setTotlePage(totlePage);
				page.setTotle(totle);
				page.setList(list);
				request.setAttribute("page", page);
				request.getRequestDispatcher("kqlist.jsp").forward(
						request, response);
			}
			
			if (mode != null && mode.equals("add")) {
				
				String yxid=session.getAttribute("yx").toString();
				String bjid=session.getAttribute("bj").toString();
				
				
				
				String descs = request.getParameter("descs");
			
				String kc = request.getParameter("kc");
				String tid = request.getParameter("tid");
				String zj = request.getParameter("zj");
				String sid = request.getParameter("sid");
				String times = request.getParameter("times");
				String sqlUpdate = " insert into kq(yx,bj,sid,tid,kc,descs,times,zj,states) values("+yxid
				+","+bjid+","+sid+",'"+tid+"','"+kc+"','"+descs+"','"+times+"','"+zj+"','待审核')";

				Statement stmt = conn.createStatement();
				stmt.executeUpdate(sqlUpdate);

				
				
				String sql = " select * from kq where 1=1 and bj="+bjid;
				String sql2 = "select count(*) from kq where 1=1 and bj="+bjid;
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

				List<Kq> list = new ArrayList<Kq>();
				while (rs.next()) {
					
					Kq bean = new Kq();
					bean.setId(rs.getInt("id"));
					bean.setYx(rs.getInt("yx"));
					bean.setBj(rs.getInt("bj"));
					bean.setSid(rs.getInt("sid"));
					bean.setTid(rs.getString("tid"));
					bean.setKc(rs.getString("kc"));
					bean.setStates(rs.getString("states"));
					bean.setTimes(rs.getString("times"));
					bean.setDescs(rs.getString("descs"));
					bean.setZj(rs.getString("zj"));
					list.add(bean);
				}
				Page page = new Page();
				page.setPageNo(pageNo);
				page.setTotlePage(totlePage);
				page.setTotle(totle);
				page.setList(list);
				request.setAttribute("page", page);
				request.getRequestDispatcher("kqlist.jsp").forward(
						request, response);

			}

			
			if (mode != null && mode.equals("slist1")) {
				String bjid=session.getAttribute("bj").toString();
				String sql = " select * from kq where 1=1 and states='待审核' and bj="+bjid;
				String sql2 = "select count(*) from kq where 1=1 and states='待审核' and bj="+bjid;
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

				List<Kq> list = new ArrayList<Kq>();
				while (rs.next()) {
					
					Kq bean = new Kq();
					bean.setId(rs.getInt("id"));
					bean.setYx(rs.getInt("yx"));
					bean.setBj(rs.getInt("bj"));
					bean.setSid(rs.getInt("sid"));
					bean.setTid(rs.getString("tid"));
					bean.setKc(rs.getString("kc"));
					bean.setStates(rs.getString("states"));
					bean.setTimes(rs.getString("times"));
					bean.setDescs(rs.getString("descs"));
					bean.setZj(rs.getString("zj"));
					list.add(bean);
				}
				Page page = new Page();
				page.setPageNo(pageNo);
				page.setTotlePage(totlePage);
				page.setTotle(totle);
				page.setList(list);
				request.setAttribute("page", page);
				request.getRequestDispatcher("kqlist1.jsp").forward(
						request, response);
			}
			
			if (mode != null && mode.equals("slist2")) {
				String bjid=session.getAttribute("bj").toString();
				String sql = " select * from kq where 1=1 and states<>'待审核' and bj="+bjid;
				String sql2 = "select count(*) from kq where 1=1 and states<>'待审核' and bj="+bjid;
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

				List<Kq> list = new ArrayList<Kq>();
				while (rs.next()) {
					
					Kq bean = new Kq();
					bean.setId(rs.getInt("id"));
					bean.setYx(rs.getInt("yx"));
					bean.setBj(rs.getInt("bj"));
					bean.setSid(rs.getInt("sid"));
					bean.setTid(rs.getString("tid"));
					bean.setKc(rs.getString("kc"));
					bean.setStates(rs.getString("states"));
					bean.setTimes(rs.getString("times"));
					bean.setDescs(rs.getString("descs"));
					bean.setZj(rs.getString("zj"));
					list.add(bean);
				}
				Page page = new Page();
				page.setPageNo(pageNo);
				page.setTotlePage(totlePage);
				page.setTotle(totle);
				page.setList(list);
				request.setAttribute("page", page);
				request.getRequestDispatcher("kqlist2.jsp").forward(
						request, response);
			}
			
			if (mode != null && mode.equals("sh1")) {
				String bjid=session.getAttribute("bj").toString();
				String id = request.getParameter("id");
				String states = request.getParameter("states");
				if(states.equals("1")){
					String sqlupdate = "update  kq set states='通过' where id=" + id;
					Statement stmt = conn.createStatement();
					stmt.executeUpdate(sqlupdate);
				}else{
					String sqlupdate = "update  kq set states='不通过' where id=" + id;
					Statement stmt = conn.createStatement();
					stmt.executeUpdate(sqlupdate);
				}
				
				
				String sql = " select * from kq where 1=1 and states='待审核' and bj="+bjid;
				String sql2 = "select count(*) from kq where 1=1 and states='待审核' and bj="+bjid;
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

				List<Kq> list = new ArrayList<Kq>();
				while (rs.next()) {
					
					Kq bean = new Kq();
					bean.setId(rs.getInt("id"));
					bean.setYx(rs.getInt("yx"));
					bean.setBj(rs.getInt("bj"));
					bean.setSid(rs.getInt("sid"));
					bean.setTid(rs.getString("tid"));
					bean.setKc(rs.getString("kc"));
					bean.setStates(rs.getString("states"));
					bean.setTimes(rs.getString("times"));
					bean.setDescs(rs.getString("descs"));
					bean.setZj(rs.getString("zj"));
					list.add(bean);
				}
				Page page = new Page();
				page.setPageNo(pageNo);
				page.setTotlePage(totlePage);
				page.setTotle(totle);
				page.setList(list);
				request.setAttribute("page", page);
				request.getRequestDispatcher("kqlist1.jsp").forward(
						request, response);
			}
			
			
			if (mode != null && mode.equals("deletes")) {

				String id = request.getParameter("id");

				String sqlDel = "delete from  kq where id=" + id;
				Statement stmt = conn.createStatement();
				stmt.executeUpdate(sqlDel);
				String bjid=session.getAttribute("bj").toString();
				
				String sql = " select * from kq where 1=1 and bj="+bjid;
				String sql2 = "select count(*) from kq where 1=1 and bj="+bjid;
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

				List<Kq> list = new ArrayList<Kq>();
				while (rs.next()) {
					
					Kq bean = new Kq();
					bean.setId(rs.getInt("id"));
					bean.setYx(rs.getInt("yx"));
					bean.setBj(rs.getInt("bj"));
					bean.setSid(rs.getInt("sid"));
					bean.setTid(rs.getString("tid"));
					bean.setKc(rs.getString("kc"));
					bean.setStates(rs.getString("states"));
					bean.setTimes(rs.getString("times"));
					bean.setDescs(rs.getString("descs"));
					bean.setZj(rs.getString("zj"));
					list.add(bean);
				}
				Page page = new Page();
				page.setPageNo(pageNo);
				page.setTotlePage(totlePage);
				page.setTotle(totle);
				page.setList(list);
				request.setAttribute("page", page);
				request.getRequestDispatcher("kqlist.jsp").forward(
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
			
			if (mode != null && mode.equals("kqtj")) {
				String btimes = request.getParameter("btimes")==null?"":request.getParameter("btimes");
				String etimes = request.getParameter("etimes")==null?"":request.getParameter("etimes");
				String sql = " select yx,count(1) sid from kq where 1=1 ";
				if(btimes!=null&&!btimes.equals("")){
					sql+=" and times>='"+btimes+"'";
				}
				if(etimes!=null&&!etimes.equals("")){
					sql+=" and times<='"+etimes+"'";
				}
				sql+=" group by yx ";
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
				request.getRequestDispatcher("kqtj.jsp").forward(
						request, response);
			}
			if (mode != null && mode.equals("tjlist")) {
				
				String yx = request.getParameter("yx");
				
				String sql = " select * from kq where 1=1 and yx="+yx;
				String sql2 = "select count(*) from kq where 1=1 and yx="+yx;
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

				List<Kq> list = new ArrayList<Kq>();
				while (rs.next()) {
					
					Kq bean = new Kq();
					bean.setId(rs.getInt("id"));
					bean.setYx(rs.getInt("yx"));
					bean.setBj(rs.getInt("bj"));
					bean.setSid(rs.getInt("sid"));
					bean.setTid(rs.getString("tid"));
					bean.setKc(rs.getString("kc"));
					bean.setStates(rs.getString("states"));
					bean.setTimes(rs.getString("times"));
					bean.setDescs(rs.getString("descs"));
					bean.setZj(rs.getString("zj"));
					list.add(bean);
				}
				Page page = new Page();
				page.setPageNo(pageNo);
				page.setTotlePage(totlePage);
				page.setTotle(totle);
				page.setList(list);
				request.setAttribute("page", page);
				request.getRequestDispatcher("kqtjlist.jsp").forward(
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
