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
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/layui/css/admin.css" media="all">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/layui/css/login.css" media="all">
  <script type="text/javascript" src="<%=request.getContextPath()%>/static/layui/layui.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-1.12.4.min.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/static/validate/jquery.validate.min.js"></script>
<%--  <script type="text/javascript" src="<%=request.getContextPath()%>/res/layer/layui-v2.3.0/layui/lay/modules/jquery.js"></script>--%>
  <script type="text/javascript" src="<%=request.getContextPath()%>/static/layui/layui.all.js"></script>
<script>
    //可以自定义默认提示信息 手机号验证
    jQuery.validator.addMethod("isPhone", function(value, element) {
        var length = value.length;
        var mobile = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1}))+\d{8})$/;
        return this.optional(element) || (length == 11 && mobile.test(value));
    }, "请填写正确的手机号码");

    /*验证码*/
    function yanzheng(obj){
        var btn = $("#btn").val();
        if (btn!==obj){
            return;
        }

    }


$().ready(function() {
// 在键盘按下并释放及提交后验证提交表单
		$("#fm").validate({
          errorElement:'span',
		 errorPlacement: function(error, element) {
		     error.appendTo(element.parent());
		     },
		   rules: {
		     userPhone: {
		       required: true,
                 isPhone:true,
		     },
             userPwd: {
               required: true,
             }
           },
		   messages: {
             userPhone: {
		       required: "请输入手机号!",
                 isPhone:"请填写正确的手机号码"
		     },
		   	userPwd: {
		       required: "请输入密码!",
		     }
		   }, 
		});
		}); 
		$.validator.setDefaults({
			submitHandler:function(){
            if ($("#userPwd").val()!==$("#userPwd1").val()){
                layer.msg("密码不相同");
                return;
            }
            if ($("#LAY-user-login-vercode").val()!==$("#dataCode").val()){
                layer.msg("验证码输入有误");
                return;
            }
			$.post("<%=request.getContextPath()%>/auth/user/updatePwdByPhone",
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
                      layer.msg(data.msg, {
                        offset: '15px'
                        ,icon: 16
                        ,time: 1500
                      }, function(){
                        window.location.href="<%=request.getContextPath()%>/auth/user/toLogin";
                      });
                    })
	}
})
    var dataCode = "";
$(function () {
    layui.use('form',function(){
        let form = layui.form;
        form.render();
    });
    $("#LAY-user-getsmscode").click(function(){
        if ($("#userPhone").val()===""){layer.msg("填写手机号"); return;}
        if ($("#yanzhengma").val()===""){layer.msg("请填写验证码"); return;}
        $.post("<%=request.getContextPath()%>/auth/user/getPhoneCode",
            {"userPhone":$("#userPhone").val()},
            function (data) {
                $("#dataCode").val(data.data);
                alert(data.data);
        })
    })
})
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
</script>
    <style>
      span.error {
        font-size:11px;
        color: red;
      }
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
        <!-- 手机号 -->
          <div class="layui-form-item">
              <label class="layadmin-user-login-icon layui-icon layui-icon-cellphone" for="userPhone"></label>
              <input type="text" name="userPhone" id="userPhone" lay-verify="phone" placeholder="手机号" class="layui-input">
          </div>

        <%-- 图形验证码 --%>
          <label>图形验证码:</label>&nbsp;&nbsp;
          <input type="text" style="height: 31px;width: 140px" id="yanzhengma" onblur="yanzheng(this.value)"/>
          <input type="button" value="" id="btn" class="button" style="" /><br><br>
          <div class="layui-form-item">
              <div class="layui-row">
                  <div class="layui-col-xs7">
                      <label class="layadmin-user-login-icon layui-icon layui-icon-vercode" for="LAY-user-login-vercode"></label>
                      <input type="text" name="vercode" id="LAY-user-login-vercode" lay-verify="required" placeholder="验证码" class="layui-input">
                  </div>
                  <div class="layui-col-xs5">
                      <div style="margin-left: 10px;">
                          <button type="button" class="layui-btn layui-btn-primary layui-btn-fluid" id="LAY-user-getsmscode">获取验证码</button>
                      </div>
                  </div>
              </div>
          </div>
          <div class="layui-form-item">
              <label class="layadmin-user-login-icon layui-icon layui-icon-password" for="userPwd"></label>
              <input type="password" name="userPwd" id="userPwd" lay-verify="pass" placeholder="新密码" class="layui-input">
          </div>
          <div class="layui-form-item">
              <label class="layadmin-user-login-icon layui-icon layui-icon-password" for="userPwd1"></label>
              <input type="password" name="userPwd1" id="userPwd1" lay-verify="required" placeholder="确认新密码" class="layui-input">
          </div>
          <input type="hidden" id="dataCode" value="" />
        <div class="layui- form-item">
          <input type="submit" value="注 册" class="layui-btn layui-btn-fluid" />
        </div>
      </div>
    </div>
  </div>
</form>
</body>

</html>