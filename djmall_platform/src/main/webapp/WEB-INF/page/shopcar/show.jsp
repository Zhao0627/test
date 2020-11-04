<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: dj
  Date: 2020/7/17
  Time: 9:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-1.12.4.min.js"></script>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/layui/css/layui.css" media="all">
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/layui/layui.all.js" charset="utf-8"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/token.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/cookie.js"></script>
</head>
<body>
<div style="margin-left: 20px;margin-top: 20px;width: 550px;">
    <input type="checkbox" id="boxid" onclick="setAllNo()" /><span style="color: red;font-size: 13px">全选/全不选</span><br><br>
    <c:forEach var="spuList" items="${shopCarShowList}">
        <fieldset style="width: 550px;height: 120px;">
            <legend>商品信息</legend>
            <div style="float: left">
                <c:if test="${spuList.checked==true}">
                    <input type="checkbox" checked name="productSkuId" value="${spuList.productSkuId}" onclick="pitchOn(this.value)" />
                </c:if>
                <c:if test="${spuList.checked==false}">
                    <input type="checkbox" name="productSkuId" value="${spuList.productSkuId}" onclick="pitchOn(this.value)" />
                </c:if>
            </div>
            <div style="width: 130px;float: left;margin-left: 10px;">
                <label>名称:</label>
                    ${spuList.productName}<br><br>
                    ${spuList.valueAttrNames}<br><br>
                <label>邮费:</label>
                    ${spuList.freight}
            </div>
            <div style="width: 130px;float: left;margin-left: 30px;">
                <label>原价:</label>
                    ${spuList.oldPrice}<br><br>
                <label>折扣:</label>
                    ${spuList.skuRate}%<br><br>
                <label>现价:</label>
                    ${spuList.newPrice}
            </div>
            <div style="height: 90px;float: left;margin-left: 30px;"><br>
                <label>数量</label>
                <input type="button" value="-" style="height: 20px;width: 20px;border: none;background-color:#169bd5;outline: none;" onclick="countChange(0,${spuList.productSkuId})" />
                <input type="text" id="shopCount_${spuList.productSkuId}" name="shopCount" style="width: 60px;text-align: center" value="${spuList.shopCount}" oninput="show()" />
                <input type="button" value="+" style="height: 20px;width: 20px;border: none;background-color:#169bd5;outline: none;" onclick="countChange(1,${spuList.productSkuId})" />
                <div style="color: rebeccapurple">（有货）</div>
                <div style="margin-left: 30px;margin-top: -17px"><br>
                    <input type="button" value="后悔了，不想要了" style="border: none;color: #169bd5;background-color:transparent;outline: none;">
                </div>
            </div>

        </fieldset>
    </c:forEach>
    <div style="float: left;margin-top: 10px;">
        已选择<input type="text" id="count" value="0" style="width: 20px;border: none;text-align: center;" readonly="readonly"/>件商品，
        总商品金额：￥<input type="text" id="shopSumPrice" value="0" style="width: 20px;border: none;text-align: center;" readonly="readonly"/>，
        商品折后金额：￥<input type="text" id="discountPrice" value="0" style="width: 20px;border: none;text-align: center;" readonly="readonly"/>，
        运费：￥<input type="text" id="sumFreight" value="0" style="width: 20px;border: none;text-align: center;" readonly="readonly"/>
        应付总额：￥<input type="text" id="sumPrice" value="0" style="width: 20px;border: none;text-align: center;" readonly="readonly"/>
    </div>
    <div style="float: right">
        <input type="button" value="去结算" style="width: 60px;border: none;background-color:#169bd5;outline: none;border-radius: 5px;" />
    </div>
</div>
</body>
<script>
    //全选/全不选操作
    function setAllNo(){
        var loves = document.getElementsByName("productSkuId");
        for (var i = 0; i < loves.length; i++) {
            loves[i].checked = true;
        }
        $('input[name="productSkuId"]:checked').each(function(){//遍历每一个名字为interest的复选框，其中选中的执行函数
            productSkuIds.push($(this).val());//将选中的值添加到数组chk_value中
        });
        var productSkuId=productSkuIds.join(",")
        pitchOn(productSkuId);
    }

    /**
     * 选中与非选中
     * @param productSkuId
     */
    var productSkuIds = [];//定义一个数组
    function pitchOn(productSkuId){
        productSkuIds.push(productSkuId);
        var productSkuId=productSkuIds.join(",")
/*       $('input[name="productSkuId"]:checked').each(function(){//遍历每一个名字为interest的复选框，其中选中的执行函数
                    productSkuIds.push($(this).val());//将选中的值添加到数组chk_value中
                });
                var productSkuId=productSkuIds.join(",")*/
        token_post("<%=request.getContextPath()%>/shop/car/checkedTrueAndFalse?TOKEN="+cookie.get("TOKEN"),
            {"productSkuIds":productSkuId},
            function (data) {
                parent.window.location.reload();
        })
    }


    /**
     *
     * 优先加载
     * */
    $(function () {
        show();
    })

    /* 加减法修改内存 */
    function countChange(obj,productSkuId) {
        var sku = $("#shopCount_"+productSkuId).val();
        if (obj==1){
            if (sku>200){
                layer.msg("购买数量最大不可超过200件",function () {
                    return;
                });
                return;
            }
            sku++;
        }
        if (obj==0){
            if (sku<=0){
                layer.msg("购买数量不得小于0",function () {
                    return;
                });
                return;
            }
            sku--;
        }
        $("#shopCount_"+productSkuId).val(sku);
        show();
    }

    /**
     * 展示下面的信息
     *
     * */
    function show() {
        var checkedLength=$("input[type='checkbox'][name='productSkuId'][checked]").length;
        $("#count").val(checkedLength);
        var productSkuIds = [];//定义一个数组
        $('input[name="productSkuId"]:checked').each(function(){//遍历每一个名字为interest的复选框，其中选中的执行函数
            productSkuIds.push($(this).val());//将选中的值添加到数组chk_value中
        });
        var productSkuId=productSkuIds.join(",");
        if (productSkuIds.length==0){
            return;
        }
        token_post("<%=request.getContextPath()%>/shop/car/findShopCarByProductSkuIdsAndUserId?TOKEN="+cookie.get("TOKEN"),
            {"productSkuIds":productSkuId},
            function (data) {
                var b=[];
                var d=[];
                for (var i=0; i < data.data.length;i++){
                    var a=$("#shopCount_"+productSkuIds[i]).val();
                    switch("*"){
                        case "*":d[i]=data.data[i].oldPrice*a;
                    }
                    switch("*"){
                        case "*":b[i]=data.data[i].newPrice*a;
                    }
                }
                var discountPrice=0;
                var shopSumPrice=0;
                var sumFreight=0;
                for (var j = 0; j < data.data.length; j++) {
                    if (data.data[j].freight=="包邮"){
                        data.data[j].freight=0;
                    }else {
                        data.data[j].freight=parseInt(data.data[j].freight.slice(0,-1));
                    }
                    sumFreight+=data.data[j].freight;
                    shopSumPrice+=d[j]
                    discountPrice+=b[j]
                }
                $("#shopSumPrice").val(shopSumPrice.toFixed(2));
                $("#discountPrice").val(discountPrice.toFixed(2));
                if (shopSumPrice.toFixed(2)==0.00){
                    sumFreight=0;
                }
                $("#sumFreight").val(sumFreight+"元");
                var c=Number(discountPrice)+Number(sumFreight);
                $("#sumPrice").val(c.toFixed(2));
                /*css设置*/
                $("#count").css('width',parseInt($("#count").val().length)*8+'px');
                $("#shopSumPrice").css('width',parseInt($("#shopSumPrice").val().length)*8+'px');
                $("#discountPrice").css('width',parseInt($("#discountPrice").val().length)*8+'px');
                $("#sumFreight").css('width',parseInt($("#sumFreight").val().length)*12+'px');
                $("#sumPrice").css('width',parseInt($("#sumPrice").val().length)*8+'px');
            })
    }
</script>
</html>
