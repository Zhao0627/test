
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
                {"ArrIds":$("#ArrIds").val()},
            function (data) {
                var html = "";
                for(var i = 0; i < data.data.length; i++ ){
                    var attr = data.data[i];
                    html += "<tr align='center'>"
                   /* if (attr.checked==1){
                        html += "<td><input type = 'checkbox' checked='true' id='check_"+i+"'  name = id value = '"+attr.attrId+"'></td>"
                    }else{
                        html += "<td><input type = 'checkbox' checked='flase' id='check_"+i+"'  name = id value = '"+attr.attrId+"'></td>"
                    }*/
                    html += "<td><input type = 'checkbox' id='check_"+i+"'  name = id value = '"+attr.attrId+"'></td>"
                    html += "<td id='attrId_'+i>"+attr.attrId+"</td>"
                    html += "<td>"+attr.attrName+"</td>"
                    html += "<td>"+attr.valueList+"</td>"
                    html += "</tr>"
                }
                $("#tbd").html(html);
            })
        }
        function saveSku() {
            var ids = [];//定义一个数组
            $('input[name="id"]:checked').each(function(){//遍历每一个名字为interest的复选框，其中选中的执行函数
                ids.push($(this).val());//将选中的值添加到数组chk_value中
            });
            var id=ids.join(",");
            $.post("<%=request.getContextPath()%>/dict/sku/saveSku",
                {"attrId":id,"productType":$("#productType").val()},
                function (data) {
                    layer.msg(data.msg, {icon: 1}, function(){
                        var index=window.parent.location.reload();
                        parent.layer.close(index);
                        return;
                    });
                })

        }
    </script>
</head>
<body>
<form id="fm" >
        <input type="hidden" name="productType" id="productType" value="${productType}">
        <input type="hidden" name="ArrIds" id="ArrIds" value="${ArrIds}">
        <shiro:hasPermission name='SKU_ATTR_SAVE_BTN'>
        <input type="button" value="保存" onclick="saveSku()"/>
        </shiro:hasPermission>
        <table>
        <tr>
            <th></th>
            <th>编号</th>
            <th>属性名</th>
            <th>属性值</th>
        </tr>
        <tbody id="tbd"></tbody>

    </table>
    <form>

</body>
</html>
