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
	<script type="text/javascript" src="<%=basePath %>/static/js/easyui/locale/easyui-lang-zh_CN.js"></script>

	<%--引入vue--%>
	<script src="https://cdn.jsdelivr.net/npm/vue"></script>
	<script src="https://cdn.jsdelivr.net/npm/vue-resource@1.5.1"></script>

	<%--引入kindeditor--%>
	<script type="text/javascript" src="<%=basePath %>/static/js/kindeditor-5.1.10/kindeditor.js"></script>
	<script type="text/javascript" src="<%=basePath %>/static/js/kindeditor-5.1.10/lang/zh_CN.js"></script>
</head>
<body class="easyui-layout">
<%--北丐--%>
<div data-options="region:'north',title:'北丐',split:true" style="height: 100px;background: linear-gradient(#a1c4fd, #c2e9fb)"></div>
<%--南帝--%>
<div data-options="region:'south',title:'南帝',noheader:true,split:true" style="height: 60px;background: linear-gradient(#a8edea, #fed6e3)">
	<center style="line-height: 50px">&copy;版权所有|维权必究</center>
</div>
<%--西毒--%>
<div data-options="region:'west',iconCls:'icon-world',title:'菜单栏',split:true" style="width: 150px;background: linear-gradient(#a8edea, #fed6e3)">
	<ul id="tt"></ul>
</div>
<%--东邪--%>
<div data-options="region:'east',title:'东邪',split:true" style="width: 150px;background: linear-gradient(#a8edea, #fed6e3)"></div>
<%--中神通--%>
<div data-options="region:'center',title:'中神通',split:true,noheader:true">
	<%--书写选项卡--%>
	<%--
        fit：true，宽度和高度会与父标签保持一致
    --%>
	<div id="tb" class="easyui-tabs" data-options="fit:true" >
		<div data-options="title:'首页',iconCls:'icon-house'" style="width: 150px;background: linear-gradient(#fddb92, #d1fdff)">
			<center style="padding-top:150px;font-size: 36px;color: blue;font-weight: bolder;text-shadow: 10px 10px 5px #ccc">欢迎使用乐享购后台管理系统</center>
		</div>
	</div>
</div>
</body>
<script type="text/javascript">
	$("#tt").tree({
		url:"<%=basePath %>getAuthorityByUsername.do",
		lines:true,
		onClick:function(node){
			console.log(node);
			var flag = $("#tb").tabs('exists',node.text);
			if(!flag && node.url!=null){//选项卡不存在
				$("#tb").tabs('add',{
					title:node.text,
					iconCls:node.iconCls,
					closable:true,
					href:"<%=basePath %>/pages/"+node.url
				});
			}else{//选项卡存在，则选中
				$("#tb").tabs('select',node.text);
			}
		}
	});
</script>
</html>
