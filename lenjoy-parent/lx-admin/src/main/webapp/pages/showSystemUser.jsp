<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort() +request.getContextPath()+"/";
%>
<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath %>">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>系统用户信息查询</title>
</head>
<body>
<%--数据表格--%>
<table id="dg"></table>
<%--添加系统账号--%>
<div id="box1">

</div>
<%--编辑前台横向菜单窗口--%>
<div id="box2">
	<center style="padding-top: 30px">
		<form id="ff2" method="post" action="javascript:void(0)">
			<table cellpadding="5">
				<tr>
					<td>菜单名:</td>
					<td><input type="text" name="title"></input></td>
				</tr>
				<tr>
					<td>跳转链接:</td>
					<td><input type="text" name="url"></input></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<a href="javascript:void(0)" id="edit">编辑</a>
						<a href="javascript:void(0)" id="res">重置</a>
					</td>
				</tr>
			</table>
		</form>
	</center>
</div>
<%--设置权限--%>
<div id="box3">

</div>

<script type="text/javascript">
	function editAuthority(id){
		//$("#box3").window("open");
		$('#box3').panel('open').panel('refresh','<%=basePath %>/pages/editAuthority.jsp?userId='+id);
	}

	$(function(){//处理文档流
		/*表格请求后台java接口*/
		$("#dg").datagrid({
			url:"<%=basePath %>/getSystemUser.do",
			rownumbers:true,
			columns:[
				[
					{checkbox:true},
					{field:'id',title:'主键',width:150,align:'center',sortable:true},
					{field:'username',title:'账号',width:150},
					{field:'isRoot',title:'角色',width:150,
						formatter: function(value,row,index){
							if(value=='1'){
								return '超级管理员';
							}
							return '普通管理员';
						}
					},
					{field:'updateTime',title:'更新时间',width:200},
					{field:'operate',title:'操作',width:200,
						formatter: function(value,row,index){
							if(row.isRoot=='1'){
								return '无操作';
							}
							return '<a class="c1" onclick="editAuthority('+row.id+')"></a>';
						}
					}
				]
			],
			onLoadSuccess:function(data){
				jQuery(".c1").linkbutton({
					iconCls:'icon-edit',
					text:'设置授权',
					plain:true
				});
			},
			sortName:'id',/*定义field=id的列可以排序*/
			remoteSort:false,/*关闭远程排序*/
			fit:true,
			striped:true,//斑马线效果
			/*rowStyler:function(index,row){//实现隔行变色 index：索引  row：当前行
                if(index%2==0){//奇数
                    return "background-color:red";
                }else{//偶数
                    return "background-color:green";
                }
            },*/
			toolbar:[
				{//添加按钮
					iconCls:'icon-add',
					text:'添加账号',
					handler:function(){//事件处理器
						$("#box1").window('open');
					}
				},'-',
				{//编辑按钮
					iconCls:'icon-edit',
					text:'编辑账号',
					handler:function(){//事件处理器
						//首先判断是否选中某一条记录
						var ckAttr = $("#dg").datagrid("getSelections");
						var len = ckAttr.length;
						if(len==0){
							$.messager.alert('警告','没有选中要编辑的行');
						}else if(len==1){
							$("#edit").linkbutton({
								iconCls:'icon-ok'
							});
							$("#res").linkbutton({
								iconCls:'icon-cancel'
							});
							$("#ff2 input[name=title]").validatebox({
								required: true,
								validType: 'length[1,10]'
							});
							$("#ff2 input[name=url]").validatebox({
								required: true,
								validType: 'url'
							});
							//进行数据回显
							var row = $("#dg").datagrid("getSelected");
							$("#ff2 input[name=title]").val(row.title);
							$("#ff2 input[name=url]").val(row.url);
							$("#box2").window('open');
						}else{
							$.messager.alert('警告','编辑时不能选中多行');
						}
					}
				},'-',
				{
					iconCls:'icon-remove',
					text:'批量删除',
					handler:function(){//事件处理器
						//首先判断是选中数据记录
						var ckAttr = $("#dg").datagrid("getSelections");
						var len = ckAttr.length;
						if(len==0){
							$.messager.alert('警告','没有选中要删除的记录');
						}else{
							$.messager.confirm('警告', '您确认删除记录吗？', function(r){
								if (r){//确认删除
									var idStr = "";

									for(var i=0;i<ckAttr.length;i++){
										idStr+=ckAttr[i].id+",";
									}
									$.ajax({
										url:"<%=basePath %>/deleteWebMenu.do",
										type:"post",
										dataType:"json",
										data:{
											"idStr":idStr
										},
										success:function(rs){
											if(rs){//删除成功
												//2、刷新表格
												$("#dg").datagrid('reload');
												//3、提示成功
												$.messager.show({
													title:"提示",
													msg:"菜单删除成功"
												});
											}else{//删除失败
												$.messager.alert('提示','删除失败，请重试');
											}
										},
										error:function(err){
											$.messager.alert('提示','删除失败，请重试');
										}
									});
								}
							});
						}
					}
				}//批量删除
			]
		});

		/*添加账号*/
		$("#box1").window({
			width:600,
			height:400,
			href:'<%=basePath %>/pages/addSystemUserForm.jsp',
			title:"添加账号",
			iconCls:'icon-add',
			draggable:true,/*能拖动*/
			resizable:false, /*不能改变尺寸*/
			minimizable:false,
			collapsible:false,
			maximizable:false,
			modal:true,
			closed:true,  /*窗口初始化时就默认关闭*/
			onClose:function(){//关闭窗口时触发事件
				$("#a1").val("");
				$("#a2").val("");
			}
		});

		/*编辑前台横向菜单窗口*/
		$("#box2").window({
			width:300,
			height:200,
			title:"编辑横向菜单",
			iconCls:'icon-edit',
			draggable:true,/*能拖动*/
			resizable:false, /*不能改变尺寸*/
			minimizable:false,
			collapsible:false,
			maximizable:false,
			modal:true,
			closed:true
		});

		/*编辑触发ajax*/
		$("#edit").click(function(){
			var row = $("#dg").datagrid("getSelected");
			var id=row.id;
			$.ajax({
				url:"<%=basePath %>/updateWebMenu.do",
				type:"post",
				dataType:"json",
				data:{
					"title":$("#ff2 input[name=title]").val(),
					"url":$("#ff2 input[name=url]").val(),
					"id":id
				},
				success:function(rs){
					if(rs){//编辑成功
						//1、关闭添加前台菜单窗口
						$("#box2").window('close');
						//2、刷新表格
						$("#dg").datagrid('reload');
						//3、提示添加成功
						$.messager.show({
							title:"提示",
							msg:"菜单编辑成功"
						});
					}else{//添加失败
						$.messager.alert('提示','编辑失败，请重试');
					}
				},
				error:function(err){
					$.messager.alert('提示','编辑失败，请重试');
				}
			});
		});
	});

	/*设置权限*/
	$("#box3").window({
		width:500,
		height:200,
		//href:'<%=basePath %>/pages/editAuthority.jsp',
		title:"设置权限",
		iconCls:'icon-edit',
		draggable:true,/*能拖动*/
		resizable:false, /*不能改变尺寸*/
		minimizable:false,
		collapsible:false,
		maximizable:false,
		modal:true,
		closed:true,  /*窗口初始化时就默认关闭*/
		onClose:function(){//关闭窗口时触发事件
			/*$("#a1").val("");
            $("#a2").val("");*/
		}
	});
</script>
</body>
</html>
