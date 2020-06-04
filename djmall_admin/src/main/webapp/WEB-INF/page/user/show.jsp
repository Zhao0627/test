
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/layer/layer.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.12.4.min.js"></script>

    <script type="text/javascript">
        $(function () {
            search();
        })
        function search(){
            $.post("<%=request.getContextPath()%>/user/show",
                {},
                function (data) {
                    var html = ""
                    for (var i = 0; i < data.length; i++) {
                        var user = data[i];
                        html += "<tr>"
                        html += "<td>"+user.userName+"</td>"
                        html += "<td>"+user.userPwd+"</td>"
                        html += "<td >"+user.userAge+"</td>"
                        html += "<td><input type='button' value='修改' onclick='update("+user.id+")'/>"
                        html += "<input type='button' value='删除' onclick='del("+user.id+")'/></td>>"
                        html += "</tr>"
                    }
                    $("#tab").html(html);
                })
        }
        function del(id) {
            $.post("<%=request.getContextPath()%>/user/deleteUser",
                    {"id": id},
                    function (data) {
                        layer.msg(data.msg, {icon : 6, time : 1000}, function(){
                            window.location.href="<%=request.getContextPath()%>/user/toShow";
                            return;
                        });
                    })
        }

        function update(id) {
            window.location.href="<%=request.getContextPath()%>/user/toUpdate?id="+id;
        }
    </script>
</head>
<body>
<table align="center" border="solid" style=" border-collapse:collapse;">
    <tr>
        <th>用户名</th>
        <th>密码</th>
        <th>年龄</th>
        <th>操作</th>
    </tr>
    <tbody id="tab"></tbody>
</table>
</body>
</html>
