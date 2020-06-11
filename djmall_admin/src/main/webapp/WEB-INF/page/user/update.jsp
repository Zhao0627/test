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
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/layui/layui.all.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript">
        function update() {
            $.post("<%=request.getContextPath()%>/auth/user/updateUser",
                $("#fm").serialize(),
                function (data) {
                    layer.msg(data.msg, {icon : 6, time : 1000}, function(){

                    });
                    if(data.code == 200){
                        layer.msg(data.msg, {icon : 6, time : 1000}, function(){
                            window.parent.location.reload();
                            parent.layer.close(index);
                            return;
                        });
                    }else{
                        layer.msg(data.msg, {icon : 5, time : 1000},);
                        return;
                    }
                })
        }
    </script>
</head>
<body>
<div align="center">
<form id="fm">
    <input type="hidden" value="${user.userId}"  name="userId" />
    <input type="hidden" value="${user.salt}"  name="salt" />
    用户名：<input type="text" name="userName" value="${user.userName}" /><br /><br />
    手机号：<input type="text" name="userPhone" value="${user.userPhone}" /><br /><br />
    邮箱号：<input type="text" name="userEmail" value="${user.userEmail}" /><br /><br />
    性 &nbsp;&nbsp;
    别  &nbsp;&nbsp;&nbsp;
    ：<input type="radio" name="userSex" value="1" <c:if test='${user.userSex==1}' >checked</c:if>>男&nbsp;
            <input type="radio" name="userSex" value="2" <c:if test='${user.userSex==2}' >checked</c:if>>女<br><br>
    <input type="button" value="修改" onclick="update()" />
</form>
    <a style='color: red'></a>
</div>
</body>
</html>
