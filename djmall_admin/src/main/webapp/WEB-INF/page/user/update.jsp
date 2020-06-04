<%--
  Created by IntelliJ IDEA.
  User: zzw
  Date: 2020/5/18
  Time: 21:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/layer/layer.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript">
        function update() {
            $.post("<%=request.getContextPath()%>/user/updateUser",
                $("#fm").serialize(),
                function (data) {
                    layer.msg(data.msg, {icon : 6, time : 1000}, function(){
                        window.location.href="<%=request.getContextPath()%>/user/toShow"
                        return;
                    });
                   /* if(data.code == 200){
                        layer.msg(data.msg, {icon : 6, time : 1000}, function(){
                            window.location.href="<%=request.getContextPath()%>/user/toShow"
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
<body>
<div align="center">
<form id="fm">
    <input type="hidden" value="${user.id}"  name="id" />
    用户名：<input type="text" name="userName" value="${user.userName}" /><br /><br />
    密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码：<input type="text" name="userPwd" value="${user.userPwd}" /><br /><br />
    年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;龄：<input type="text" name="userAge" value="${user.userAge}" /><br /><br />
    <input type="button" value="修改" onclick="update()" />
</form>
</div>
</body>
</html>
