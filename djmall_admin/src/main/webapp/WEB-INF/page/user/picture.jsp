<%--
  Created by IntelliJ IDEA.
  User: dj
  Date: 2020/6/3
  Time: 19:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title></title>
    <meta name="viewport" content="width=device-width">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/idcode/jquery.idcode.css"  media="all"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/idcode/jquery.idcode.js"></script>
</head>

<body>
<label class="lblVerification">验证码</label>
<input type="text" id="Txtidcode" class="txtVerification" />
<span id="idcode"></span>
<div>
    <button type="button" id="btns">验证</button>
</div>

<script>
    $.idcode.setCode();

    $("#btns").click(function (){
        var IsBy = $.idcode.validateCode();
        alert(IsBy);
        console.log(IsBy);
    });
</script>

</body>

</html>
