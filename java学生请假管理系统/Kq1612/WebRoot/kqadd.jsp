<%@ page language="java" pageEncoding="utf-8"%>
<%@page import="java.util.*" %>
<%@page import="java.sql.*" %>
<%@page import="com.bean.*"%>
<%@page import="com.util.*"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    
     
DBUtil util = new DBUtil();
Connection conn = util.openConnection();

String sql = "select * from stu ";
PreparedStatement pstmt1 = conn.prepareStatement(sql);
ResultSet rs = pstmt1.executeQuery();

    
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>请假信息管理系统</title>
<link rel="stylesheet" href="<%=path %>/css/bootstrap.css" />
<link rel="stylesheet" href="<%=path %>/css/css.css" />
<script type="text/javascript" src="<%=path %>/js/jquery1.9.0.min.js"></script>
<script type="text/javascript" src="<%=path %>/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=path %>/js/sdmenu.js"></script>
<script type="text/javascript" src="<%=path %>/js/rl.js"></script>
<script type="text/javascript" src="<%=path %>/js/laydate/laydate.js"></script>
<script type="text/javascript">
	
		function check() {
				document.submitForm.submit();
		}
</script>
</head>

<body>
     
    <div class="right_cont">
<ul class="breadcrumb">当前位置：
  考勤管理
</ul>
   
   <div class="title_right"><strong>考勤添加</strong></div>
   <form id="submitForm" name="submitForm" action="<%=path %>/KqServlet?mode=add" method="post" >
   
   <div style="width:900px; margin:auto">
   <table class="table table-bordered" >
   <tr>
       <td width="12%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">学生：</td>
       <td width="38%">
       
       <select name="sid" id="sid" class=" span1-1">
             <%while(rs.next()){ %>
            <option value="<%=rs.getInt("id")%>"><%=rs.getString("realname") %></option>
            <%} %>
          </select>
       
       </td>
     </tr>
     <tr>
       <td width="12%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">周几：</td>
       <td width="38%"><input type="text" name="zj" id="zj" class="span1-1"  /></td>
     </tr>
      <tr>
       <td width="12%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">时间：</td>
       <td width="38%"><input type="text" name="times" id="times" class="span1-1" onclick="SelectDate('etimes')" /></td>
     </tr>
      <tr>
       <td width="12%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">教师：</td>
       <td width="38%"><input type="text" name="tid" id="tid" class="span1-1" /></td>
     </tr>
      <tr>
       <td width="12%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">课程：</td>
       <td width="38%"><input type="text" name="kc" id="kc" class="span1-1" /></td>
     </tr>
       <tr>
       <td width="12%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">事项：</td>
       <td width="38%">
       
       <select name="descs" id="descs" class=" span1-1">
            <option value="迟到">迟到</option>
            <option value="早退">早退</option>
            <option value="请假">请假</option>
            <option value="正常">正常</option>
            <option value="缺课">缺课</option>
          </select>
       
       </td>
     </tr>
   </table>
   <table  class="margin-bottom-20 table  no-border" >
        <tr>
     	<td class="text-center">
     	<input type="button" value="确定" class="btn btn-info " style="width:80px;" onclick="check()"/>
     	</td>
     </tr>
 </table>
      
   </div> 
   </form>
     </div>      
 
</body>
</html>
