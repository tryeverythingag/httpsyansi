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
        <button @click="color='c1'">背景色红色</button><button @click="color='c2'">背景色绿色</button>
        <div v-bind:class="color" style="width: 100px;height: 100px;border:solid 1px red"></div>
    </div>
    <script type="text/javascript">
        const vue1 = new Vue({
            el:"#box1",
            data:{
                color:"",
                bookName:""
            },
            computed:{
                bN(){return this.bookName+"-清华大学出版社"}
            },
            methods:{
                aa(){},bb(){}
            },
            beforeCreate(){
                console.log("beforeCreate......."+this.$el);
                console.log("beforeCreate......."+this.$data);
                console.log("beforeCreate......."+this.$computed);
                console.log("beforeCreate......."+this.$methods);
            },
            created(){
                console.log("created......."+this.$el);
                console.log("created......."+this.$data);
                console.log("created......."+this.$computed);
                console.log("created......."+this.$methods);
            },
            beforeMount(){
                console.log("beforeMount......."+this.$el);
                console.log("beforeMount......."+this.$data);
                console.log("beforeMount......."+this.$computed);
                console.log("beforeMount......."+this.$methods);
            },
            mounted(){
                console.log("mounted......."+this.$el);
                console.log("mounted......."+this.$data);
                console.log("mounted......."+this.$computed);
                console.log("mounted......."+this.$methods);
            },
            beforeUpdate(){
                console.log("beforeUpdate......."+this.$el);
                console.log("beforeUpdate......."+this.$data);
                console.log("beforeUpdate......."+this.$computed);
                console.log("beforeUpdate......."+this.$methods);
            },
            updated(){
                console.log("updated......."+this.$el);
                console.log("updated......."+this.$data);
                console.log("updated......."+this.$computed);
                console.log("updated......."+this.$methods);
            },
            beforeDestroy(){
                console.log("beforeDestroy......."+this.$el);
                console.log("beforeDestroy......."+this.$data);
                console.log("beforeDestroy......."+this.$computed);
                console.log("beforeDestroy......."+this.$methods);
            },
            destroyed(){
                console.log("beforeDestroy......."+this.$el);
                console.log("beforeDestroy......."+this.$data);
                console.log("beforeDestroy......."+this.$computed);
                console.log("beforeDestroy......."+this.$methods);
            }
        });
    </script>

</body>
</html>
