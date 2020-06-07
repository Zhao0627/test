
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
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
                        html += "<td>"+role.roleId+"</td>"
                        html += "<td>"+role.roleName+"</td>"
                        html += "<td><input type='button' value='关联资源' onclick='saveResource("+role.roleId+")'/>"
                        html += "<input type='button' value='编辑' onclick='update("+role.roleId+")'/>"
                        html += "<input type='button' value='删除' onclick='del("+role.roleId+")'/></td>>"
                        html += "</tr>"
                    }
                    $("#tab").html(html);
                })
        }
        function del(roleId) {
            $.post("<%=request.getContextPath()%>/auth/role/deleteRole",
                    {"id": roleId},
                    function (data) {
                        layer.msg(data.msg, {icon : 6, time : 1000}, function(){
                            search();
                            return;
                        });
                    })
        }


        function update(roleId) {
            layer.open({
                type: 2,
                title: '新增',
                shadeClose: true,
                shade: 0.8,
                area: ['380px', '90%'],
                content: "<%=request.getContextPath()%>/auth/role/toUpdateRole?id="+roleId //iframe的url
            });
        }

        function saveRole() {
            layer.open({
                type: 2,
                title: '新增',
                shadeClose: true,
                shade: 0.8,
                area: ['380px', '90%'],
                content: "<%=request.getContextPath() %>/auth/role/toSaveRole" //iframe的url
            });
        }

        /*
        * 关联资源
        * */
        function saveResource(id) {
            layer.open({
                type: 2,
                title: '新增',
                shadeClose: true,
                shade: 0.8,
                area: ['380px', '90%'],
                content: "<%=request.getContextPath() %>/auth/role/toSaveRoleResource?id="+id //iframe的url
            });
        }
    </script>
</head>
<body>
<table align="center" border="solid" style=" border-collapse:collapse;">
    <tr>
        <th>
            <input type="button" value="新增" onclick="saveRole()">
        </th>
    </tr>
    <tr>
        <th>编号</th>
        <th>角色名</th>
        <th>操作</th>
    </tr>
    <tbody id="tab"></tbody>
</table>
</body>
</html>
