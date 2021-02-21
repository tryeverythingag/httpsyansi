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
	<%--
		拖动组件：
		easyUI的调用方式1：通过class来调用，组件名格式：easyui-组件名
			easyUI组件的几个组成部分：
				(1)、调用方式:class="easyui-组件名"
				(2)、easyui的属性：对控件进行定制化，easyui的属性需要书写在data-options中(第1中调用方式)
				(3)、事件
				(4)、方法
	--%>
	<%--<div data-options="revert:true,cursor:'pointer',edge:10" class="easyui-draggable" style="width: 100px;height: 100px;background: red;border-radius: 50%">

	</div>--%>

	<%--
		easyui的第2种调用方式：javascript调用
			easyUI组件的几个组成部分：
				(1)、调用方式:jquery对象.组件名([{}]);
				(2)、easyui的属性：书写在组件名的{属性名:属性值}中
				(3)、事件
				(4)、方法
	--%>
	<div id="dg" style="width: 100px;height: 100px;background: red;border-radius: 50%">

	</div>
	<script type="text/javascript">
		$("#dg").draggable({
			/*属性*/
			revert:true,
			cursor:'pointer',
			/*事件*/
			onDrag:function(){
			    console.log("被拖动了");
			},
            onStopDrag:function(){
			    console.log("拖动停止了");
			}
		});
	</script>
</body>
</html>
