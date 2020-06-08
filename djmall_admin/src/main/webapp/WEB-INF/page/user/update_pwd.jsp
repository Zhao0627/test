<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/idcode/jquery.idcode.css"  media="all"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/layui/css/admin.css" media="all">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/layui/css/login.css" media="all">
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/layui/layui.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/idcode/jquery.idcode.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/validate/jquery.validate.min.js"></script>
    <%--  <script type="text/javascript" src="<%=request.getContextPath()%>/res/layer/layui-v2.3.0/layui/lay/modules/jquery.js"></script>--%>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/layui/layui.all.js"></script>

    <script>
        $().ready(function() {
// 在键盘按下并释放及提交后验证提交表单
            $("#fm").validate({
                errorElement:'span',
                errorPlacement: function(error, element) {
                    error.appendTo(element.parent());
                },
                rules: {
                    userPwd: {
                        required: true,
                    },
                    userPwd1: {
                        required: true,
                        equalTo: "#userPwd"

                    }
                },
                messages: {
                    userPwd: {
                        required: "请输入密码!",
                    },
                    userPwd1: {
                        required: "请确认密码!",
                        equalTo:"密码不相同"
                    },
                },
            });
        });
        $.validator.setDefaults({
            submitHandler:function(){
                $.post("<%=request.getContextPath()%>/auth/user/updatePwd",
                    $("#fm").serialize(),
                    function(data){
                        if(data.code!='200') {
                            layer.msg(data.msg, {
                                offset: '15px'
                                ,icon: 16
                                ,time: 1500
                            }, function(){
                            });
                        }
                        layer.msg("修改成功，请前往登录", {
                            offset: '15px'
                            ,icon: 16
                            ,time: 1500
                        }, function(){
                            window.location.href="<%=request.getContextPath()%>/auth/user/toLogin";
                        });
                    })
            }
        })
        $(function () {
            layui.use('form',function(){
                let form = layui.form;
                form.render();
            });
        })
    </script>
    <style>
        span.error {
            font-size:11px;
            color: red;
        }
    </style>
</head>
<body>
<form id="fm" >
    <div class="layadmin-user-login layadmin-user-display-show" id="LAY-user-login" style="display: none;">
        <div class="layadmin-user-login-main">
            <div class="layadmin-user-login-box layadmin-user-login-header">
                <h2>用户修改密码</h2>
                <p>欢迎小可爱加入</p>
            </div>
            <input type="hidden" name="userId" value="${userId}" />
            <div class="layadmin-user-login-box layadmin-user-login-body layui-form">
                <!-- 密码 -->
                <div class="layui-form-item">
                    <label class="layadmin-user-login-icon layui-icon layui-icon-password" for="userPwd"></label>
                    <input type="password" name="userPwd" id="userPwd" lay-verify="pass" placeholder="新密码" class="layui-input">
                </div>

                <!-- 确认密码 -->
                <div class="layui-form-item">
                    <label class="layadmin-user-login-icon layui-icon layui-icon-password" for="userPwd1"></label>
                    <input type="password" name="userPwd1" id="userPwd1" lay-verify="required" placeholder="确认新密码" class="layui-input">
                </div>


                <div class="layui- form-item">
                    <input type="submit" value="确认修改" class="layui-btn layui-btn-fluid" />
                </div>
            </div>
        </div>
    </div>
</form>
</body>

</html>