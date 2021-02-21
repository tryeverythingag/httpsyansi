<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort() +request.getContextPath()+"/";
%>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath %>">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>new jsp</title>
    <%--引入vue--%>
    <script src="https://cdn.jsdelivr.net/npm/vue"></script>
    <script src="https://cdn.jsdelivr.net/npm/vue-resource@1.5.1"></script>
</head>
<body>
<h1>哈哈</h1>
<ul id="u">
    <li v-for="banner in bannerList">{{banner}}</li>
</ul>
<script type="text/javascript">
    var vue1 = new Vue({
        el:"#u",
        data: {
            bannerList: []
        },
        mounted(){
            this.$http.post("http://localhost:7073//findBannersByConsumer.do").then(
                function(rs){
                    this.bannerList=rs.body;
                }
            );
        }
    });
</script>
</body>
</html>
