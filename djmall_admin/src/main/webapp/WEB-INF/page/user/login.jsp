<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>登入 - 点金教育DJmall项目</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/layui/css/admin.css" media="all">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/layui/css/login.css" media="all">
</head>
<body>
    <form id="fm">
        <div class="layadmin-user-login layadmin-user-display-show" id="LAY-user-login" style="display: none;">

            <div class="layadmin-user-login-main">
                <div class="layadmin-user-login-box layadmin-user-login-body layui-form">
                    <div style="color: green;font-size: 45px;" align="left"><b>©DJ-mall</b></div><br/><br>
                    <div class="layui-form-item">
                        <label class="layadmin-user-login-icon layui-icon layui-icon-username" for="LAY-user-login-username"></label>
                        <input type="text" name="userName" id="LAY-user-login-username" lay-verify="required" placeholder="用户名/手机号/邮箱" onblur="getUserSalt(this.value)" class="layui-input">
                    </div>
                    <div class="layui-form-item">
                        <label class="layadmin-user-login-icon layui-icon layui-icon-password" for="LAY-user-login-password"></label>
                        <input type="password" name="userPwd" id="LAY-user-login-password" lay-verify="required" placeholder="密码" class="layui-input">
                    </div>

                    <div class="layui-form-item" style="margin-bottom: 20px;">
                        <input type="checkbox" name="remember" lay-skin="primary" title="记住密码">
                        <a href="<%=request.getContextPath()%>/auth/user/toForgetPwd" class="layadmin-user-jump-change layadmin-link" style="margin-top: 7px;">忘记密码？</a>
                    </div>
                    <div class="layui-form-item">
                        <button class="layui-btn layui-btn-fluid" type="button" onclick="login()" lay-submit lay-filter="LAY-user-login-submit">登 入</button>
                    </div>

                    <input type="hidden" name="salt" id="salt" value="">
                    <input type="hidden" name="phone" id="phone" value="">
                    <input type="hidden" name="userId" id="userId" value="">
                    <input type="hidden" name="userPwd1" id="userPwd1" value="">

                    <div class="layui-trans layui-form-item layadmin-user-login-other">
                        <label>社交账号登入</label>
                        <a href="javascript:;"><i class="layui-icon layui-icon-login-qq"></i></a>
                        <a href="javascript:;"><i class="layui-icon layui-icon-login-wechat"></i></a>
                        <a href="javascript:;"><i class="layui-icon layui-icon-login-weibo"></i></a>

                        <a href="<%=request.getContextPath()%>/auth/user/toInsert" class="layadmin-user-jump-change layadmin-link">还没有账号？免费注册</a>
                    </div>
                </div>
            </div>

            <div class="layui-trans layadmin-user-login-footer">
                <p>© 2019® <a href="http://www.dianit.cn/" target="_blank">点金教育Djmall</a></p>
                <p>
                    <span><a href="http://www.layui.com/admin/#get" target="_blank">VIP授权</a></span>
                    <span><a href="http://www.layui.com/admin/pro/" target="_blank">在线售出</a></span>
                    <span><a href="http://localhost:8085/platform/shop/toIndexShow" target="_blank">前往djmall官网</a></span>
                </p>
            </div>

        </div>
    </form>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/layui/layui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/layui/layui.all.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/md5-min.js"></script>
<script type="text/javascript">
    //判断当前窗口路径与加载路径是否一致。
    if(window.top.document.URL != document.URL){
        //将窗口路径与加载路径同步
        window.top.location = document.URL;
    }


    function getUserSalt(userName){
        if(userName != ""){
            $.post("<%=request.getContextPath()%>/auth/user/getUserSalt",
                {"userName":userName},
                function(data){
                    if(data.code == 200){
                        $("#salt").val(data.data.salt);
                        $("#phone").val(data.data.userPhone);
                        $("#userId").val(data.data.userId);
                        $("#userPwd1").val(data.data.userPwd);
                    }
                })
        }
    }

    function login(){
        var bb = $("#LAY-user-login-password").val()=='';
        var aa = $("#LAY-user-login-username").val()=='';
        if (aa || bb) {
            layer.msg('必填选项不得为空', {
                offset: '15px'
                ,icon: 16
                ,time: 3000
            }, function(){
                window.location.href="<%=request.getContextPath()%>/auth/user/toLogin";
            });
            return;
        }
        var userPwd = $("#LAY-user-login-password").val();
        var pwd = md5(userPwd);
        if ($("#userPwd1").val()===pwd){
            layer.msg("暂判定您的密码不安全，请前往修改密码", {
                offset: '15px'
                ,icon: 16
                ,time: 1500
            }, function(){
                window.location.href="<%=request.getContextPath()%>/auth/user/toUpdatePwd?userPhone="+$("#phone").val();
            });
            return;
        }
        var salt =  $("#salt").val();
        var newPwd = md5(pwd + salt);
        $("#LAY-user-login-password").val(newPwd);
        $.post("<%=request.getContextPath()%>/auth/user/login",
            $("#fm").serialize(),
            function(data){
                if(data.code!="200") {
                    layer.msg(data.msg, {
                        offset: '15px'
                        ,icon: 16
                        ,time: 1500
                    }, function(){
                        window.location.href="<%=request.getContextPath()%>/auth/user/toLogin";
                    });
                }
                else{
                    layer.msg(data.msg, {
                        offset: '15px'
                        ,icon: 16
                        ,time: 1500
                    }, function(){
                        window.location.href="<%=request.getContextPath()%>/index/toIndex";
                    });
                }
            })
    }
</script>
</body>

</html>