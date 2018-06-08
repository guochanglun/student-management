<%@ page language="java" pageEncoding="utf-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    
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
  请假管理
</ul>
   
   <div class="title_right"><strong>请假添加</strong></div>
   <form id="submitForm" name="submitForm" action="<%=path %>/AddQjServlet" method="post" enctype="multipart/form-data">
   
   <div style="width:900px; margin:auto">
   <table class="table table-bordered" >
     <tr>
       <td width="12%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">开始时间：</td>
       <td width="38%"><input type="text" name="btimes" id="btimes" class="span1-1" onclick="SelectDate('btimes')" /></td>
     </tr>
      <tr>
       <td width="12%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">结束时间：</td>
       <td width="38%"><input type="text" name="etimes" id="etimes" class="span1-1" onclick="SelectDate('etimes')" /></td>
     </tr>
      <tr>
       <td width="12%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">资料：</td>
       <td width="38%">
       <input type="file" name="pwd" size="20"/>
       </td>
     </tr>
      <tr>
       <td width="12%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">请假原因：</td>
       <td colspan="3"><textarea name="descs" id="descs" class="span10"></textarea></td>
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
