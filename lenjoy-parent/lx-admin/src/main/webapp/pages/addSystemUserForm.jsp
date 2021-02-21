<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort() +request.getContextPath()+"/";
%>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath %>">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>添加后台账号信息表单页面</title>
    <script type="text/javascript" src="<%=basePath %>/static/js/easyui/jquery.min.js"></script>
</head>
<body>
<center style="padding-top: 30px">
    <form id="ff" method="post" action="javascript:void(0)">
        <table cellpadding="5">
            <tr>
                <td>账号:</td>
                <td><input id="a1" type="text" name="username"></input></td>
            </tr>
            <tr>
                <td>密码:</td>
                <td><input id="a2" type="text" name="pwd" value="123456"></input></td>
            </tr>
            <tr>
                <td>授权:</td>
                <td>
                    <ul id="abc"></ul>
                </td>
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

<script type="text/javascript">
    $(function(){
        //加载权限树
        $('#abc').tree({
            url:'<%=basePath %>/getAuthority.do',
            checkbox:true
        });

        /*添加触发ajax*/
        $("#add").click(function(){
            var dms = $('#abc').tree("getChecked",['indeterminate','checked']);
            var menuIds = "";
            for(var i in dms){
                menuIds+=dms[i].id+",";
            }
            $.ajax({
                url:"<%=basePath %>/addSystemUser.do",
                type:"post",
                dataType:"json",
                data:{
                    "username":$("#ff input[name=username]").val(),
                    "pwd":$("#ff input[name=pwd]").val(),
                    "menuIds":menuIds
                },
                success:function(rs){
                    alert(rs)
                }
            });
        });

        /*重置添加内容*/
        $("#rs").click(function(){
            $("#ff input[type=text]").val("");
            $("#a1").focus();
        });

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
            validType: 'length[1,10]'
        });
    });
</script>
</body>
</html>
