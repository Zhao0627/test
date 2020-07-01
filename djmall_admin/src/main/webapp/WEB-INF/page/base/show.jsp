
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/layui/layui.all.js"></script>
    <script type="text/javascript">
        $(function () {
            search();
        })
        function search() {
            $.get("<%=request.getContextPath()%>/dict/base",
                $("#fm").serialize(),
                function (data) {
                    var html = "";
                    for(var i = 0; i < data.data.length; i++ ){
                        var dict = data.data[i];
                        html += "<tr >"
                        html += "<td>"+dict.code+"</td>"
                        html += "<td>"+dict.name+"</td>"
                        html += "<td>"+dict.pcode+"</td>"
                        <shiro:hasPermission name='DICT_UPDATE_BTN'>
                        html +="<td>"
                        html += "<input type='button' value='修改' onclick=\"update('"+dict.code+","+dict.name+"')\">"
                        html += "</td>"
                        </shiro:hasPermission>
                        html += "</tr>"
                    }
                    $("#tbd").html(html);
                })
        }

        function update(code){
            var name;
            name=prompt("请写出你要修改的名称",code.split(",")[1]);
            if (name==""){
                layer.msg("请输入")
            }
            if (name==null){
                layer.msg("已取消");
                return;
            }
            if (name!=="" && name!==""){
                $.post("<%=request.getContextPath()%>/dict/base",
                    {"_method":"PUT","code":code.split(",")[0],"name":name},
                    function (data) {
                        layer.msg(data.msg,function(){
                            window.location.href="<%=request.getContextPath()%>/dict/base/toShow"
                            return;
                        });
                    })
            }
        }

        function save() {
/*            var pcode = $("#pcode").val();
            var name = $("#name").val();
            var code = $("#code").val();
            if (pcode!="" && name!="" && code != ""){
                layer.msg("必填项不得为空！");
                return;
            }*/
            $.post("<%=request.getContextPath()%>/dict/base",
                $("#fm").serialize(),
                function (data) {
                    layer.msg(data.msg,function(){
                        window.location.href="<%=request.getContextPath()%>/dict/base/toShow"
                        return;
                    });
            })
        }
    </script>
</head>
<body style="float: none">
<form id="fm">
    <shiro:hasPermission name="DICT_SAVE_BTN">
    <input type="hidden" name="_method" value="POST"/>
    分类上级&nbsp;&nbsp;&nbsp;
        <select id="pcode" name="pCode">
        <c:forEach items="${basedata}" var="basedata" >
            <option value="${basedata.code}">${basedata.name}</option>
        </c:forEach>
    </select><br>

    分类名称&nbsp;&nbsp;&nbsp;<input type="text" name="name" id="name" /><br>
    分类code&nbsp;&nbsp;&nbsp;<input type="text" name="code" id="code" /><br>

    <input type="button" value="新增" onclick="save()"/>
    </shiro:hasPermission>
</form>
    <table border = 1px style="padding: 0px;border: solid;">
        <tr>
            <th>CODE</th>
            <th>字典名</th>
            <th>上级CODE</th>
            <shiro:hasPermission name='DICT_UPDATE_BTN'>
            <th>操  作</th>
            </shiro:hasPermission>
        </tr>
        <tbody id="tbd"></tbody>
    </table>


</body>
</html>
