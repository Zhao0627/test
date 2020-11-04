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
    <script type="text/javascript">
        /* 去新增我的收货地址 */
        function addReceiverSite() {
            window.open("http://localhost:8085/platform/order/receiver/toAddReceiverSite?TOKEN="+getToken());
        }
    </script>
</head>
<body>
<div style="margin-left: 20px;margin-top: 20px;width: 550px;">
    <form id="fm">
        <div style="margin-left: 5px;">
            <label style="font-size: 12px;">收货人信息:</label>
            <select style="font-size: 12px;width: 350px;" name="receiverId">
                <c:forEach items="${orderReceiver}" var="orderReceiver">
                    <option value="${orderReceiver.receiverId}">${orderReceiver.receiverName}-${orderReceiver.receiverPhone}-${orderReceiver.receiverProvince}${orderReceiver.receiverCity}${orderReceiver.receiverCounty}${orderReceiver.receiverDetail}</option>
                </c:forEach>
            </select>&nbsp;&nbsp;&nbsp;
            <a href="javascript:addReceiverSite();" style="font-size: 12px;color: #0D8BBD;">没有地址？去添加</a>
        </div>
        <br>
        <c:forEach var="spuList" items="${shopCar}" varStatus="xh">
            <fieldset style="width: 550px;height: 120px;">
                <input type="hidden" class="sumList" name="spuList[${xh.index}].productSkuId" value="${spuList.productSkuId}" />
                <input type="hidden" name="spuList[${xh.index}].userId" value="${spuList.userId}" />
                <input type="hidden" name="spuList[${xh.index}].productId" value="${spuList.productSpuId}" />
                <legend>商品信息</legend>
                <div style="width: 130px;float: left;margin-left: 10px;">
                    <label>名称:</label>
                        <input type="text" name="spuList[${xh.index}].productName" id="productName_${spuList.productSkuId}" style="width: 60px;border: none;" readonly="readonly" value="${spuList.productName}" /><br><br>
                        <input type="text" name="spuList[${xh.index}].skuAttrValueNames" id="valueAttrNames_${spuList.productSkuId}" style="width: 95px;border: none;" readonly="readonly" value="${spuList.valueAttrNames}" /><br><br>
                </div>
                <div style="width: 240px;float: left;margin-left: 20px;">
                    <label>原价:</label>
                    <input type="text" name="spuList[${xh.index}].oldPrice" id="oldPrice_${spuList.productSkuId}" style="width: 50px;border: none;" readonly="readonly" value="${spuList.oldPrice}" /><br><br>
                    <label>折扣:</label>
                    <input type="text" name="spuList[${xh.index}].skuRate" id="skuRate_${spuList.productSkuId}" style="width: 25px;border: none;" readonly="readonly" value="${spuList.skuRate}" />%,
                    <label>现价￥</label>
                    <input type="text" name="spuList[${xh.index}].newPrice" id="newPrice_${spuList.productSkuId}" style="width: 50px;border: none;" readonly="readonly" value="${spuList.newPrice}" /><br><br>
                </div>
                <div style="width: 130px;float: left;margin-left: 0px;">
                    <label>数量:×</label>
                    <input type="text" name="spuList[${xh.index}].skuCount" id="shopCount_${spuList.productSkuId}" style="width: 30px;border: none;" readonly="readonly" value="${spuList.skuCount}" /><br><br>
                    <label>邮费:</label>
                    <input type="text" name="spuList[${xh.index}].freight" id="freight_${spuList.productSkuId}" style="width: 30px;border: none;" readonly="readonly" value="${spuList.freight}" /><br><br>
                </div>
            </fieldset>
        </c:forEach>
        <div style="float: left;margin-top: 10px;">
            共<input type="text" id="totalBuyCount" name="totalBuyCount" style="width: 20px;border: none;text-align: center;" readonly="readonly"/>件商品，
            总商品金额：￥<input type="text" name="totalMoney" id="totalMoney" style="width: 100px;border: none;text-align: center;" readonly="readonly"/>，
            商品折后金额：￥<input type="text" name="shopSumPrice" id="shopSumPrice" style="width: 20px;border: none;text-align: center;" readonly="readonly"/>，
            运费：￥<input type="text" name="totalFreight" id="totalFreight" style="width: 20px;border: none;text-align: center;" readonly="readonly"/>
            应付总额：￥<input type="text" name="totalPayMoney" id="totalPayMoney" style="width: 20px;border: none;text-align: center;" readonly="readonly"/>
        </div>
        <div  style="float: right;margin-top: 10px;">
            <label style="font-size: 12px">支付方式</label>
            <select name="payType" style="font-size: 12px;" >
                <c:forEach items="${basedata}" var="basedata">
                    <option value="${basedata.code}">${basedata.name}</option>
                </c:forEach>
            </select>
        </div>
        <input type="hidden" name="orderStatus" id="orderStatus" />
        <div style="float: left;margin-top: 40px;margin-left: 200px;">
            <input type="button" value="提交订单" style="width: 60px;border: none;background-color:#169bd5;outline: none;border-radius: 5px;" onclick="toBalance('待支付')" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="button" value="取消订单" style="width: 60px;border: none;background-color: transparent;outline: none;border-radius: 5px;" onclick="toBalance('已取消')" />
        </div>
    </form>
</div>
</body>
<script type="text/javascript">
    $(function () {
        var productSkuIds=[]; //获取值
        $(".sumList").each(function(){
            productSkuIds.push($(this).val()); //(获取两个控件的val值并且用逗号拼接)
        });
        var shopCount=0;
        var oldSumPrice=0;
        var newSumPrice=0;
        var sumfreight=0;
        for (var i = 0; i < productSkuIds.length; i++) {
            shopCount+=parseInt($("#shopCount_"+productSkuIds[i]).val());
            oldSumPrice+=$("#oldPrice_"+productSkuIds[i]).val()*parseInt($("#shopCount_"+productSkuIds[i]).val());
            newSumPrice+=$("#newPrice_"+productSkuIds[i]).val()*parseInt($("#shopCount_"+productSkuIds[i]).val());
            var freight=$("#freight_"+productSkuIds[i]).val();
            if (freight=="包邮"){
                freight=0;
            }else {
                freight=parseInt(freight.slice(0,-1));
            }
            sumfreight+=freight;
        }
        var totalPayMoney=sumfreight+newSumPrice;
        $("#totalBuyCount").val(shopCount).css('width',parseInt($("#totalBuyCount").val().length)*8+'px');
        $("#shopSumPrice").val(newSumPrice.toFixed(2)).css('width',parseInt($("#shopSumPrice").val().length)*8+'px');
        $("#totalMoney").val(oldSumPrice.toFixed(2)).css('width',parseInt($("#totalMoney").val().length)*8+'px');
        $("#totalFreight").val(sumfreight.toFixed(2)).css('width',parseInt($("#totalFreight").val().length)*12+'px');
        $("#totalPayMoney").val(totalPayMoney.toFixed(2)).css('width',parseInt($("#totalPayMoney").val().length)*8+'px');
    })
    
    function toBalance(obj) {
        console.log(obj);
        $("#orderStatus").val(obj);
        token_post("<%=request.getContextPath()%>/order/addOrder?TOKEN="+getToken(),
            $("#fm").serialize(),
            function (result) {
                if(result.code==200){{
                    layer.msg(result.msg,function () {
                        window.open("http://localhost:8085/platform/order/toMyOrderShow?TOKEN="+getToken());
                    })
                }}

        })
    }
</script>
</html>
