<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: dj
  Date: 2020/7/21
  Time: 10:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/layui/css/layui.css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/layui/layui.all.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/layui/layui.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/cookie.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/token.js"></script>
<head>
    <title>Title</title>
</head>
<style>
    label{
        font-size: 15px;
        font-family: 楷体;
        margin-left: 10px;
    }
    .text{
        font-size: 12px;
        font-family: 楷体;
        border-radius: 8px;
        border: 1px solid black;
        outline:none;
        margin-left: 15px;
        width: 170px;
        height: 30px;
        padding-left:10px;
    }
    .radio{
        size: 20px;
        zoom: 70%;
    }
    #button{
        font-size: 12px;
        font-family: 楷体;
        border-radius: 8px;
        border: none;
        outline: none;
        background-color: #0D8BBD;
        width: 140px;
        height: 30px;
    }
</style>
<body>
<form id="fm"><br>
    <p>
        <input type="hidden" name="userId" value="${user.userId}">
        <label>昵称:</label>
        <input type="text" class="text" name="nickName" placeholder="${user.nickName}"  />
    </p><br>
    <p>
        <label>头像:</label><input type="file" style="margin-left: 20px;margin-top: -20px;width: 170px;font-size: 12px;font-family: 楷体;" name="file">
    </p><br>
    <p>
        <label>性别:</label> &nbsp;&nbsp;&nbsp;
        <c:forEach items="${sex}" var="sex">
            <input type="radio" class="radio" name="userSex" value="${sex.code}" title="${sex.name}" <c:if test="${user.userSex==sex.code}">checked</c:if>/><font style="font-family: 楷体;font-size: 14px">${sex.name}</font>
        </c:forEach>
    </p><br>
    <p>
        <label>邮箱:</label>
        <input type="text" class="text" name="userEmail" placeholder="${user.userEmail}"/>
    </p>
</form>
    <br>
    <div align="center"><input type="button" id="button" value="修改" onclick="updateUser()"/></div>
</body>
<script type="text/javascript">
    function updateUser() {
        token_post("<%=request.getContextPath()%>/consumer/updateUser",
            $("#fm").serialize(),
            function (result) {
            alert(result.msg);
        })
    }
</script>
</html>
