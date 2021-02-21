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
    <style type="text/css">
        .c1{
            width: 100px;
            height:100px;
            background: red;
        }
        .c2{
            width: 100px;
            height:100px;
            background: green;
        }
    </style>
</head>
<body>
    <%--v-once、v-bind、v-if、v-show--%>
    <div id="box1">
        <input type="text" v-model="bookName">
        <span v-once>{{bookName}}</span>
        <hr/>
        <button @click="color='c1'">背景色红色</button><button @click="color='c2'">背景色绿色</button>
        <div v-bind:class="color" style="width: 100px;height: 100px;border:solid 1px red"></div>
        <hr/>
        <span v-if="age>18">可以学习Java视频</span>
        <span v-else>不可以学习Java视频</span>
        <hr>
        <h1>演示v-show指令</h1>
        <span v-show="age>18">Java视频</span>
    </div>
    <script type="text/javascript">
        const vue1 = new Vue({
            el:"#box1",
            data:{
                bookName:"Java课程全套",
                color:"",
                age:18
            }
        });
    </script>

    <%--v-for--%>
    <div id="box2">
        <ul>
            <%--<li v-for="i in 10">{{i}}</li>--%>
            <%--遍历map集合--%>
            <%--<li v-for="(v,k,i) in book1">{{i+1}}:{{k}}:{{v}}</li>--%>
            <li v-for="(map,i) in books" v-if="i%2==0" style="background: red">{{i}}:{{map.bookName}}:{{map.price}}:{{map.num}}:{{map.publisherName}}</li>
            <li v-else style="background: green">{{i}}:{{map.bookName}}:{{map.price}}:{{map.num}}:{{map.publisherName}}</li>
        </ul>
    </div>
    <script type="text/javascript">
        const vue2 = new Vue({
            el:"#box2",
            data:{
                book1:{"bookName":"Spring春天来了","price":99.9,"num":100,"publisherName":"清华大学出版社"},
                books:[
                    {"bookName":"Spring春天来了","price":99.9,"num":100,"publisherName":"清华大学出版社"},
                    {"bookName":"Java从入门到精通","price":69.9,"num":80,"publisherName":"北京大学出版社"},
                    {"bookName":"Vue好简单","price":100,"num":90,"publisherName":"武汉大学出版社"}
                ]
            }
        });
    </script>

    <%--computed属性与vue的生命周期--%>
    <div id="box3">
        请输入书名：<input type="text" v-model="bookName"><br/>
        <span v-text="bN"></span>
    </div>
    <script type="text/javascript">
        const vue3 = new Vue({
            el:"#box3",
            data:{
                bookName:"Java"
            },
            computed:{
                bN(){
                    return this.bookName+"-清华大学出版社";
                }
            }
        });
    </script>
</body>
</html>
