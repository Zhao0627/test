<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: dj
  Date: 2020/7/6
  Time: 18:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/layui/layui.all.js"></script>
<script type="text/javascript">
    $(function () {
        show();
    })
    var dataLength;
    function show() {
        $.post("<%=request.getContextPath()%>/product/sku/show",
                {"productId":$("#proId").val()},
                function (data) {
                    dataLength=data.data.length;
                    var html = "";
                    for(var i = 0; i < data.data.length; i++ ){
                        var sku = data.data[i];
                        html += "<tr >"
                        html += "<td><input type='checkbox' name='skuId' class='checked' value='"+sku.id+"' />"+sku.id+"</td>"
                        html += "<td style='text-align: center' id='sku_attr_value_names"+sku.id+"'>"+sku.skuAttrValueNames+"</td>"
                        html += "<td style='text-align: center' id='sku_"+sku.id+"'>"+sku.skuCount+"</td>"
                        html += "<td style='text-align: center' id='sku_price"+sku.id+"'>"+sku.skuPrice+"</td>"
                        html += "<td style='text-align: center' id='sku_rate"+sku.id+"'>"+sku.skuRate+"</td>"
                        html += "<td style='text-align: center' id='is_default"+sku.id+"'>"+sku.isDefaultShow+"</td>"
                        html += "<td id='downbtn_"+sku.id+"' onclick='soldOut("+sku.id+")' >"+sku.downBtn+"</td>"
                        html += "<td id='sku_status"+sku.id+"' hidden>"+sku.skuStatus+"</td>"
                        html += "<input type='hidden' id='1sku_id"+sku.id+"' name='SkuList["+i+"].id' value='"+sku.id+"' />"
                        html += "<input type='hidden' id='1sku_attr_value_names"+sku.id+"' name='SkuList["+i+"].skuAttrValueNames' value='"+sku.skuAttrValueNames+"' />"
                        html += "<input type='hidden' id='1sku_"+sku.id+"' name='SkuList["+i+"].skuCount' value='"+sku.skuCount+"' />"
                        html += "<input type='hidden' id='1sku_price"+sku.id+"' name='SkuList["+i+"].skuPrice' value='"+sku.skuPrice+"' />"
                        html += "<input type='hidden' id='1sku_rate"+sku.id+"' name='SkuList["+i+"].skuRate' value='"+sku.skuRate+"' />"
                        html += "<input type='hidden' id='1is_default"+sku.id+"' name='SkuList["+i+"].isDefault' value='"+sku.isDefault+"' />"
                        html += "<input type='hidden' id='1sku_status"+sku.id+"' name='SkuList["+i+"].skuStatus' value='"+sku.skuStatus+"' />"
                        html += "</tr>"
                    }
                    $("#tbd").html(html);
                })
    }

    function selectType() {
        $("#type").attr("disabled","disabled");
    }
    var skuCount="";
    var index="";
    /* 修改库存 */
    function toUpdateCount() {
        var data = $('input:checkbox[name="skuId"]:checked').map(function () {
            return $(this).val();
        }).get();
        if (data.length==0){
            layer.msg("必须选一个");
            return;
        }
        if (data.length>1){
            layer.msg("只能选一个");
            return;
        }
        skuCount=$("#sku_"+data.join(",")).html();
        index=layer.open({
            type: 1,
            title:['库存修改','padding-left:70px;color:white;background-color:#169bd5;font-size:10px;'],
            shadeClose:false,
            shade: 0.5,
            area: ['200px', '130px'],
            content: '<center>' +
                '<br><label>库存:</label>' +
                '<input type="button" value="-" onclick="countChange('+0+')" />' +
                '<input type="text" id="skuCount" style="width: 60px;text-align: center" value="'+skuCount+'" />' +
                '<input type="button" value="+" onclick="countChange('+1+')" /><br><br>' +
                '<input type="button" value="确定" onclick="updateCount('+data[0]+')" /></center>',
        })
    }

    /* 修改库存中的保存 */
    function updateCount(id) {
        $("#sku_"+id).html($("#skuCount").val());
        $("#1sku_"+id).val($("#skuCount").val());
        $(".checked").attr("checked", false);
        layer.close(index);
    }

    /* 编辑 */
    var index1;
    function redact() {
        var data = $('input:checkbox[name="skuId"]:checked').map(function () {
            return $(this).val();
        }).get();
        if (data.length==0){
            layer.msg("必须选一个");
            return;
        }
        if (data.length>1){
            layer.msg("只能选一个");
            return;
        }
        //弹出一个iframe层
        index1=layer.open({
            type: 1,
            title:['库存修改','padding-left:70px;color:white;background-color:#169bd5;font-size:10px;'],
            shadeClose:false,
            shade: 0.5,
            area: ['500px', '500px'],
            content: '<br><label>SKU属性:</label><input type="text" id="productSkuAttrValueNames" readonly value="'+$("#sku_attr_value_names"+data.join(",")).html()+'"/><br><label>库存</label>' +
                '<input type="text" id="productSkuCount" value="'+$("#sku_"+data.join(",")).html()+'" /><br><label>价格</label>' +
                '<input type="text" id="productSkuPrice" value="'+$("#sku_price"+data.join(",")).html()+'"><br><label>折扣</label>' +
                '<input type="text" id="productSkuRate" value="'+$("#sku_rate"+data.join(",")).html()+'"/><br>' +
                '<input type="button" value="保存" onclick="keep('+data.join(",")+')" />'
        });
    }

    /* 编辑中的保存 */
    function keep(id) {
        $("#sku_attr_value_names"+id).html($("#productSkuAttrValueNames").val())
        $("#sku_price"+id).html($("#productSkuPrice").val())
        $("#sku_rate"+id).html($("#productSkuRate").val())
        $("#sku_"+id).html($("#productSkuCount").val());

        $("#1sku_attr_value_names"+id).val($("#productSkuAttrValueNames").val())
        $("#1sku_price"+id).val($("#productSkuPrice").val())
        $("#1sku_rate"+id).val($("#productSkuRate").val())
        $("#1sku_"+id).val($("#productSkuCount").val());
        $(".checked").attr("checked", false);
        layer.close(index1);
    }

    /* 加减法修改内存 */
    function countChange(obj) {

        var sku = $("#skuCount").val();
        if (obj==1){
            sku++;
        }
        if (obj==0){
            if (sku<=0){
                layer.msg("库存不得小于0",function () {
                    return;
                });
                return;
            }
            sku--;
        }
        $("#skuCount").val(sku);
    }

    /* 设为默认 */
    function updateDefault() {
        var data = $('input:checkbox[name="skuId"]:checked').map(function () {
            return $(this).val();
        }).get();
        for (var i=0;i<data.length;i++){
            if ($("#is_default"+data[i]).html()=="是"){
                $("#is_default"+data[i]).html("不是");
                $("#1is_default"+data[i]).val("NODEFAULT");
            }else{
                $("#is_default"+data[i]).html("是");
                $("#1is_default"+data[i]).val("DEFAULT");
            }
        }
        $(".checked").attr("checked", false);
    }

    /**
     * 下架
     */
    function soldOut(id) {
        if ($("#1is_default"+id).val()=="DEFAULT"){
            layer.msg("默认商品不可以下架",function () {
                return;
            })
            return;
        }
        $("#sku_status"+id).html("PRODUCT_DOWN");
        $("#1sku_status"+id).val("PRODUCT_DOWN");
        $("#downbtn_"+id).html("已下架");
        layer.msg("下架ing");
    }

    function updateSpuAndAku() {
        $.ajax({
            url : "<%=request.getContextPath()%>/product/spu/Update",
            type : "POST",
            cache : false,
            data : new FormData($('#fm')[0]),
            processData : false,
            contentType : false,
            success : function(data) {
                layer.msg(data.msg, {icon : 1},function () {
                    var index=window.parent.location.reload();
                    parent.layer.close(index);
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
<html>
<head>
    <title>Title</title>
</head>
<body>
<input type="hidden" id="proId" value="${productId}">
<form id="fm">
    <input type="hidden" id="productId" name="productId" value="${productId}">
    <label>名称</label>
    <input type="text" name="productName" value="${spu.productName}"/><br><br>
    <label>邮费</label>
    <select name="freightId">
        <c:forEach items="${logistics}" var="logistics">
            <option value="${logistics.freightId}" <c:if test='${spu.freightId==logistics.freightId}'>selected=selected</c:if>>${logistics.logisticsCompanyShow}——${logistics.freight}</option>
        </c:forEach>
    </select><br><br>
    <label>描述</label>
    <textarea name="productDescribe" style="height: 50px;width: 200px;">${spu.productDescribe}</textarea><br><br>
    <label>图片</label>
    <img alt="" src=" http://qekjuppsu.bkt.clouddn.com/${spu.img}?imageView2/1/w/35/h/60/q/75" >
    <input type="hidden" name="img" value="${spu.img}"/>
    <input type="file" id="img" name="file" /><br><br>
    <label>分类</label>
    <select name="type" id="type" onchange="selectType()" >
        <c:forEach items="${type}" var="type">
            <option value="${type.code}" <c:if test='${type.code==spu.type}'>selected=selected</c:if>>${type.name}</option>
        </c:forEach>
    </select><br><br>
    <label>SKU列表</label>
    <input type="button" value="修改库存" onclick="toUpdateCount()" />
    <input type="button" value="编辑" onclick="redact()" />
    <input type="button" value="设为默认" onclick="updateDefault()"/>
    <table border="1px">
        <tr>
            <th>编号</th>
            <th>SKU属性</th>
            <th>库存</th>
            <th>价格（元）</th>
            <th>折扣（%）</th>
            <th>是否默认</th>
            <th>操作</th>
        </tr>
        <tbody id="tbd">
        </tbody>
    </table><br><br>
    <center>
    <input type="button" value="修改" onclick="updateSpuAndAku()" />
    </center>
</form>
</body>
</html>
