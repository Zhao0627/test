
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        function search(id) {
            $.post("<%=request.getContextPath()%>/dict/attr/valueList",
                $("form").serialize(),
                function (data) {
                    var html = "";
                    for(var i = 0; i < data.data.length; i++ ){
                        var value = data.data[i];
                        html += "<tr align='center'>"
                        html += "<td>"+value.valueId+"</td>"
                        html += "<td>"+value.attrValue+"</td>"
                        html +="<shiro:hasPermission name='VALUE_DEL_BUTTON'>"
                        html +="<td><input type='button' value='移除' onclick='toDelValue("+value.valueId+")'></td>"
                        html +="</shiro:hasPermission>"
                        html += "</tr>"
                    }

                    $("#tbd").html(html);
                })
        }
        function add() {
            var index = layer.load();
            $.post("<%=request.getContextPath()%>/dict/attr/addValue",
                $("#fm").serialize(),
                function (data) {
                    if(data.code == 200){
                        layer.close(index);
                        layer.msg(data.msg, {icon: 1}, function(){
                            parent.location.href="<%=request.getContextPath()%>/dict/attr/toShow"
                        });

                    }else {
                        layer.close(index);
                        layer.msg(data.msg,{icon: 5})
                    }
                })
        }

        function toDelValue(id) {
            var index = layer.load();
            $.post("<%=request.getContextPath()%>/dict/attr/delValue?valueId="+id,
                $("#fm").serialize(),
                function (data) {
                    if(data.code == 200){
                        layer.close(index);
                        layer.msg(data.msg, {icon: 1}, function(){
                            parent.location.href="<%=request.getContextPath()%>/dict/attr/toShow"
                        });

                    }else {
                        layer.close(index);
                        layer.msg(data.msg,{icon: 5})
                    }
                })
        }
    </script>
</head>
<body>
    <form id="form">
        <input type="hidden" value="${attr.attrId}" name="attrId"/>
    </form>
    <form id="fm">
        属性名:${attr.attrName}<br/>
        <input type="hidden" value="${attr.attrId}" name="attrId"/>
        属性值：<input type="text" name="attrValue"/>
        <input type="button" value="新增" onclick="add()">

    </form>
    <table>
        <tr>
            <th>编号</th>
            <th>属性值</th>
            <th>操作</th>
        </tr>
        <tbody id="tbd"></tbody>

    </table>

</body>
</html>
