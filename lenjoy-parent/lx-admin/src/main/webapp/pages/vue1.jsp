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
    <script src="https://cdn.jsdelivr.net/npm/vue"></script>
    <script src="https://cdn.jsdelivr.net/npm/vue-resource@1.5.1"></script>
</head>
<body>
    <%--插值表达式与指令--%>
	<div id="box1">
        <span>{{uName}}</span><br/>
        <span v-text="uName"></span><br/>
        <span v-html="uName"></span><br/>
        <input type="text" v-model="uName">
    </div>
    <script type="text/javascript">
        var vue1 = new Vue({
            el:"#box1",
            data:{
                uName:"<h1 style='color:red'>王二麻子</h1>"
            }
        });
    </script>

    <hr/>
    <%--v-model指令--%>
    <div id="box2">
        <input type="text" v-model="num"><button v-on:click="num++">+</button>
        <h1>请选择您的兴趣爱好：</h1>
        <input type="checkbox" v-model="hobby" value="1">足球<br/>
        <input type="checkbox" v-model="hobby" value="2">篮球<br/>
        <input type="checkbox" v-model="hobby" value="3">游泳<br/>
        <input type="checkbox" v-model="hobby" value="4">跳舞<br/>
        <h1>您的兴趣爱好为：</h1>
        <span>{{hobby.join(",")}}</span>
    </div>
    <script type="text/javascript">
        var vue2 = new Vue({
            el:"#box2",
            data:{
                num:0,
                hobby:[]
            }
        });
    </script>

    <hr/>
    <%--事件阻止与事件冒泡--%>
    <div id="box3">
        <div v-on:click="aa" style="width: 100px;height: 100px;background-color: red">
            <button @click.stop="bb">点我</button>
        </div>
        <a href="http://www.baidu.com" @click.prevent="cc">百度一下</a>
    </div>
    <script type="text/javascript">
        var vue3 = new Vue({
            el:"#box3",
            methods:{
                aa(){
                    console.log("我是div");
                },
                bb(){
                    console.log("我是button");
                },
                cc(){
                    console.log("百度一下");
                }
            }
        });
    </script>


</body>
</html>
