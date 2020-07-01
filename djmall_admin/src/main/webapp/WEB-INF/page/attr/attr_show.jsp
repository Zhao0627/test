
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
            $.post("<%=request.getContextPath()%>/dict/attr/list",
                $("#fm").serialize(),
            function (data) {
                var html = "";
                    for(var i = 0; i < data.data.length; i++ ){
                        var attr = data.data[i];
                        html += "<tr align='center'>"
                        html += "<td>"+attr.attrId+"</td>"
                        html += "<td>"+attr.attrName+"</td>"
                        if (attr.valueList==null){
                            html += "<td>暂无</td>"
                        }else{
                            html += "<td>"+attr.valueList+"</td>"
                        }
                        html +="<shiro:hasPermission name='ATTR_VALUE_BUTTON'>"
                        html +="<td><input type='button' value='关联属性值' onclick='toAttrValue("+attr.attrId+")'></td>"
                        html +="</shiro:hasPermission>"
                        html += "</tr>"
                    }

                $("#tbd").html(html);
            })
        }

        function toAttrValue(id){
            layer.open({
                type: 2,
                title: '修改',
                shadeClose: true,
                shade: 0.8,
                area: ['380px', '90%'],
                content: "<%=request.getContextPath() %>/dict/attr/toAttrValue?attrId="+id //iframe的url
            });
        }
        function add() {
            var index = layer.load();
            $.post("<%=request.getContextPath()%>/dict/attr/add",
                $("#fm").serialize(),
                function (data) {
                    if(data.code == 200){
                        layer.close(index);
                        layer.msg(data.msg, {icon: 1}, function(){
                            window.location.href="<%=request.getContextPath()%>/dict/attr/toShow"
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
    <form id="fm" >
        属性名：<input type="text" name="attrName">
        <input type="button" value="新增商品属性" onclick="add()"/>
        <table>
        <tr>
            <th>编号</th>
            <th>属性名</th>
            <th>属性值</th>
            <th>操作</th>
        </tr>
        <tbody id="tbd"></tbody>

    </table>
    <form>

</body>
</html>
