<%--
  Created by IntelliJ IDEA.
  User: dj
  Date: 2020/7/1
  Time: 17:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/layui/layui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/layui/layui.all.js"></script>
<script type="text/javascript">
    $(function () {
        search();
    })
    function search(){
        $.post("<%=request.getContextPath()%>/auth/role/show",
            {},
            function (data) {
                var html = ""
                for (var i = 0; i < data.data.length; i++) {
                    var role = data.data[i];
                    html += "<tr>"
                    html += "<td><input type = 'radio' id='check_"+i+"'  name = id value = '"+role.roleId+"'></td>"
                    html += "<td>"+role.roleId+"</td>"
                    html += "<td>"+role.roleName+"</td>"
                    html += "</tr>"
                }
                $("#tbd").html(html);
            })
    }

    function mandate() {
        $.post("<%=request.getContextPath()%>/auth/user/mandate",
            {"roleId":$('input:radio:checked').val(),"id":$("#userId").val()},
            function (data) {
                if (data.code==200){

                }
                var index=window.parent.location.reload();
                parent.layer.close(index);
        })
    }
</script>
<body>
<form id="fm">
    <input type="button" value="确定" onclick="mandate()">
    <input type="hidden" value="${id}" id="userId">
    <table>
        <tr>
            <th>编号</th>
            <th>角色名</th>
        </tr>
        <tbody id="tbd">
        </tbody>
    </table>
</form>
</body>
</html>
