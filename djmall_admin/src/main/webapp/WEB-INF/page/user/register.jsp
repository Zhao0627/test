<%--
  Created by IntelliJ IDEA.
  User: zzw
  Date: 2020/5/18
  Time: 21:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/layer/layer.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript">
        function register() {
            $.post("<%=request.getContextPath()%>/user/insertUser",
                $("#fm").serialize(),
                function (data) {
                    layer.msg(data.msg, {icon : 6, time : 1000}, function(){
                        window.location.href="<%=request.getContextPath()%>/user/toLogin"
                        return;
                    });
                    /*if(data.code == 200){
                        layer.msg(data.msg, {icon : 6, time : 1000}, function(){
                            window.location.href="<%=request.getContextPath()%>/user/toLogin"
                            return;
                        });
                    }else{
                        layer.msg(data.msg);
                        return;
                    }*/
                })
        }
    </script>
</head>
<body bgcolor="black">
<center>
    <div style="background-color: gold;width: 300px;margin-top: 200px;"><br>
    <form id="fm">
        用户名：&nbsp;&nbsp;&nbsp;&nbsp;
        <input type="text" name="userName"/><br><br>

        密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码：&nbsp;&nbsp;&nbsp;&nbsp;
        <input type="text" name="userPwd"/><br><br>

        年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;龄：&nbsp;&nbsp;&nbsp;&nbsp;
        <input type="text" name="userAge"/><br><br>
        <input type="button" value="注册" onclick="register()" /><br>
    </form>
    </div>
</center>
</body>
</html>
