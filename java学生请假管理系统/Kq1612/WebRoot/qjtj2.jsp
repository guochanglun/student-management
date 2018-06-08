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
<script type="text/javascript" src="<%=path %>/js/rl.js"></script>
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
	            action = "<%=path %>/QjServlet?mode=tj2";
	            submit();
	        }
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
     <td width="10%" align="right" bgcolor="#f1f1f1">开始时间：</td>
     <td width="23%"><input type="text" name="btimes" id="btimes" class="span1-1" onclick="SelectDate('btimes')" /></td>
       <td width="10%" align="right" bgcolor="#f1f1f1">结束时间：</td>
     <td width="23%"><input type="text" name="etimes" id="etimes" class="span1-1" onclick="SelectDate('etimes')" /></td>
     </tr>
       </table>
       <table  class="margin-bottom-20 table  no-border" >
        <tr>
     	<td class="text-center">
     	<input type="button" value="统计" class="btn btn-info " style="width:80px;" onclick="search()"/>
     	</td>
     </tr>
 </table>
   
   
       <table class="table table-bordered">
         <tr>
         
       
	
         
           <td align="center" nowrap="nowrap" bgcolor="#f1f1f1"><strong>学生</strong></td>
           <td align="center" nowrap="nowrap" bgcolor="#f1f1f1"><strong>缺勤次数</strong></td>
             <td align="center" nowrap="nowrap" bgcolor="#f1f1f1"><strong>缺勤率</strong></td>
         </tr>
          <%for(int i=0;i<list.size();i++){ 
        	Qj bean = (Qj)list.get(i);
        %>
         <tr>
           <td align="center"><%=ValueBean.getStuNameById(bean.getSid()) %></td>
           <td align="center"><%=bean.getYx() %></td>
            <td align="center"><%=bean.getS1() %>%</td>
         </tr>
         <%} %>
       </table>
</div>
     </form>
     
     </div>     
 
</body>
</html>
