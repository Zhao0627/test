<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html style="height: 100%">
<head>
  <meta charset="utf-8">
  <title>注册 - 点金教育DJmall项目</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/static/layui/css/layui.css" media="all">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/static/layui/css/admin.css" media="all">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/static/layui/css/login.css" media="all">
<style type="text/css">
	span.error {
	  	font-size:11px;
	    color: red;
	}
</style>
</head>
<body style="height: 100%">
<form id="fm">
  <div class="layadmin-user-login layadmin-user-display-show" id="LAY-user-login" style="display: none;">
    <div class="layadmin-user-login-main">
      <div class="layadmin-user-login-box layadmin-user-login-header">
      </div>
      <div class="layadmin-user-login-box layadmin-user-login-body layui-form">
          <input type="hidden" name="activatedState" value="INACTIVE">
        <!-- 用户名 -->
        <div class="layui-form-item">
         <p>
          <label class="layadmin-user-login-icon layui-icon layui-icon-username" for="userName"></label>
          <input type="text" name="userName" id="userName" lay-verify="nickname" placeholder="用户名" class="layui-input">
         </p>
        </div>

        <!-- 昵称 -->
          <div class="layui-form-item">
              <label class="layadmin-user-login-icon layui-icon layui-icon-username" for="nickName"></label>
              <input type="text" name="nickName" id="nickName" lay-verify="nickname" placeholder="昵称" class="layui-input">
          </div>

        <!-- 密码 -->
        <div class="layui-form-item">
        <p>
          <label class="layadmin-user-login-icon layui-icon layui-icon-password" for="userPwd"></label>
          <input type="password" name="userPwd"  id="userPwd"  lay-verify="pass" placeholder="密码" class="layui-input">
        </p>
        </div>

        <!-- 确认密码 -->
          <div class="layui-form-item">
              <label class="layadmin-user-login-icon layui-icon layui-icon-password" for="userPwd1"></label>
              <input type="password" name="userPwd1" id="userPwd1" lay-verify="required" placeholder="确认密码" class="layui-input">
          </div>

          <%--手机号--%>
          <div class="layui-form-item">
              <label class="layadmin-user-login-icon layui-icon layui-icon-cellphone" for="userPhone"></label>
              <input type="text" name="userPhone" id="userPhone" lay-verify="phone" placeholder="手机号" class="layui-input">
          </div>

          <%--邮箱--%>
          <div class="layui-form-item">
              <label class="layadmin-user-login-icon layui-icon layui-icon-email" for="userEmail"></label>
              <input type="text" name="userEmail" id="userEmail" lay-verify="email" placeholder="邮箱" class="layui-input">
          </div>

          <%--级别--%>
          <div class="layui-form-item">
              <label>级别</label>
              <div style="margin-top: -28px;margin-left: 50px">
                  <c:forEach items="${role}" var="role">
                        <input type="radio" name="userLevel" value="${role.roleId}" lay-verify="email" class="layui-input" checked title="${role.roleName}">
                  </c:forEach>
              </div>
          </div>

          <%--性别--%>
          <div class="layui-form-item">
              <label>性别</label>
              <div style="margin-top: -28px;margin-left: 50px">
                  <c:forEach items="${basedataSex}" var="basedataSex">
                    <input type="radio" name="userSex" value="${basedataSex.code}" id="userSex" lay-verify="email" class="layui-input" checked title="${basedataSex.name}">
                  </c:forEach>
              </div>
          </div>

        <div class="layui-form-item">
          <input type="checkbox" name="agreement" lay-skin="primary" title="同意用户协议" checked>
        </div>
        
        <div class="layui- form-item">
          <button class="layui-btn layui-btn-fluid" type="submit">注 册</button>
        </div>
        <div class="layui-trans layui-form-item layadmin-user-login-other">
          <label>社交账号注册</label>
          <a href="javascript:;"><i class="layui-icon layui-icon-login-qq"></i></a>
          <a href="javascript:;"><i class="layui-icon layui-icon-login-wechat"></i></a>
          <a href="javascript:;"><i class="layui-icon layui-icon-login-weibo"></i></a>
          
          <a href="<%=request.getContextPath()%>/auth/user/toLogin" class="layadmin-user-jump-change layadmin-link layui-hide-xs">用已有帐号登入</a>
          <a href="<%=request.getContextPath()%>/auth/user/toLogin" class="layadmin-user-jump-change layadmin-link layui-hide-sm layui-show-xs-inline-block">登入</a>
        </div>
      </div>
    </div>
    <div class="layui-trans layadmin-user-login-footer">
      <p>© 2019 <a href="http://www.layui.com/" target="_blank">1392495684QQ.com</a></p>
      <p>
        <span><a href="http://www.layui.com/admin/#get" target="_blank">获取授权</a></span>
        <span><a href="http://www.layui.com/admin/pro/" target="_blank">在线演示</a></span>
        <span><a href="http://www.layui.com/admin/" target="_blank">前往官网</a></span>
      </p>
    </div>
  <input type="hidden" name="userLevel" value="0" /><br>
  </div>
</form>
  <script type="text/javascript" src="<%=request.getContextPath()%>/static/layui/layui.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-1.12.4.min.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/static/layui/layui.all.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/static/validate/jquery.validate.min.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/static/validate/messages_zh.min.js"></script>
</body>
<script type="text/javascript">
	$.validator.setDefaults({
	   		 submitHandler: function() {

	   		     if ($("#userName").val()==$("#nickName").val()){
	   		         layer.msg("昵称和名称不得一致！");
	   		         return
                 }
				$.post(
						"<%=request.getContextPath()%>/auth/user/insert",
						$("#fm").serialize(),
						function(data){
							layer.msg(data.msg, {
				            	offset: '15px'
				            	,icon: 16
				            	,time: 1500
				          	},function(){
								window.location.href="<%=request.getContextPath()%>/auth/user/toLogin"
				          	});
						})
		}
	})

    jQuery.validator.addMethod("isPhone", function(value, element) {
        var length = value.length;
        var mobile = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1}))+\d{8})$/;
        return this.optional(element) || (length == 11 && mobile.test(value));
    }, "请填写正确的手机号码");//可以自定义默认提示信息

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
	                        url: "<%=request.getContextPath()%>/auth/user/findUserNamePhoneEmail",
	                        data:{
	                        	userName:function() {
	                                return $("#userName").val();
	                            },
	                            dataType:"json"
	                        }
	                    }
		      },

                userPhone: {
		        required: true,
		      //去重验证
                    isPhone:true,
		     		 remote: {
	                        type: 'POST',
	                        url: "<%=request.getContextPath()%>/auth/user/findUserNamePhoneEmail",
	                        data:{
	                        	userPhone:function() {
	                                return $("#userPhone").val();
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
	                        url: "<%=request.getContextPath()%>/auth/user/findUserNamePhoneEmail",
	                        data:{
	                        	userEmail:function() {
	                                return $("#userEmail").val();
	                            },
	                            dataType:"json"
	                        }
	                    }
		      },

                nickName: {
		        required: true,
		      },

		    	userPwd: {
		        required: true,
		      },

                userPwd1: {
                    required: true,
                    equalTo: "#userPwd"
                }

		    },

		    messages: {

		    	userPhone: {
		        required: "请输入手机号!",
		       	  remote:"手机号重复!",
                    isPhone:"请填写正确的手机号码"

		      },
                userName: {
		        required: "请输入用户名!",
		       	  remote:"用户名重复!"
		      },
                userEmail: {
		        required: "请输入Email!",
		       	  remote:"Email重复!"
		      },
                nickName: {
		        required: "请输入昵称!",
		      },
		    	userPwd: {
		        required: "请输入密码!",
		      },
                userPwd1: {
		        required: "请输入密码!",
                 equalTo:"密码不相同"
		      },
		    }, 
		});
	}); 
	

</script>
</html>