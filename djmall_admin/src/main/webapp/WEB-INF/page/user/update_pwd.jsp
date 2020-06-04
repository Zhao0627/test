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
			   userName: {
		       required: true,
                 //去重验证
                 remote: {
                   type: 'POST',
                   url: "<%=request.getContextPath()%>/user/findNameOrEmail",
                   data:{
                     userName:function() {
                       return $("#userName").val();
                     },
                     dataType:"json"
                   }
                 }
			   },
		     userEmail: {
		       required: true,
               //去重验证
               remote: {
                 type: 'POST',
                 url: "<%=request.getContextPath()%>/user/findNameOrEmail",
                 data:{
                   userEmail:function() {
                     return $("#userEmail").val();
                   },
                   dataType:"json"
                 }
               },
		       email:true
		     },
             userPwd: {
               required: true,
             },
             userLevel: {
               required: true,
             }
           },
		   messages: {
             userName: {
		       required: "请输入用户名!",
		      	  remote:"用户名重复!"
		     },
		   	userPwd: {
		       required: "请输入密码!",
		     },
             userLevel: {
               required: "请选择!",
             },
		    userEmail: {
		       required: "请输入邮箱!",
               remote:"邮箱重复!",
               email:"格式不正确"
		     }
		   }, 
		});
		}); 
		$.validator.setDefaults({
			submitHandler:function(){
			$.post("<%=request.getContextPath()%>/user/insert",
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
                      layer.msg("注册成功，前往邮箱激活", {
                        offset: '15px'
                        ,icon: 16
                        ,time: 1500
                      }, function(){
                        window.location.href="<%=request.getContextPath()%>/user/toLogin";
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
<script>
    $.idcode.setCode();

    $("#btns").click(function (){
        var IsBy = $.idcode.validateCode();
        alert(IsBy);
        console.log(IsBy);
    });
</script>
    <style>
        #v_container{
            width: 100px;
            height: 40px;
        }
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
      <div class="layadmin-user-login-box layadmin-user-login-body layui-form">
        <!-- 用户名 -->
        <div class="layui-form-item">
          <label class="layadmin-user-login-icon layui-icon layui-icon-username" for="userPhone"></label>
          <input type="text" name="userPhone" id="userPhone" lay-verify="userPhone" placeholder="手机号" class="layui-input">
        </div>

        <%-- 图形验证码 --%>
          <label class="lblVerification">验证码</label>
          <input type="text" id="Txtidcode" class="txtVerification" />
          <span id="idcode"></span>
          <div>
              <button type="button" id="btns">验证</button>
          </div>

        <!-- 密码 -->
        <div class="layui-form-item">
          <label class="layadmin-user-login-icon layui-icon layui-icon-password" for="userPwd"></label>
          <input type="password" name="userPwd" id="userPwd" lay-verify="pass" placeholder="密码" class="layui-input">
        </div>

        <div class="layui-form-item"  style="margin-left: -30px;white-space: nowrap;margin-left: -55px;">
              <label class="layui-form-label">级别:</label>
              <div class="layui-input-block">
                  <input name="userLevel" title="用户" type="radio" checked="" value="1">
                  <input name="userLevel" title="管理" type="radio" value="2">
              </div>
        </div>
        <div class="layui- form-item">
          <input type="submit" value="注 册" class="layui-btn layui-btn-fluid" />
        </div>
      </div>
    </div>
  </div>
</form>
</body>

</html>