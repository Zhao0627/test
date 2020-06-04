<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/layui/layui.all.js"></script>
<script type="text/javascript">
	function out(){
		window.location.href="<%=request.getContextPath()%>/auth/user/toLogin";
	}
	$(function(){
		setInterval("$('#time1').html(new Date().toLocaleString()+' 星期'+'日一二三四五六'.charAt(new Date().getDay()));",1000);
		
	})

</script>
<title>Insert title here</title>
</head>
<body>
<div align="center">
    <span style="color:red"><h2>欢迎${user.userName}登录</h2></span>
</div>
<input type="button" value="退出" onclick="out()">
<table border="1px" bordercolor="black" bgcolor="yellow" align="right"  >
<tbody id="time1" align="justify"></tbody>
</table>
</body>
</html>