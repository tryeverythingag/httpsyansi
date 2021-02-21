<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort() +request.getContextPath()+"/";
%>
<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath %>">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>欢迎来到易买网后台</title>
	<link rel="stylesheet" type="text/css" href="<%=basePath %>/static/js/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>/static/js/easyui/themes/icon.css">
	<script type="text/javascript" src="<%=basePath %>/static/js/easyui/jquery.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>/static/js/easyui/jquery.easyui.min.js"></script>
</head>
<body>

	<div id="tb" class="easyui-tabs" style="width: 800px;height:400px;">
		<div data-options="title:'横向菜单',iconCls:'icon-ok',closable:true">选项卡1</div>
		<div data-options="title:'纵向菜单',iconCls:'icon-manager'">选项卡2</div>
		<div data-options="title:'轮播查询',iconCls:'icon-search'">选项卡3</div>
	</div>
	<button id="btn">生成选项卡</button>

	<script type="text/javascript">
		$("#btn").click(function(){
            var flag = $("#tb").tabs('exists','型控件');
			if(!flag){
                $("#tb").tabs('add',{
                    title:'型控件',
                    iconCls:'icon-abc',
                    closable:true
                });
			}
		});
	</script>
</body>
</html>
