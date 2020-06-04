<%--
  Created by IntelliJ IDEA.
  User: zzw
  Date: 2020/4/18
  Time: 23:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/layui/layui.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/layui/layui.all.js"></script>
    <script type="text/javascript">
            function add() {
/*                $.post("<%=request.getContextPath()%>/auth/role/findRoleById",
                    $("#fm").serialize(),
                    function (data) {
                        if(data.code == 200){

                        }
                    })*/

                var index = layer.load();
                $.post("<%=request.getContextPath()%>/auth/role/saveRole",
                    $("#fm").serialize(),
                    function (data) {
                        if(data.code == 200){
                            layer.close(index);
                            layer.msg(data.msg, {icon: 1}, function(){
                                parent.location.href="<%=request.getContextPath()%>/auth/role/toShow"
                            });
                        }
                    })
            }
    </script>
</head>
<body>

    <form id="fm">
        角色名<input type="text" name="roleName"/><br />
        <input type="button" value="增加" onclick="add()">
    </form>




</body>
</html>
