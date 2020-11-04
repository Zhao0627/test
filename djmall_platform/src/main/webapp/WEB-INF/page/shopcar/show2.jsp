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
        <input type="hidden" class="shopCarId" value="${spuList.shopCarId}">
        <input type="hidden" class="sumList" value="${spuList.productSkuId}" />
        <fieldset style="width: 550px;height: 120px;">
            <legend>商品信息</legend>
            <div style="float: left">
                <c:if test="${spuList.checked==true}">
                    <input type="checkbox" checked name="productSkuId" value="${spuList.productSkuId}" onclick="show()" />
                </c:if>
                <c:if test="${spuList.checked==false}">
                    <input type="checkbox" name="productSkuId" value="${spuList.productSkuId}" onclick="show()" />
                </c:if>
            </div>
            <div style="width: 130px;float: left;margin-left: 10px;">
                <label>名称:</label>
                    <input type="text" name="productName" id="productName_${spuList.productSkuId}" style="width: 60px;border: none;" readonly="readonly" value="${spuList.productName}" /><br><br>
                    <input type="text" name="valueAttrNames" id="valueAttrNames_${spuList.productSkuId}" style="width: 95px;border: none;" readonly="readonly" value="${spuList.valueAttrNames}" /><br><br>
                <label>邮费:</label>
                <input type="text" name="freight" id="freight_${spuList.productSkuId}" style="width: 30px;border: none;" readonly="readonly" value="${spuList.freight}" /><br><br>
            </div>
            <div style="width: 130px;float: left;margin-left: 30px;">
                <label>原价:</label>
                <input type="text" name="oldPrice" id="oldPrice_${spuList.productSkuId}" style="width: 50px;border: none;" readonly="readonly" value="${spuList.oldPrice}" /><br><br>
                <label>折扣:</label>
                <input type="text" name="skuRate" id="skuRate_${spuList.productSkuId}" style="width: 30px;border: none;" readonly="readonly" value="${spuList.skuRate}%" /><br><br>
                <label>现价:</label>
                <input type="text" name="newPrice" id="newPrice_${spuList.productSkuId}" style="width: 50px;border: none;" readonly="readonly" value="${spuList.newPrice}" /><br><br>
            </div>
            <div style="height: 90px;float: left;margin-left: 30px;"><br>
                <label>数量</label>
                <input type="button" value="-" style="height: 20px;width: 20px;border: none;background-color:#169bd5;outline: none;" onclick="countChange(0,${spuList.productSkuId})" />
                <input type="hidden" id="skuCount_${spuList.productSkuId}" name="skuCount" value="${spuList.skuCount}" />
                <input type="text" id="shopCount_${spuList.productSkuId}" name="shopCount" style="width: 60px;text-align: center" value="${spuList.shopCount}" oninput="judge(${spuList.productSkuId})" />
                <input type="button" value="+" style="height: 20px;width: 20px;border: none;background-color:#169bd5;outline: none;" onclick="countChange(1,${spuList.productSkuId})" />
                <div style="color: rebeccapurple;width: 70px;height: 20px;" id="prompt_${spuList.productSkuId}"></div>
                <div style="margin-left: 30px;margin-top: -17px;"><br>
                    <input type="button" value="后悔了，不想要了" onclick="deleteShopCar(${spuList.productSkuId})" style="border: none;color: #169bd5;background-color:transparent;outline: none;">
                </div>
            </div>

        </fieldset>
    </c:forEach>
    <form id="fm">
        <div style="float: left;margin-top: 10px;">
            <input type="hidden" name="totalBuyCount" id="totalBuyCount" />
            已选择<input type="text" id="count" value="0" style="width: 20px;border: none;text-align: center;" readonly="readonly"/>件商品，
            总商品金额：￥<input type="text" id="shopSumPrice" value="0" style="width: 20px;border: none;text-align: center;" readonly="readonly"/>，
            商品折后金额：￥<input type="text" name="totalMoney" id="discountPrice" value="0" style="width: 20px;border: none;text-align: center;" readonly="readonly"/>，
            运费：￥<input type="text" name="totalFreight" id="sumFreight" value="0" style="width: 20px;border: none;text-align: center;" readonly="readonly"/>
            应付总额：￥<input type="text" name="totalPayMoney" id="sumPrice" value="0" style="width: 20px;border: none;text-align: center;" readonly="readonly"/>
        </div>
    </form>
    <div style="float: right">
        <input type="button" value="去结算" style="width: 60px;border: none;background-color:#169bd5;outline: none;border-radius: 5px;" onclick="toBalance()" />
    </div>
</div>
</body>

<script type="text/javascript">
    /**
     * 退出或者刷新会修改数据库
     * */
/*    window.onbeforeunload=function (){
        /!*勾选完的ids*!/
        var checkedProductSkuIds = [];//定义一个数组
        $('input[name="productSkuId"]:checked').each(function(){//遍历每一个名字为interest的复选框，其中选中的执行函数
            checkedProductSkuIds.push($(this).val());//将选中的值添加到数组chk_value中
        });
        pitchOn(checkedProductSkuIds);

        /!*展示出来的所有的ids*!/
        var productSkuIds=[]; //获取值
        $(".sumList").each(function(){
            productSkuIds.push($(this).val()); //(获取两个控件的val值并且用逗号拼接)
        });

        var shopCarIds=[]; //获取值
        $(".shopCarId").each(function(){
            shopCarIds.push($(this).val()); //(获取两个控件的val值并且用逗号拼接)
        });
        var shopCounts=[];
        for (var i = 0; i < productSkuIds.length; i++) {
            shopCounts.push($("#shopCount_"+productSkuIds[i]).val());
        }
        token_post("<%=request.getContextPath()%>/shop/car/updateCount",
            {"shopCarIds":shopCarIds.join(","),"shopCounts":shopCounts.join(",")},
            function (result) {
                return;
        })
    }*/
</script>

<script>
    /* 删除购物车数据 */
    function deleteShopCar(productSkuId) {
        var productSkuIds=[];
        if (productSkuId!=undefined){
            productSkuIds.push(productSkuId);
        }else {
            /*勾选完的ids*/
            $('input[name="productSkuId"]:checked').each(function(){//遍历每一个名字为interest的复选框，其中选中的执行函数
                productSkuIds.push($(this).val());//将选中的值添加到数组chk_value中
            });
        }
        token_post("<%=request.getContextPath()%>/shop/car/deleteShopCar?TOKEN="+cookie.get("TOKEN"),
            {"productSkuIds":productSkuIds.join(",")},
            function (result) {
                if (result.code==200){
                    layer.msg(result.msg,function () {
                    })
                }
        })
    }

    //全选/全不选操作
    function setAllNo(){
        var loves = document.getElementsByName("productSkuId");
        if($("#boxid").prop("checked")){
            for (var i = 0; i < loves.length; i++) {
                loves[i].checked = true;
            }
        }else{
            for (var i = 0; i < loves.length; i++) {
                loves[i].checked = false;
            }
        }
        show();
    }

    /**
     * 选中与非选中
     * @param productSkuId
     */
    function pitchOn(checkedProductSkuIds){
        /*展示出来的所有的ids*/
        debugger;
        var productSkuIds=[]; //获取值
        $(".sumList").each(function(){
            productSkuIds.push($(this).val()); //(获取两个控件的val值并且用逗号拼接)
        });
        if (checkedProductSkuIds.length==productSkuIds.length){
            $("#boxid").checked=true;
        }else {
            $("#boxid").checked=false;
        }
        if (checkedProductSkuIds.length==0){
            return;
        }
        return;
        token_post("<%=request.getContextPath()%>/shop/car/checkedTrueAndFalse?TOKEN="+cookie.get("TOKEN"),
            {"productSkuIds":productSkuIds.join(","),"checkedProductSkuIds":checkedProductSkuIds.join(",")},
            function (data) {
                parent.window.location.reload();
        })
    }

    /**
     *
     * 优先加载
     * */
    $(function () {
        /*选择复选框禁用*/
        var productSkuIds=[]; //获取值页面全部的sku
        $(".sumList").each(function(){
            productSkuIds.push($(this).val()); //(获取两个控件的val值并且用逗号拼接)
        });
        for (var i=0;i<productSkuIds.length;i++){
            if (parseInt($("#skuCount_"+productSkuIds[i]).val())<parseInt($("#shopCount_"+productSkuIds[i]).val())){
                document.getElementsByName("productSkuId")[i].checked=false;
                /*禁用方法*/
                document.getElementsByName("productSkuId")[i].disabled = true;
            }
        }
        show();
    })

    /* 加减法修改内存 */
    function countChange(obj,productSkuId) {
        var sku=$("#shopCount_"+productSkuId).val();
        var skuCount=$("#skuCount_"+productSkuId).val();
        if(parseInt(sku)>parseInt(skuCount)){
            $("#shopCount_"+productSkuId).val($("#skuCount_"+productSkuId).val());
            $("#prompt_"+productSkuId).html("(库存不足)")
            show();
            return;
        }
        if (obj==1){
            if (sku>=200){
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
     * 判断库存与实际购买量
     * */
    function judge(productSkuId) {
        var shopCount=$("#shopCount_"+productSkuId).val();
        var skuCount=$("#skuCount_"+productSkuId).val();
        if(parseInt(shopCount)>parseInt(skuCount)){
            $("#shopCount_"+productSkuId).val($("#skuCount_"+productSkuId).val());
            $("#prompt_"+productSkuId).html("(库存不足)")
        }
        if (shopCount<=0){
            layer.msg("购买数量不得小于0",function () {
                return;
            });
            return;
        }
        if (shopCount>=200){
            layer.msg("购买数量最大不可超过200件",function () {
                return;
            });
            return;
        }
        show();
    }
    
    /**
     * 动态展示下面的信息
     *
     * */
    function show() {
        var productSkuIds = [];//定义一个数组
        $('input[name="productSkuId"]:checked').each(function(){//遍历每一个名字为interest的复选框，其中选中的执行函数
            productSkuIds.push($(this).val());//将选中的值添加到数组chk_value中
        });
        $("#count").val(productSkuIds.length);
        var sumSkuIds=[]; //获取值页面全部的sku
        $(".sumList").each(function(){
            sumSkuIds.push($(this).val()); //(获取两个控件的val值并且用逗号拼接)
        });
        if (sumSkuIds.length==productSkuIds.length){
            document.getElementById("boxid").checked = true;
        }else {
            document.getElementById("boxid").checked = false;
        }
        var b=[];
        var d=[];
        var totalBuyCount="";
        for (var i = 0; i < productSkuIds.length; i++) {
            var shopCount=$("#shopCount_"+productSkuIds[i]).val();
            var oldPrice=$("#oldPrice_"+productSkuIds[i]).val();
            var newPrice=$("#newPrice_"+productSkuIds[i]).val();
            switch("*"){
                case "*":d[i]=oldPrice*shopCount;
            }
            switch("*"){
                case "*":b[i]=newPrice*shopCount;
            }
            totalBuyCount+=shopCount;
        }
        $("#totalBuyCount").val(totalBuyCount);
        var discountPrice=0;
        var shopSumPrice=0;
        var sumFreight=0;
        for (var j = 0; j < productSkuIds.length; j++) {
            var freight=$("#freight_"+productSkuIds[j]).val();
            if (freight=="包邮"){
                freight=0;
            }else {
                freight=parseInt(freight.slice(0,-1));
            }
            sumFreight+=freight;
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
    }

    /**
     * 去结算
     */
    function toBalance() {
        var productSkuIds = [];//定义一个数组
        $('input[name="productSkuId"]:checked').each(function(){//遍历每一个名字为interest的复选框，其中选中的执行函数
            productSkuIds.push($(this).val());//将选中的值添加到数组chk_value中
        });
        var shopCount=[];
        for (var i = 0; i < productSkuIds.length; i++) {
            var count=$("#shopCount_"+productSkuIds[i]).val();
            shopCount.push(count);
        }
        window.open("http://localhost:8085/platform/order/toSaveOrder?TOKEN="+cookie.get("TOKEN")+"&productSkuIds="+productSkuIds.join(",")+"&shopCount="+shopCount.join(","));
    }
    


</script>
</html>
