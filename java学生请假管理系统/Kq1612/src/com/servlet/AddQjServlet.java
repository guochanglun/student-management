package com.servlet;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspsmart.upload.Files;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
import com.util.DBUtil;
import com.util.Sentiment;
import com.util.ValueBean;


/**
 * 添加请假
 * @author wzk
 *
 */
public class AddQjServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String path = "upfile";
	/**
	 * Constructor of the object.
	 */
	public AddQjServlet() {
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

		String gurl = "/upfile/";
		String loadpath = this.getServletConfig().getServletContext()
				.getRealPath("/");
		String fullPath = loadpath + path;
		File fullDir = new File(fullPath);
		if (!fullDir.exists()) {
			fullDir.mkdirs();
		}
		SmartUpload smartUpload = new SmartUpload();

		long maxSize = 10 * 1024 * 1024;// 
		String allowFileExtList = "jar,exe,doc,docx,txt,html,xml,xls,pdf,jpg,png,gif";
		smartUpload.initialize(getServletConfig(), request, response);
		smartUpload.setMaxFileSize(maxSize);
		smartUpload.setAllowedFilesList(allowFileExtList);
		
		String btimes = "";
		String descs = "";
		String etimes = "";
		try {
			smartUpload.upload();
			btimes = smartUpload.getRequest().getParameter("btimes");
			descs = smartUpload.getRequest().getParameter("descs");
			boolean result = Sentiment.classify(descs);
			if(!result){
				System.out.println("用词情感太消极");
				request.getRequestDispatcher("sentiment.jsp").forward(
						request, response);
				return;
			}
			etimes = smartUpload.getRequest().getParameter("etimes");
			Files allFiles = smartUpload.getFiles();
			Enumeration fileEnum = allFiles.getEnumeration();
			while (fileEnum.hasMoreElements()) {
				com.jspsmart.upload.File smartFile = (com.jspsmart.upload.File) fileEnum
						.nextElement();
				if (!smartFile.isMissing()) {
					String fileName = smartFile.getFileName();
					String type = fileName.substring(fileName.lastIndexOf("."));
					// 创建对象
					Random rnd = new Random();
					// 获取随机int
					int r = rnd.nextInt(100);
					Date date2 = new Date();
					SimpleDateFormat formatter = new SimpleDateFormat(
							"yyyyMMddHHmmss");
					String strDate2 = formatter.format(date2);
					fileName = strDate2 + r + type;
					response.setContentType("text/html;charset=gbk");// 
					smartFile.saveAs(fullPath + "//" + fileName,
							com.jspsmart.upload.File.SAVEAS_PHYSICAL);
					gurl += fileName;
				}
				break;
			}

		} catch (SmartUploadException e) {
			e.printStackTrace();
		}

		DBUtil util = new DBUtil();
		Connection conn = util.openConnection();
		try {
			
			HttpSession session = request.getSession();
			Integer id = (Integer) session.getAttribute("id");
			String sql = "insert into qj(yx,bj,sid,states,btimes,etimes,descs,url,s1,s2,s3,s4, realname) values("
					+ session.getAttribute("yx")+","+ session.getAttribute("bj")+","+ session.getAttribute("id")
					+ ",'待审核','"
					+ btimes+ "','"
					+ etimes
					+ "','"
					+ descs
					+ "','" + gurl+ "','待审核','待审核','待审核','待审核', '"+ValueBean.getStuNameById(id)+"')";

			System.out.println(sql);

			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			util.closeConn(conn);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		util.closeConn(conn);
		request.getRequestDispatcher("QjServlet?mode=list").forward(request,
				response);
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
