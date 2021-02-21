<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort() +request.getContextPath()+"/";
%>
<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath %>">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>正在结算</title>
    <script type="text/javascript" src="<%=basePath %>/static/js/jquery-1.8.2.min.js"></script>
</head>
<body>
    <h1>正在结算中...${param.orderNo}</h1>
    <input type="hidden" name="orderNo" value="${param.orderNo}">
    <script type="text/javascript">
        function aaa(){
            $.ajax({
                url:"http://localhost:9093/checkOrder.do",
                type:"POST",
                dataType:"json",
                data:{
                    "orderNo": $("input[name=orderNo]").val()
                },
                success:function(rs){
                    if(rs){//结算模块已经处理完毕
                        window.location.href="http://localhost:8085/static/pages/seckillFinish.html";
                    }else{
                        window.location.href="http://localhost:8085/pages/cache.jsp?orderNo=${param.orderNo}";
                    }
                }
            });
        }
        window.setInterval('aaa()',2000);
    </script>
</body>
</html>