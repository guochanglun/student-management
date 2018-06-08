<%@ page language="java" pageEncoding="utf-8"%>
<%@ page import="com.util.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="java.util.*"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
   	List list = (ArrayList)request.getAttribute("list");
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
        
         function frist()
		{
		 	with (document.getElementById("memberForm")) {
	            method = "post";
	            action = "<%=path %>/QjServlet?mode=list&pageNoStr=1";
	            submit();
	        }
		}
        
		
		function search()
		{ 
			with (document.getElementById("memberForm")) {
	            method = "post";
	            action = "<%=path %>/QjServlet?mode=list";
	            submit();
	        }
		}
	
		function modify(id){
			window.location.href="<%=path %>/QjServlet?mode=modifybefore&id="+id;
		}
		function del(id){
			window.location.href="<%=path %>/QjServlet?mode=deletes&id="+id;
		}
		function tp(id){
			window.location.href="<%=path %>/QjServlet?mode=tplist&id="+id;
		}
		function ly(id){
			window.location.href="<%=path %>/LiuyanServlet?mode=list&gid="+id;
		}
		</script>
</head>

<body>
     
     <div class="right_cont">
<ul class="breadcrumb">当前位置：
请假统计
</ul>
   <div class="title_right"><strong>请假统计</strong></div>  
    <form id="memberForm">
   <div style="width:900px; margin:auto">
       <table class="table table-bordered">
         <tr>
         
       
	
         
           <td align="center" nowrap="nowrap" bgcolor="#f1f1f1"><strong>学生</strong></td>
           <td align="center" nowrap="nowrap" bgcolor="#f1f1f1"><strong>缺勤次数</strong></td>
         </tr>
          <%for(int i=0;i<list.size();i++){ 
        	Qj bean = (Qj)list.get(i);
        %>
         <tr>
           <td align="center"><%=ValueBean.getStuNameById(bean.getSid()) %></td>
           <td align="center"><%=bean.getYx() %></td>
         </tr>
         <%} %>
       </table>
</div>
     </form>
     
     </div>     
 
</body>
</html>
