<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/layui/css/layui.css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/layui/layui.all.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/md5-min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/layui/layui.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/cookie.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/token.js"></script>
    <style type="text/css">
        .button {
            background-color: #4CAF50;
            border: none;
            color: white;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            width: 100px;
            height: 35px;
        }
    </style>
    <script type="text/javascript">

        /* 验证码 */
        $(function () {
            var oBtn = $("#btn");
            var str = "";
            var len = str.length;
            len = 5;
            var arr = new Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", 'X', "Y", 'Z',
                "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z");
            for (var i = 0; i < len; i++) {
                var index = Math.floor(Math.random()*62);
                str+=arr[index];
            }
            oBtn.val(str);
            $("#btn").click(function(){
                var str1 = "";
                var len1 = str1.length;
                len1 = 5;
                var arr1 = new Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", 'X', "Y", 'Z',
                    "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z");
                for (var i = 0; i < len1; i++) {
                    var index1 = Math.floor(Math.random()*62);
                    str1+=arr1[index1];
                }
                $("#btn").val(str1);
            });
        })

        /* 获取验证码 */
        $(function () {
            layui.use('form',function(){
                let form = layui.form;
                form.render();
            });
            $("#LAY-user-getsmscode").click(function(){
                if ($("#userPhone").val()===""){layer.msg("填写手机号"); return;}
                if ($("#yanzhengma").val()===""){layer.msg("请填写验证码"); return;}
                $.post("<%=request.getContextPath()%>/consumer/getPhoneCode",
                    {"userPhone":$("#userPhone").val()},
                    function (data) {
                        console.log(data.msg)
                        $("#dataCode").val(data.msg);
                    })
            })
        })

        /*验证码*/
        function yanzheng(obj){
            var btn = $("#btn").val();
            if (btn!==obj){
                return;
            }
        }

        layui.use('element', function () {
            var element = layui.element;
            element.on('tab(filter)', function (data) {
                console.log(this); //当前Tab标题所在的原始DOM元素
                console.log(data.index); //得到当前Tab的所在下标
                console.log(data.elem); //得到当前的Tab大容器
            });
        });

        /*获取盐*/
        function getUserSalt(userName){
            if(userName != ""){
                $.post("<%=request.getContextPath()%>/consumer/getUserSalt",
                    {"userName":userName},
                    function(data){
                        if(data.code == 200){
                            $("#salt").val(data.data.salt);
                        }
                    })
            }
        }

        function login(){
            if ($("#LAY-user-login-username").val()==""){
                layer.msg("请输入用户名/手机号/邮箱");
                return;
            }
            if ($("#LAY-user-login-password").val()==""){
                layer.msg("请输入密码");
                return;
            }
            var salt = $("#salt").val();
            var pwd = md5($("#LAY-user-login-password").val());
            var newPwd = md5(pwd + salt);
            $(":password").val(newPwd);
            $.post("<%=request.getContextPath()%>/consumer/login", $("#loginform").serialize(), function (result) {
                if (result.code == 200) {
                    set_login(result.data.token, result.data.nickName);
                    // 刷新父页面
                    parent.window.location.reload();
                } else {
                    $(":password").val("");
                    layer.msg(result.msg);
                   /* parent.window.location.reload();*/
                }
            });
        };
        
        function phoneLogin() {
            if ($("#LAY-user-login-vercode").val()!=$("#dataCode").val()){
                layer.msg("验证码输入有误");
                return;
            }
            $.post("<%=request.getContextPath()%>/consumer/phoneLogin",
                $("#phoneFm").serialize(),
                function (result) {
                    if (result.code == 200) {
                        set_login(result.data.token, result.data.nickName);
                        // 刷新父页面
                        parent.window.location.reload();
                    } else {
                        $(":password").val("");
                        layer.msg(result.msg);
                    }
            })
        }
    </script>
</head>
<body>

<div class="layui-tab layui-tab-card">
    <ul class="layui-tab-title" lay-filte="login">
        <li class="layui-this">账户普通</li>
        <li>手机登陆</li>
    </ul>
    <div class="layui-tab-content">
        <div class="layui-tab-item layui-show">
            <input type="hidden" id="salt">
            <form id="loginform">
                <input type="text" name="userName" id="LAY-user-login-username" lay-verify="required" placeholder="用户名/手机号/邮箱" style="width: 248px;" onblur="getUserSalt(this.value)" class="layui-input"><br>
                <input type="password" name="userPwd" id="LAY-user-login-password" lay-verify="required" style="width: 248px;" placeholder="密码" class="layui-input"><br>
                <button class="layui-btn layui-btn-fluid" type="button" onclick="login()" lay-submit lay-filter="LAY-user-login-submit" style="width: 248px;">登 入</button>
            </form>
        </div>
        <div class="layui-tab-item">
            <form id="phoneFm">
                <div class="layadmin-user-login-box layadmin-user-login-body layui-form">
                    <input type="text" name="userPhone" id="userPhone" lay-verify="phone" placeholder="手机号" class="layui-input" style="width: 248px;"><br>
                    <input type="text" style="height: 31px;width: 140px" placeholder="图形验证码" id="yanzhengma" onblur="yanzheng(this.value)"/>
                    <input type="button" value="" id="btn" class="button" style="" /><br><br>
                    <div style="space:nowrap;">
                        <input type="text" name="vercode" id="LAY-user-login-vercode" lay-verify="required" placeholder="验证码" style="height: 31px;width: 140px">
                        <button type="button" class="layui-btn layui-btn-primary layui-btn-fluid" id="LAY-user-getsmscode" style="width: 100px;">获取验证码</button>
                    </div><br>
                </div>
                <button class="layui-btn layui-btn-fluid" type="button" onclick="phoneLogin()" lay-submit lay-filter="LAY-user-login-submit" style="width: 248px;">登 入</button>
                <input type="text" id="dataCode" value="" />
            </form>
        </div>
    </div>
</div>
</body>
</html>