<%--
  Created by IntelliJ IDEA.
  User: dj
  Date: 2020/6/10
  Time: 16:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<script src="<%=request.getContextPath()%>/static/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript">setTimeout("history.go(-1)", 5000);  </script>
<SCRIPT language=javascript>
    function go()
    {
        window.history.go(-1);
    }
    setTimeout("go()",3000);
</script>
<body>
<center>
    <h1>403 Forbidden</h1>
<hr>
    <h3>服务器拦截——>拦截原因：无访问权限。<a href="javascript:;" onClick="javascript:history.back(-1);">返回上一页</a>或者5秒后自动返回</h3>
</center>
</body>
</html>
