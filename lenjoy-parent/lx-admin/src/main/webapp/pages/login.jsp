<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<head>
	<base href="<%=basePath %>">
	<meta charset="UTF-8">
	<title>后台系统登录</title>
	<script src="<%=basePath %>/static/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
	<link rel="stylesheet" href="<%=basePath %>/static/css/jigsaw.css">
	<script src="<%=basePath %>/static/js/layui/layui.js" charset="utf-8"></script>
	<script type="text/javascript" src="<%=basePath %>/static/js/xadmin.js"></script>
	<script src="<%=basePath %>/static/js/jigsaw.js"></script>
	<style>
		.container {
			width: 310px;
			margin: 100px auto;
		}

		#msg {
			width: 100%;
			line-height: 40px;
			font-size: 14px;
			text-align: center;
		}

		a:link,
		a:visited,
		a:hover,
		a:active {
			margin-left: 100px;
			color: #0366D6;
		}

		body {
			background: #ebebeb;
			font-family: "Helvetica Neue", "Hiragino Sans GB", "Microsoft YaHei", "\9ED1\4F53", Arial, sans-serif;
			color: #222;
			font-size: 12px;
		}

		* {
			padding: 0px;
			margin: 0px;
		}

		.top_div {
			background: #008ead;
			width: 100%;
			height: 400px;
		}

		.ipt {
			border: 1px solid #d3d3d3;
			padding: 10px 10px;
			width: 290px;
			border-radius: 4px;
			padding-left: 35px;
			-webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
			box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
			-webkit-transition: border-color ease-in-out .15s, -webkit-box-shadow ease-in-out .15s;
			-o-transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s;
			transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s
		}

		.ipt:focus {
			border-color: #66afe9;
			outline: 0;
			-webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075), 0 0 8px rgba(102, 175, 233, .6);
			box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075), 0 0 8px rgba(102, 175, 233, .6)
		}

		.u_logo {
			background: url("<%=basePath %>/static/images/username.png") no-repeat;
			padding: 10px 10px;
			position: absolute;
			top: 43px;
			left: 57px;
		}

		.p_logo {
			background: url("<%=basePath %>/static/images/password.png") no-repeat;
			padding: 10px 10px;
			position: absolute;
			top: 12px;
			left: 57px;
		}

		.b_logo {
			background: url("<%=basePath %>/static/images/ldj.png") no-repeat;
			padding: 10px 10px;
			position: absolute;
			top: 12px;
			left: 57px;
		}

		a {
			text-decoration: none;
		}

		.tou {
			background: url("<%=basePath %>/static/images/tou.png") no-repeat;
			width: 97px;
			height: 92px;
			position: absolute;
			top: -87px;
			left: 140px;
		}

		.left_hand {
			background: url("<%=basePath %>/static/images/left_hand.png") no-repeat;
			width: 32px;
			height: 37px;
			position: absolute;
			top: -38px;
			left: 150px;
		}

		.right_hand {
			background: url("<%=basePath %>/static/images/right_hand.png") no-repeat;
			width: 32px;
			height: 37px;
			position: absolute;
			top: -38px;
			right: -64px;
		}

		.initial_left_hand {
			background: url("<%=basePath %>/static/images/hand.png") no-repeat;
			width: 30px;
			height: 20px;
			position: absolute;
			top: -12px;
			left: 100px;
		}

		.initial_right_hand {
			background: url("<%=basePath %>/static/images/hand.png") no-repeat;
			width: 30px;
			height: 20px;
			position: absolute;
			top: -12px;
			right: -112px;
		}

		.left_handing {
			background: url("<%=basePath %>/static/images/left-handing.png") no-repeat;
			width: 30px;
			height: 20px;
			position: absolute;
			top: -24px;
			left: 139px;
		}

		.right_handinging {
			background: url("<%=basePath %>/static/images/right_handing.png") no-repeat;
			width: 30px;
			height: 20px;
			position: absolute;
			top: -21px;
			left: 210px;
		}
	</style>
</head>

<body onload="loadTopWindow()">
<div class="top_div"></div>
<div style="width: 400px; height: 290px; margin: auto auto; background: #ffffff; text-align: center; margin-top: -200px; border: 1px solid #e7e7e7">
	<div style="width: 165px; height: 96px; position: absolute">
		<div class="tou"></div>
		<div id="left_hand" class="initial_left_hand"></div>
		<div id="right_hand" class="initial_right_hand"></div>
	</div>
	<form action="/login.do" method="post">
		<!--<p style="padding: 30px 0px 10px 0px; position: relative; margin-top:0px;margin-bottom:-30px;">
				<span></span>
				<input class="ipt acc" type="hidden" id="tip" value="${flag} " style="background-color:orange;color:red">
			</p>-->
		<p style="padding: 30px 0px 10px 0px; position: relative;">
			<span class="u_logo"></span> <input  class="ipt acc" pattern=".{3,12}" required="required" type="text" name="username" id="account" placeholder="3-12位的数字、字母、下划线的组合">
		</p>
		<p style="position: relative;">
			<span class="p_logo"></span> <input id="password" class="ipt" pattern=".{6,12}" type="password" name="pwd" placeholder="6-12位的数字、字母、下划线的组合">
		</p>
		<p style="position: relative; margin-top: 15px;">
			<span class="b_logo"></span> <input id="" class="ipt tt" type="button" value="点击按钮进行验证" style="padding-right: 150px; background-color: white; color: gray">
		</p>
		<%--<span style="float: left; margin-top: 10px; margin-left: 55px; color: #ccc">
				<input type="checkbox" name="autoLogin" value="1">7天免登录</span>
		<span style="float: left; margin-left: 50px; margin-top: 10px;">
				<a href="#" style="color: #ccc;">忘记密码?</a></span>--%>
		<div class="container" style="margin-top: 5px; margin-left: 33px；margin-bottom:10px; display: none; position: absolute; z-index: 999; right: 476px; bottom: -35px;">
			<div id="captcha" style="position: relative; bottom: 170px;"></div>
		</div>
		<div style="height: 50px; line-height: 37px; border-top: 1px solid #e7e7e7; margin-top: 30px;">
			<p style="margin: 0px px 0px 0px;">
					<span style="float: right; margin-right: 55px;"> <input
							id="tijiao" class="ipt" type="submit" value="立即登录 —>"
							style="padding-right: 50px; background-color: #008EAD; font-weight: bold; color: #FFF;"
							disabled="disabled">
					</span>
			</p>
		</div>
	</form>
</div>
<script>
    function loadTopWindow() {
        if(window.top != null && window.top.document.URL != document.URL) {
            window.top.location = document.URL;
        }
    }

    /*$(function() {
        var val = $("#tip").val().length;
        if(val > 3) {
            $("#tip").attr("type", "text");
            $('#tip').delay(3000).hide(0);
        }
    });*/

    $(function() {
        $("#tijiao").css("disabled", "disabled");
        $(".tt").css("disabled", "disabled")

        $("input[name=account]").change(function() {
            $("input[name=password]").val('');
        });
        $("input[name=account]").blur(function() {
            var nameVal = $("input[name=account]").val();
            if(nameVal == '') {
                layer.tips('账号不能为空', '#account');
            }
        });
        $("input[name=password]").blur(function() {
            var passwordVal = $("input[name=password]").val();
            if(passwordVal == '') {
                layer.tips('密码不能为空', '#password');

            }
        });
        $(".tt").click(function() {
            var nameVal = $("input[name=account]").val();
            var passwordVal = $("input[name=password]").val();
            if(nameVal != '' && passwordVal != '') {
                $(".container").css("display", "block");
                $(".tt").css("disabled", "disabled");
            } else {
                layer.tips('账号和密码不能为空', '.tt');
            }
        })
    });

    jigsaw.init(document.getElementById('captcha'), function() {
        $(".tt").css("background", "#008EAD");
        $(".tt").css("color", "white");
        $(".tt").val("验证成功");
        $(".container").css("display", "none");
        $(".tt").attr("disabled", "disabled");
        var nameVal = $("input[name=account]").val();
        var passwordVal = $("input[name=password]").val();
        if(nameVal != '' && passwordVal != '') {
            $("input[type=submit]").removeAttr("disabled", "disabled");
        } else {
            layer.tips('账号和密码不能为空', '#tijiao');
        }
    });

    $(function() {
        //得到焦点
        $("#password").focus(function() {
            $("#left_hand").animate({
                left: "150",
                top: " -38"
            }, {
                step: function() {
                    if(parseInt($("#left_hand").css("left")) > 140) {
                        $("#left_hand").attr("class", "left_hand");
                    }
                }
            }, 2000);
            $("#right_hand").animate({
                right: "-64",
                top: "-38px"
            }, {
                step: function() {
                    if(parseInt($("#right_hand").css("right")) > -70) {
                        $("#right_hand").attr("class", "right_hand");
                    }
                }
            }, 2000);
        });
        //失去焦点
        $("#password").blur(function() {
            $("#left_hand").attr("class", "initial_left_hand");
            $("#left_hand").attr("style", "left:100px;top:-12px;");
            $("#right_hand").attr("class", "initial_right_hand");
            $("#right_hand").attr("style", "right:-112px;top:-12px");
        });
    });
</script>
</body>

</html>
