<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort() +request.getContextPath()+"/";
%>
<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath %>/getBanners.do">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>轮播</title>
</head>
<body>
<%--数据表格--%>
<table id="bannerDg"></table>
<%--添加轮播图窗口--%>
<div id="bannerBox1">
	<center style="padding-top: 30px">
		<form action="javascript:void(0)" method="post">
			<table style="width: 500px;">
				<tr>
					<td>商品图片</td>
					<td>
						<input type="button" id="onPicUpload" value="图片上传" />
						<input type="hidden" name="imgUrl" value="aaa" />
					</td>
				</tr>
				<tr>
					<td>跳转链接</td>
					<td>
						<input type="text" name="href" />
					</td>
				</tr>
				<tr>
					<td>备注</td>
					<td>
						<textarea name="remark"></textarea>
					</td>
				</tr>
				<tr>
					<td>排序号</td>
					<td>
						<select name="sort" id="">
							<option value="1">第1张</option>
							<option value="2">第2张</option>
							<option value="3">第3张</option>
						</select>
					</td>
				</tr>
				<tr>
					<td><input type="submit" value="添加"></td>
					<td>
						<input type="reset" value="取消">
					</td>
				</tr>
			</table>
		</form>
	</center>
</div>

<script type="text/javascript">
	$(function(){//处理文档流
		//添加轮播单机事件
		$("#bannerBox1 input[type=submit]").click(function(){
			$.ajax({
				url:"<%=basePath %>/addBanner.do",
				type:"POST",
				dataType:"json",
				data:{
					"imgUrl":$("#bannerBox1 input[name=imgUrl]").val(),
					"href":$("#bannerBox1 input[name=href]").val(),
					"remark":$("#bannerBox1 textarea[name=remark]").val(),
					"sort":$("#bannerBox1 select[name=sort]").val()
				},
				success: function (rs) {
					console.log(rs);
				}
			})
		});

		var editor;
		window.editor = KindEditor.create('textarea[name="description"]', {
			allowFileManager: true,
			height: "200px" //编辑器的高度为100px
		});
		/*图片上传*/
		$("#onPicUpload").click(function() {
			var _self = $(this);
			KindEditor.editor({
				//指定上传文件参数名称
				filePostName: "file",
				//指定上传文件请求的url。
				uploadJson: '<%=basePath %>/testUpload',
				//上传类型，分别为image、flash、media、file
				dir: "image"
			}).loadPlugin('image', function() {
				this.plugin.imageDialog({
					showRemote: false,
					clickFn: function(url, title, width, height, border, align) {
						$("input[name=imgUrl]").val(url);
						$("#onPicUpload").after("<a href='" + url + "' target='_blank'><img src='" + url + "' width='80' height='50'/></a>");
						$("#onPicUpload").remove();
						this.hideDialog();
					}
				});
			});
		});

		/*表格请求后台java接口*/
		$("#bannerDg").datagrid({
			url:"<%=basePath %>/getHxMenus.do",
			rownumbers:true,
			pagination:true,
			pageList:[5,10,20,30,50],
			columns:[
				[
					{checkbox:true},
					{field:'id',title:'主键',width:150,align:'center'},
					{field:'imgUrl',title:'图片地址',width:150},
					{field:'href',title:'跳转链接',width:150},
					{field:'remark',title:'备注',width:150},
					{field:'sort',title:'排序号',width:150},
					{field:'updateTime',title:'更新时间',width:200,sortable:true}
				]
			],
			sortName:'updateTime',/*定义field=id的列可以排序*/
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
					text:'添加',
					handler:function(){//事件处理器
						$("#bannerBox1").window('open');
					}
				},'-',
				{//编辑按钮
					iconCls:'icon-edit',
					text:'编辑',
				},'-',
				{
					iconCls:'icon-remove',
					text:'批量删除',
				}//批量删除
			]
		});

		/*添加前台横向菜单窗口*/
		$("#bannerBox1").window({
			width:600,
			height:400,
			title:"添加轮播",
			iconCls:'icon-add',
			draggable:true,/*能拖动*/
			resizable:false, /*不能改变尺寸*/
			minimizable:false,
			collapsible:false,
			maximizable:false,
			modal:true,
			closed:true//,  /*窗口初始化时就默认关闭*/
			/*  onClose:function(){//关闭窗口时触发事件
                  $("#a1").val("");
                  $("#a2").val("");
              }*/
		});
	});
</script>
</body>
</html>
