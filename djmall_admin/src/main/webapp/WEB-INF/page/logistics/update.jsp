<%--
  Created by IntelliJ IDEA.
  User: dj
  Date: 2020/7/6
  Time: 12:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/layui/layui.all.js"></script>
<script type="text/javascript">
    function update() {
        $.post("<%=request.getContextPath()%>/dict/log/update",
            $("#fm").serialize(),
            function (data) {
                if (data.code==200){
                    layer.msg(data.msg);
                    var index=window.parent.location.reload();
                    parent.layer.close(index);
                    return;
                }
        })
    }
</script>
<html>
<head>
    <title>Title</title>
</head>
<body>
<center>
<form id="fm">
    <label>运费</label>
    <input type="text" value="${logs.freight}" name="freight">
    <input type="hidden" name="freightId" value="${logs.freightId}" ><br><br>
    <input type="button" value="修改" onclick="update()">
</form>
</center>
</body>
</html>
