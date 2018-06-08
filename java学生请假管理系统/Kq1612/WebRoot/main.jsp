<%@ page language="java" pageEncoding="utf-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    String roles=request.getSession().getAttribute("roles").toString();
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

</head>

<body>
<div class="header">
	 <div class="logo"><h3><font color="white">请假信息管理系统</font></h3></div>
     
				<div class="header-right">
                <i class="icon-question-sign icon-white"></i> <a href="#">帮助</a> <i class="icon-off icon-white"></i> <a id="modal-973558" href="#modal-container-973558" role="button" data-toggle="modal">注销</a> <i class="icon-user icon-white"></i> <a href="#"><%=roles %></a> 
                <div id="modal-container-973558" class="modal hide fade" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="width:300px; margin-left:-150px; top:30%">
				<div class="modal-header">
					 <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h3 id="myModalLabel">
						注销系统
					</h3>
				</div>
				<div class="modal-body">
					<p>
						您确定要注销退出系统吗？
					</p>
				</div>
				<div class="modal-footer">
					 <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button> <a class="btn btn-primary" style="line-height:20px;" href="<%=path %>/login.jsp" >确定退出</a>
				</div>
			</div>
				</div>
</div>
<!-- 顶部 -->     
            
<div id="middle">
     <div class="left">
     
     <script type="text/javascript">
var myMenu;
window.onload = function() {
	myMenu = new SDMenu("my_menu");
	myMenu.init();
};
</script>

<div id="my_menu" class="sdmenu">
	<div >
		<span>基本信息</span>
		
		<%if(roles.equals("系统管理员")){ %>
		<a href="<%=path %>/YxServlet?mode=list" target="right">院系管理</a>
		<a href="<%=path %>/yxadd.jsp" target="right">院系添加</a>
		<a href="<%=path %>/BjServlet?mode=list" target="right">班级管理</a>
		<a href="<%=path %>/bjadd.jsp" target="right">班级添加</a>
		<a href="<%=path %>/UsersServlet?mode=list" target="right">用户管理</a>
		<a href="<%=path %>/usersadd.jsp" target="right">用户添加</a>
		<a href="<%=path %>/StuServlet?mode=list" target="right">学生管理</a>
		<a href="<%=path %>/stuadd.jsp" target="right">学生添加</a>
	    <a href="<%=path %>/KqServlet?mode=kqtj" target="right">考勤统计</a>
		<a href="<%=path %>/QjServlet?mode=tj" target="right">请假统计</a>
		<a href="<%=path %>/QjServlet?mode=tj2" target="right">缺勤率</a>
		<%}else if(roles.equals("主管院长")){  %>
		<a href="<%=path %>/StuServlet?mode=list" target="right">学生管理</a>
		<a href="<%=path %>/QjServlet?mode=slist4" target="right">待审核请假</a>
		<a href="<%=path %>/QjServlet?mode=tj" target="right">请假统计</a>
		<a href="<%=path %>/QjServlet?mode=tj2" target="right">缺勤率</a>
		<%}else if(roles.equals("辅导员")){  %>
		<a href="<%=path %>/StuServlet?mode=list" target="right">学生管理</a>
		<a href="<%=path %>/QjServlet?mode=slist3" target="right">待审核请假</a>
		<a href="<%=path %>/QjServlet?mode=tj" target="right">请假统计</a>
		<a href="<%=path %>/QjServlet?mode=tj2" target="right">缺勤率</a>
		<%}else if(roles.equals("班主任")){  %>
		<a href="<%=path %>/StuServlet?mode=list" target="right">学生管理</a>
		<a href="<%=path %>/QjServlet?mode=slist2" target="right">待审核请假</a>
		<a href="<%=path %>/KqServlet?mode=slist1" target="right">待审核考勤</a>
		<a href="<%=path %>/KqServlet?mode=slist2" target="right">考勤查看</a>
		<a href="<%=path %>/QjServlet?mode=tj" target="right">请假统计</a>
		<a href="<%=path %>/QjServlet?mode=tj2" target="right">缺勤率</a>
		<%}else if(roles.equals("分管班委")){  %>
		<a href="<%=path %>/QjServlet?mode=list" target="right">请假管理</a>
		<a href="<%=path %>/qjadd.jsp" target="right">请假添加</a>
		<a href="<%=path %>/StuServlet?mode=list" target="right">学生管理</a>
		<a href="<%=path %>/QjServlet?mode=slist1" target="right">待审核请假</a>
		<a href="<%=path %>/QjServlet?mode=tj" target="right">请假统计</a>
		<a href="<%=path %>/QjServlet?mode=tj2" target="right">缺勤率</a>
		<a href="<%=path %>/KqServlet?mode=list" target="right">考勤管理</a>
		<a href="<%=path %>/kqadd.jsp" target="right">考勤添加</a>
		<%}else {  %>
		<a href="<%=path %>/QjServlet?mode=list" target="right">请假管理</a>
		<a href="<%=path %>/qjadd.jsp" target="right">请假添加</a>
		<%} %>
		
		
	</div>
</div>

     </div>
     <div class="Switch"></div>
     <script type="text/javascript">
	$(document).ready(function(e) {
    $(".Switch").click(function(){
	$(".left").toggle();
	 
		});
});
</script>
 <div class="right"  id="mainFrame">
     
      <iframe scrolling="auto" rameborder="0" src="" name="right" width="100%" height="100%"></iframe>    
     </div>
    </div>
    
<!-- 底部 -->
    
    

 <script>
!function(){
laydate.skin('molv');
laydate({elem: '#Calendar'});
}();
 
</script>



 
</body>
</html>
