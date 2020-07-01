
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
            $.post("<%=request.getContextPath()%>/dict/sku/list",
                $("#fm").serialize(),
            function (data) {
                var html = "";
                    for(var i = 0; i < data.data.length; i++ ){
                        var sku = data.data[i];
                        html += "<tr>"
                        html += "<td>"+(i+1)+"</td>"
                        html += "<td>"+sku.productShow+"</td>"
                        html += "<td>"+sku.valueList+"</td>"
                        html +="<shiro:hasPermission name='SKU_ATTR_BUTTON'>"
                        html +="<td><input type='button' value='关联属性' onclick='toSkuAttr(\""+sku.productType+"\")'></td>"
                        html +="</shiro:hasPermission>"
                        html += "</tr>"
                    }

                $("#tbd").html(html);
            })
        }

        function toSkuAttr(productType){
            layer.open({
                type: 2,
                title: '关联属性',
                shadeClose: true,
                shade: 0.8,
                area: ['380px', '90%'],
                content: "<%=request.getContextPath() %>/dict/sku/toSkuAttr?productType="+productType //iframe的url
            });
        }
    </script>
</head>
<body>
    <form id="fm">
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
