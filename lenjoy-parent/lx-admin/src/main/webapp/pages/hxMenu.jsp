<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort() +request.getContextPath()+"/";
%>
<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath %>">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>前台横向菜单</title>
</head>
<body>
	<%--数据表格--%>
	<table id="dg"></table>
	<%--添加前台横向菜单窗口--%>
	<div id="box1">
		<center style="padding-top: 30px">
			<form id="ff" method="post" action="javascript:void(0)">
				<table cellpadding="5">
					<tr>
						<td>菜单名:</td>
						<td><input id="a1" type="text" name="title"></input></td>
					</tr>
					<tr>
						<td>跳转链接:</td>
						<td><input id="a2" type="text" name="url"></input></td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<a href="javascript:void(0)" id="add">添加</a>
							<a href="javascript:void(0)" id="rs">重置</a>
						</td>
					</tr>
				</table>
			</form>
		</center>
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

	<script type="text/javascript">
		$(function(){//处理文档流
			/*表格请求后台java接口*/
			$("#dg").datagrid({
				url:"<%=basePath %>/getHxMenus.do",
                rownumbers:true,
                pagination:true,
                pageList:[5,10,20,30,50],
				columns:[
                    [
                        {checkbox:true},
                        {field:'id',title:'主键',width:150,align:'center',sortable:true},
                    	{field:'title',title:'菜单名',width:150},
                    	{field:'url',title:'跳转链接',width:150},
                        {field:'updateTime',title:'更新时间',width:200}
					]
				],
                sortName:'id',/*定义field=id的列可以排序*/
                remoteSort:false,/*关闭远程排序*/
				fit:true,
				striped:true,//斑马线效果
				rowStyler:function(index,row){//实现隔行变色 index：索引  row：当前行
					return "background: linear-gradient(#d4fc79, #96e6a1)";

					/*if(index%2==0){//奇数
						return "background-color:#fb5";
					}else{//偶数
						return "background-color:#58a";
					}*/
				},
				toolbar:[
					{//添加按钮
					    iconCls:'icon-add',
						text:'添加',
						handler:function(){//事件处理器
							$("#add").linkbutton({
								iconCls:'icon-ok'
							});
                            $("#rs").linkbutton({
                                iconCls:'icon-cancel'
                            });
                            $("#a1").validatebox({
                                required: true,
                                validType: 'length[1,10]'
                            });
                            $("#a2").validatebox({
                                required: true,
                                validType: 'url'
							});
                            $("#box1").window('open');
						}
					},'-',
					{//编辑按钮
					    iconCls:'icon-edit',
                        text:'编辑',
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

			/*添加前台横向菜单窗口*/
            $("#box1").window({
                width:300,
                height:200,
                title:"添加横向菜单",
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

            /*添加触发ajax*/
            $("#add").click(function(){
                $.ajax({
					url:"<%=basePath %>/addWebMenu.do",
					type:"post",
					dataType:"json",
					data:{
					    "title":$("#ff input[name=title]").val(),
						"url":$("#ff input[name=url]").val()
					},
					success:function(rs){
						if(rs){//添加成功
							//1、关闭添加前台菜单窗口
                            $("#box1").window('close');
							//3、提示添加成功
                            $.messager.show({
								title:"提示",
								msg:"菜单添加成功"
							});
							//2、刷新表格
                            $("#dg").datagrid('reload');
						}else{//添加失败
                            $.messager.alert('提示','添加失败，请重试');
						}
					},
					error:function(err){
                        $.messager.alert('提示','添加失败，请重试');
					}
				});
			});

            /*重置添加内容*/
            $("#rs").click(function(){
                $("#ff input[type=text]").val("");
                $("#a1").focus();
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
	</script>
</body>
</html>
