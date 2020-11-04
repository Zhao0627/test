<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: dj
  Date: 2020/7/3
  Time: 1:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/layui/layui.all.js"></script>
    <script>
        $(function () {
            show();
        })
        function show(){
            $.post("<%=request.getContextPath()%>/dict/attr/list",
                {"productType": $("#code").val()},
                function (data) {
                    var html = "";
                    for(var i = 0; i < data.data.length; i++ ){
                        var sku = data.data[i];
                        html += "<tr id='skuAttr'>"
                        html += "<input type='hidden' name='attrId' value='"+sku.attrId+"'/>"
                        html += "<td><input type='hidden' name='attrName' value='"+sku.attrName+"'/>"+sku.attrName+"</td>"
                        html += "<td>"
                        for (var j = 0; j < sku.valueList.split(",").length; j++){
                            /*html += "<td><input type = 'checkbox' id='check_"+i+"'  name = id value = '"+sku.attrId+"'></td>"*/
                            html += "<input type = 'checkbox' name = 'attrName' value = '"+sku.valueList.split(",")[j]+"'>"+sku.valueList.split(",")[j]
                            html += "<input type = 'hidden' name = 'valId' value = '"+sku.valIdList.split(",")[j]+"'>"
                        }
                        html += "</td>"
                        html += "</tr>"
                    }
                    $("#tbd").html(html);
                })
        }
        function jia(){
            var content = "属性名<input type='text' id='attrName'/><br/>";
            content += "属性值<input type='text' id='attrValue'/><br/>";
            layer.open({
                type: 1,
                title:'自定义属性',
                shadeClose:false,
                shade: 0.5,
                area: ['280px', '70%'],
                btn: ["确定", "取消"],
                content: content,
                yes: function (index) {
                    if ($("#attrValue").val() == '' || $("#attrValue").val()[$("#attrValue").val().length-1] == ',') {
                        layer.alert('属性值不能为空！并且属性值最后一位不能为,');
                        return;
                    }
                    var html = "";
                    html += "<tr>";
                    html += "<input type='hidden' name='attrId' value='-1'/>";
                    html += "<td><input type='hidden' name='attrName' value='"+$("#attrName").val()+"'/>" + $("#attrName").val()+"</td>";
                    var values = $("#attrValue").val().split(",");
                    html += "<td>"
                    for(var i=0; i<values.length; i++){
                        html += "<input type='checkbox' class='che' value='"+values[i]+"' />"+ values[i];
                        html += "<input type = 'hidden' name = 'valId' value = '-1'>"
                    }
                    html += "</td>";
                    html += "</tr>";
                    $("#tbd").append(html);
                    layer.close(index)
                },
            })
        }

        function Sku() {
            var attrValues = [];
            var attrValueIds = [];
            var attrName = [];
            var attrId = [];
            var trs = $("#tbd").find("tr");
            for (var i = 0; i <trs.length ; i++) {
                //获取每行的数据
                var td = $(trs[i]).find("td");

                var values = $(td[1]).find(":checked");
                var valIds =  $(td[1]).find(":hidden");
                var a =  $(td[0]).find("input").val();
                var b =  $(trs[i]).find("input").val();
                attrId.push(b)
                attrName.push(a);
                var value   = [];
                var valuess = [];
                if (values.length>0) {
                    for (var j = 0; j < values.length; j++) {
                        var attr =  $(values[j]).val().split(",");
                        var valId =  $(valIds[j]).val().split(",");
                        value[j] = attr[0];
                        valuess[j] = valId[0];
                    }
                    attrValueIds.push(valuess);
                    attrValues.push(value);
                }
            }

            var skuAttrNames = attrName.join(",");
            var attrIds = attrId.join(",");
            var dkejValues = dkej(attrValues);
            var dkejValueIds = dkej(attrValueIds);
            var html="";
            for (var q = 0; q < dkejValues.length; q++) {
                var m = q + 1;
                html += "<tr id="+'tr_'+q+" >"
                html += "<td>"+m+"</td>"
                html += "<td> <input type='hidden' name='SkuList["+q+"].skuAttrValueNames' value='"+dkejValues[q]+"'/>"+dkejValues[q]+"</td>"
                html += "<input type='hidden' name='SkuList["+q+"].skuAttrValueIds' value='"+dkejValueIds[q]+"'/>"
                html += "<td><input type='text' name='SkuList["+q+"].skuCount'/></td>"
                html += "<td><input type='text' name='SkuList["+q+"].skuPrice'/></td>"
                html += "<td><input type='text' name='SkuList["+q+"].skuRate'/></td>"
                html += "<td><input type='button' value='移除' onclick='delSku("+q+")'/></td>"
                html += "<input type='hidden' name='SkuList["+q+"].skuStatus' value='PRODUCT_UP'/>"
                html += "<input type='hidden' name='SkuList["+q+"].skuAttrNames'value='"+skuAttrNames+"' />";
                html += "<input type='hidden' name='SkuList["+q+"].skuAttrIds'value='"+attrIds+"' />";
                html += "<input type='hidden' name='skuList["+q+"].isDefault' value='DEFAULT'/>";
                html += "</tr>"
            }
            $("#tab").html(html);
        }

        function dkej(d) {
            var total = 1;
            for (var i = 0; i < d.length; i++) {
                total *= d[i].length;
            }
            var e = [];
            var itemLoopNum = 1;
            var loopPerItem = 1;
            var now = 1;
            for (var i = 0; i < d.length; i++) {
                now *= d[i].length;
                var index = 0;
                var currentSize = d[i].length;
                itemLoopNum = total / now;
                loopPerItem = total / (itemLoopNum * currentSize);
                var myIndex = 0;
                for (var j = 0; j < d[i].length; j++) {
                    for (var z = 0; z < loopPerItem; z++) {
                        if (myIndex == d[i].length) {
                            myIndex = 0;
                        }
                        for (var k = 0; k < itemLoopNum; k++) {
                            e[index] = (e[index] == null ? "" : e[index] + ":") + d[i][myIndex];
                            index++;
                        }
                        myIndex++
                    }
                }
            }
            return e;
        }
        
        function delSku(q) {
            $("#tr_"+q).remove();
        }
        
        function add() {
            $.ajax({
                url : "<%=request.getContextPath()%>/product/spu/Add",
                type : "POST",
                cache : false,
                data : new FormData($('#fm')[0]),
                processData : false,
                contentType : false,
                success : function(data) {
                    layer.msg(data.msg, {icon : 1},function () {
                        var index = layer.load(3,{shade:0.3});
                        layer.close(index);
                        return;
                    });
                },
                error : function(data) {
                    layer.msg(data.msg, {icon : 1},function () {
                        var index = layer.load(3,{shade:0.3});
                        layer.close(index);
                        return;
                    });
                }
            });
        }
    </script>
</head>
<body>
<center>
<form id="fm">
    <label>名称</label>
    <input type="text" name="productName" /><br>
    <label>邮费</label>
    <select name="freightId">
        <c:forEach items="${logistics}" var="logistics">
            <option value="${logistics.freightId}">${logistics.logisticsCompanyShow}——${logistics.freight}</option>
        </c:forEach>
    </select><br>
    <label>描述</label>
    <textarea name="productDescribe"></textarea><br>
    <label>图片</label>
    <input type="file" id="img" name="file" /><br>
    <label>分类</label>
    <select name="type" id="code" onchange="show()" >
        <c:forEach items="${productType}" var="productType">
            <option value="${productType.code}">${productType.name}</option>
        </c:forEach>
    </select>
    <label>SKU</label>
    <input type="button" value="+" onclick="jia()"><input type="button" value="生成SKU" onclick="Sku()">
    <table border="1px">
        <tr id="tr">
            <th>属性名</th>
            <th>属性值</th>
        </tr>
        <tbody id="tbd"></tbody>
    </table><br>
    生成后SKU：
    <table border="1px">
        <tr>
            <th>编号</th>
            <th>SKU属性</th>
            <th>库存</th>
            <th>价格(元)</th>
            <th>折扣(%)</th>
            <th>操作</th>
        </tr>
        <tbody id="tab"></tbody>
    </table>
    <input type="hidden" name="status" value="PRODUCT_UP">
    <input type="button" value="新增" onclick="add()">
</form>
</center>
</body>
</html>
