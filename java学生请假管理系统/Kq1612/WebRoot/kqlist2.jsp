<%@ page language="java" pageEncoding="utf-8"%>
<%@ page import="com.util.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="java.util.*"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
   	Page pageBean = (Page)request.getAttribute("page");
	int pageNo = pageBean.getPageNo();
	int totlePage = pageBean.getTotlePage();
	int totle = pageBean.getTotle();
	List list = pageBean.getList();
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
        
        
		function back()
		{
		    var pageNo= '<%=pageNo%>';
		    if(pageNo<2){
		 	   alert("当前已经是第一页");
		 	   return;
		    }else{
		 	   pageNo=Number(pageNo)-1;
		    }
				 with (document.getElementById("memberForm")) {
			            method = "post";
			             action = "<%=path %>/KqServlet?mode=list&pageNoStr="+pageNo;
			            submit();
			        }
		}
        
		function next()
		{
			   var pageNo= '<%=pageNo%>';
			   var totlePage='<%=totlePage%>';
		    if(pageNo==totlePage){
		 	   alert("当前已经是最后一页");
		 	   return;
		    }else{
		 	   pageNo=Number(pageNo)+1;
		    }  
		    with (document.getElementById("memberForm")) {
		        method = "post";
		        action = "<%=path %>/KqServlet?mode=list&pageNoStr="+pageNo;
		        submit();
		    }
		}
        
        function last()
		{ 
		   var totlePage='<%=totlePage%>';
			with (document.getElementById("memberForm")) {
	            method = "post";
	            action = "<%=path %>/KqServlet?mode=list&pageNoStr="+totlePage;
	            submit();
	        }
		}
		
		function search()
		{ 
			with (document.getElementById("memberForm")) {
	            method = "post";
	            action = "<%=path %>/KqServlet?mode=list";
	            submit();
	        }
		}
	
		function modify(id){
			window.location.href="<%=path %>/KqServlet?mode=modifybefore&id="+id;
		}
		function del(id){
			window.location.href="<%=path %>/KqServlet?mode=deletes&id="+id;
		}
		function tp(id){
			window.location.href="<%=path %>/KqServlet?mode=tplist&id="+id;
		}
		function ly(id){
			window.location.href="<%=path %>/LiuyanServlet?mode=list&gid="+id;
		}
		</script>
</head>

<body>
     
     <div class="right_cont">
<ul class="breadcrumb">当前位置：
考勤管理
</ul>
   <div class="title_right"><strong>考勤清单</strong></div>  
    <form id="memberForm">
   <div style="width:900px; margin:auto">
       <table class="table table-bordered">
         <tr>
     <td width="10%" align="right" bgcolor="#f1f1f1">学生：</td>
     <td width="23%"><input type="text" name="names" id="names" class="span1-1" /></td>
     </tr>
       </table>
       <table  class="margin-bottom-20 table  no-border" >
        <tr>
     	<td class="text-center">
     	<input type="button" value="确定" class="btn btn-info " style="width:80px;" onclick="search()"/>
     	</td>
     </tr>
 </table>
       <table class="table table-bordered">
         <tr>
         
       
	
         
           <td align="center" nowrap="nowrap" bgcolor="#f1f1f1"><strong>序号</strong></td>
           <td align="center" nowrap="nowrap" bgcolor="#f1f1f1"><strong>学生</strong></td>
           <td align="center" nowrap="nowrap" bgcolor="#f1f1f1"><strong>班级</strong></td>
           <td align="center" nowrap="nowrap" bgcolor="#f1f1f1"><strong>时间</strong></td>
           <td align="center" nowrap="nowrap" bgcolor="#f1f1f1"><strong>周几</strong></td>
           <td align="center" nowrap="nowrap" bgcolor="#f1f1f1"><strong>事项</strong></td>
            <td align="center" nowrap="nowrap" bgcolor="#f1f1f1"><strong>课程</strong></td>
             <td align="center" nowrap="nowrap" bgcolor="#f1f1f1"><strong>教师</strong></td>
            <td align="center" nowrap="nowrap" bgcolor="#f1f1f1"><strong>状态</strong></td>
         </tr>
          <%for(int i=0;i<list.size();i++){ 
        	Kq bean = (Kq)list.get(i);
          %>
         <tr>
           <td align="center"><%=bean.getId() %></td>
           <td align="center"><%=ValueBean.getStuNameById(bean.getSid()) %></td>
            <td align="center"><%=ValueBean.getBjNameById(bean.getBj()) %></td>
           <td align="center"><%=bean.getTimes() %></td>
           <td align="center"><%=bean.getZj() %></td>
           <td align="center"><%=bean.getDescs() %></td>
              <td align="center"><%=bean.getKc() %></td>
              <td align="center"><%=bean.getTid() %></td>
            <td align="center"><%=bean.getStates() %></td>
         </tr>
         <%} %>
       </table>
   <table  class="margin-bottom-20 table  no-border" >
     <tr>
       <td class="text-center">
       当前第<%=pageNo %>页/共<%=totlePage %>页&nbsp;&nbsp;共<%=totle %>
         条记录&nbsp;&nbsp;
       <input type="button" value="首页" class="btn btn-info  " style="width:80px;" onclick="frist()"/>
       <input type="button" value="上一页" class="btn btn-info  " style="width:80px;" onclick="back()"/>
        <input type="button" value="下一页" class="btn btn-info  " style="width:80px;" onclick="next()"/>
         <input type="button" value="尾页" class="btn btn-info  " style="width:80px;" onclick="last()"/></td>
     </tr>
   </table>
</div>
     </form>
     
     </div>     
 
</body>
</html>
