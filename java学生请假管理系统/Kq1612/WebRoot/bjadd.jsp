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

String sql = "select * from yx ";
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
  班级管理
</ul>
   
   <div class="title_right"><strong>班级添加</strong></div>
   <form id="submitForm" name="submitForm" action="<%=path %>/BjServlet?mode=add" method="post">
   
   <div style="width:900px; margin:auto">
   <table class="table table-bordered" >
     <tr>
       <td width="12%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">名称：</td>
       <td width="38%"><input type="text" name="names" id="names" class="span1-1"  /></td>
     </tr>
     <tr>
       <td width="12%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">所属院系：</td>
       <td width="38%">
       
       <select name="yxid" id="yxid" class=" span1-1">
             <%while(rs.next()){ %>
            <option value="<%=rs.getInt("id")%>"><%=rs.getString("names") %></option>
            <%} %>
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
