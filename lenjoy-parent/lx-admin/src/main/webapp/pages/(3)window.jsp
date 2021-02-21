<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort() +request.getContextPath()+"/";
%>
<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath %>">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>窗口控件</title>
	<link rel="stylesheet" type="text/css" href="<%=basePath %>/static/js/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>/static/js/easyui/themes/icon.css">
	<script type="text/javascript" src="<%=basePath %>/static/js/easyui/jquery.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>/static/js/easyui/jquery.easyui.min.js"></script>
</head>
<body>
	<a href="http://www.baidu.com">百度一下</a>
	<div id="box1"></div>

	<script type="text/javascript">
		$(function(){
		    $("#box1").window({
				width:600,
				height:400,
				title:"添加横向菜单",
				iconCls:'icon-add',
                draggable:false,/*不能拖动*/
				resizable:false, /*不能改变尺寸*/
                minimizable:false,
                collapsible:false,
                maximizable:false,
				modal:true
				/*closed:true*/  /*窗口初始化时就默认关闭*/
			});
		});
	</script>

	<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">添加</a>
</body>
</html>
