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
<script type="text/javascript" src="<%=path %>/js/laydate/laydate.js"></script>

</head>

<body>
     
     <div class="right_cont">
<ul class="breadcrumb">当前位置：
  电脑开票
</ul>
   <div class="title_right"><strong>平安物流到货清单</strong></div>  
   <div style="width:900px; margin:auto">
       <table class="table table-bordered">
         <tr>
     <td width="10%" align="right" bgcolor="#f1f1f1">起站：</td>
     <td width="23%"><input type="text" name="input18" id="input18" class="span1-1" /></td>
     <td width="10%" align="right" bgcolor="#f1f1f1">到站：</td>
     <td width="23%"> <select name="select3" id="select3" class=" span1-1">
         <option>郑州</option>
       </select></td>
     <td width="10%" align="right" bgcolor="#f1f1f1">装车日期：</td>
     <td><input type="text"  class="laydate-icon span1-1" id="Calendar" value="2015-08-25"    /></td>
     </tr>
       </table>
       <table  class="margin-bottom-20 table  no-border" >
        <tr>
     	<td class="text-center"><input type="button" value="确定" class="btn btn-info " style="width:80px;" /></td>
     </tr>
 </table>
       <table class="table table-bordered">
         <tr>
           <td align="center" nowrap="nowrap" bgcolor="#f1f1f1"><strong>             序号</strong></td>
           <td align="center" nowrap="nowrap" bgcolor="#f1f1f1"><strong>货单号</strong></td>
           <td align="center" nowrap="nowrap" bgcolor="#f1f1f1"><strong>发货日期</strong></td>
           <td align="center" nowrap="nowrap" bgcolor="#f1f1f1"><strong>起站</strong></td>
           <td align="center" nowrap="nowrap" bgcolor="#f1f1f1"><strong>到站</strong></td>
           <td align="center" nowrap="nowrap" bgcolor="#f1f1f1"><strong>发货人</strong></td>
           <td align="center" nowrap="nowrap" bgcolor="#f1f1f1"><strong>收货人</strong></td>
           <td align="center" nowrap="nowrap" bgcolor="#f1f1f1"><strong>收货人电话 </strong></td>
           <td align="center" nowrap="nowrap" bgcolor="#f1f1f1"><strong>货物名称</strong></td>
           <td align="center" nowrap="nowrap" bgcolor="#f1f1f1"><strong>付款方式</strong></td>
           <td align="center" nowrap="nowrap" bgcolor="#f1f1f1"><strong>运费</strong></td>
           <td align="center" nowrap="nowrap" bgcolor="#f1f1f1"><strong>送货费</strong></td>
           <td align="center" nowrap="nowrap" bgcolor="#f1f1f1"><strong>中转运费</strong></td>
           <td align="center" nowrap="nowrap" bgcolor="#f1f1f1"><strong>件数</strong></td>
         </tr>
         <tr>
           <td align="center">&nbsp;</td>
           <td align="center">&nbsp;</td>
           <td align="center">&nbsp;</td>
           <td align="center">&nbsp;</td>
           <td align="center">&nbsp;</td>
           <td align="center">&nbsp;</td>
           <td align="center">&nbsp;</td>
           <td align="center">&nbsp;</td>
           <td align="center">&nbsp;</td>
           <td align="center">&nbsp;</td>
           <td align="center">&nbsp;</td>
           <td align="center">&nbsp;</td>
           <td align="center">&nbsp;</td>
           <td align="center">&nbsp;</td>
         </tr>
       </table>
   <table  class="margin-bottom-20 table  no-border" >
     <tr>
       <td class="text-center">
       <input type="button" value="首页" class="btn btn-info  " style="width:80px;" />
       <input type="button" value="上一页" class="btn btn-info  " style="width:80px;" />
        <input type="button" value="下一页" class="btn btn-info  " style="width:80px;" />
         <input type="button" value="尾页" class="btn btn-info  " style="width:80px;" /></td>
     </tr>
   </table>
</div>
     
     
     </div>     
 
</body>
</html>
