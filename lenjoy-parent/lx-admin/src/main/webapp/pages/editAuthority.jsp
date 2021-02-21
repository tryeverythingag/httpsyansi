<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort() +request.getContextPath()+"/";
%>
<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath %>">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>设置权限</title>
</head>
<body>
<center style="padding-top: 50px">
	<table width="400px" id="authVue">
		<tr v-for="oneMenu in oneMenuList">
			<td><strong><input type="hidden" name="oneId" v-bind:value="oneMenu.oneId">{{oneMenu.oneText}}&nbsp;&nbsp;</strong></td>
			<td v-for="twoMenu in oneMenu.twoMenuList">
				<input type="checkbox" name="twoId" v-bind:value="twoMenu.twoId">{{twoMenu.twoText}}&nbsp;&nbsp;
			</td>
		</tr>
		<tr>
			<td>
				<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">确认</a>
			</td>
		</tr>
	</table>
</center>
<input type="hidden" name="userId" value="${param.userId}">
<script type="text/javascript">
	const vue1 = new Vue({
		el:"#authVue",
		data:{
			oneMenuList:[]
		},
		created(){
			this.$http.post("<%=basePath %>//findAuthorityByRelation.do").then(
					function(rs){
						this.oneMenuList=rs.body;
					}
			);
		},
		mounted(){
			$.ajax({
				url:"<%=basePath %>/getOwnAuthorityId.do",
				type:"post",
				dataType:"json",
				data:{
					"userId":$("input[name=userId]").val()
				},
				success:function(rs){
					for(i in rs){
						var menuId = rs[i];
						$("input[value="+menuId+"]").prop("checked","checked");
					}
				}
			});
		}
	});
</script>
</body>
</html>
