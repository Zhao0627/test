<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: dj
  Date: 2020/7/31
  Time: 9:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>我的订单</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/layui/css/layui.css"  media="all">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/bootstrap/css/bootstrap.css"  media="all">
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/layui/layui.all.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/token.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/cookie.js"></script>
</head>
<body>
<div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
    <ul class="layui-tab-title">
        <li class="layui-this">待付款</li>
        <li>待收货</li>
        <li>已完成</li>
        <li>已取消</li>
    </ul>
    <div class="layui-tab-content" style="height: 100px;">
        <div class="layui-tab-item layui-show">
            <table class="layui-table" >
                <thead>
                    <tr>
                        <th style="font-size: 10px;text-align: center;">订单号</th>
                            <th style="font-size: 10px;text-align: center;" id="productName">商品名称</th>
                                <th style="font-size: 10px;text-align: center;" id="productInfo" hidden>商品信息</th>
                        <th style="font-size: 10px;text-align: center;">购买数量</th>
                                    <th style="font-size: 10px;text-align: center;" hidden id="productRate">折扣</th>
                        <th style="font-size: 10px;text-align: center;">付款金额（包含邮费）</th>
                        <th style="font-size: 10px;text-align: center;">支付方式</th>
                        <th style="font-size: 10px;text-align: center;">邮费</th>
                        <th style="font-size: 10px;text-align: center;">下单时间</th>
                                <th style="font-size: 10px;text-align: center;" hidden id="payTime">付款时间</th>
                        <th style="font-size: 10px;text-align: center;" hidden id="cancelTime">取消时间</th>
                        <th style="font-size: 10px;text-align: center;" colspan="3">订单状态</th>
                    </tr>
                </thead>
                <tbody id="tbd">
                </tbody>
            </table>
            <div id="page_a" align="center">

            </div>
        </div>
    </div>
</div>
<input type="hidden" id="" />
</body>
<script type="text/javascript">
    $(function () {
        show_1();
    })
    function show_1(orderStatus) {
        token_post("<%=request.getContextPath()%>/order/myOrderShow?TOKEN="+getToken(),
            {"orderStatus":orderStatus},
            function (data) {
                var html="";
                for (var i = 0; i < data.data.length; i++) {
                    var order = data.data[i];
                    html += "<tr >"
                    html += "<td style='text-align: center;font-size: 10px;' onclick='getOrderInfo(\""+order.parentOrderNo+"\",\""+order.orderStatus+"\")'>"+order.parentOrderNo+"</td>"
                    html += "<td style='text-align: center;font-size: 10px;' id='productName_val'+order.detailId >"+order.productNameList+"</td>"
                            //html += "<td style='text-align: center;font-size: 10px;'id='productInfo_val'+order.detailId hidden >"+order.productName+":"+order.skuInfo+"</td>"
                    html += "<td style='text-align: center;font-size: 10px;'>"+order.orderTotalBuyCount+"个"+"</td>"
                            //html += "<td style='text-align: center;font-size: 10px;' hidden id='productRate_val'+order.detailId >"+order.skuRate+"%"+"</td>"
                    html += "<td style='text-align: center;font-size: 10px;'>"+order.orderTotalPayMoney+"元"+"</td>"
                    html += "<td style='text-align: center;font-size: 10px;'>"+order.name+"</td>"
                    html += "<td style='text-align: center;font-size: 10px;'>"+order.orderTotalFreight+"元"+"</td>"
                    html += "<td style='text-align: center;font-size: 10px;'>"+order.createTime+"</td>"
                            //html += "<td style='text-align: center;font-size: 10px;' id='payTime_val'+order.detailId hidden>"+order.payTime+"</td>"
                    html += "<td style='text-align: center;font-size: 10px;'>"+order.orderStatus+" | "+"<a href='javascript:updateStatusByParentOrderNo(\""+order.parentOrderNo+"\",\""+"待发货"+"\")' style='background-color: transparent;color: #0D8BBD;'>去支付</a>"+" | "+"<a href='javascript:updateStatusByParentOrderNo(\""+order.parentOrderNo+"\",\""+"已取消"+"\")' style='background-color: transparent;color: #0D8BBD;'>取消订单</a>"+"</td>"
                    html += "</tr>"
                }
                var pageNo="";
                pageNo += "<td style='text-align: center;font-size: 10px;'>"+"<a href='javascript:jiazai(\""+orderStatus+"\")' style='background-color: transparent;color: #0D8BBD;'>加载更多</a>"+"</td>"
                $("#page_a").html(pageNo);
                $("#tbd").html(html);
            })
    }
    function show_2(orderStatus) {
        token_post("<%=request.getContextPath()%>/order/myOrderShow?TOKEN="+getToken(),
            {"orderStatus":orderStatus},
            function (data) {
                var html="";
                for (var i = 0; i < data.data.length; i++) {
                    var order = data.data[i];
                    html += "<tr >"
                    html += "<td style='text-align: center;font-size: 10px;'>"+order.childOrderNo+"</td>"
                    html += "<td style='text-align: center;font-size: 10px;'>"+order.productName+":"+order.skuInfo+"</td>"
                    html += "<td style='text-align: center;font-size: 10px;'>"+order.buyCount+"个"+"</td>"
                    html += "<td style='text-align: center;font-size: 10px;'>"+order.skuRate+"%"+"</td>"
                    html += "<td style='text-align: center;font-size: 10px;'>"+order.payMoney+"元"+"</td>"
                    html += "<td style='text-align: center;font-size: 10px;'>"+order.name+"</td>"
                    html += "<td style='text-align: center;font-size: 10px;'>"+order.freightPrice+"元"+"</td>"
                    html += "<td style='text-align: center;font-size: 10px;'>"+order.createTime+"</td>"
                    if (order.orderStatus!="已完成"&&order.orderStatus!="待支付"&&order.orderStatus!="已取消"){
                        html += "<td style='text-align: center;font-size: 10px;'>"+order.payTime+"</td>"
                        if (order.orderStatus=="待发货"){
                            html += "<td style='text-align: center;font-size: 10px;'>"+order.orderStatus+"</td>"
                            html += "<td style='text-align: center;font-size: 10px;'>"+"<a href='javascript:remind(\""+order.detailId+"\",\""+"已提醒"+"\")' style='background-color: transparent;color: #0D8BBD;'>提醒卖家发货</a>"+"</td>"
                        }
                        if (order.orderStatus=="已提醒"){
                            html += "<td style='text-align: center;font-size: 10px;'>"+order.orderStatus+"</td>"
                            html += "<td style='text-align: center;font-size: 10px;color: #0D8BBD;'>待发货</td>"
                        }
                        if (order.orderStatus=="待收货"){
                            html += "<td style='text-align: center;font-size: 10px;'>"+order.orderStatus+"</td>"
                            html += "<td style='text-align: center;font-size: 10px;'>"+"<a href='javascript:updateStatusByChildOrderNo(\""+order.detailId+"\",\""+order.childOrderNo+"\",\""+order.orderStatus+"\")' style='background-color: transparent;color: #0D8BBD;'>确认收货</a>"+"</td>"
                        }
                    }
                    if (order.orderStatus=="已完成"){
                        html += "<td style='text-align: center;font-size: 10px;'>"+order.payTime+"</td>"
                        html += "<td style='text-align: center;font-size: 10px;'>"+order.orderStatus+"</td>"
                        html += "<td style='text-align: center;font-size: 10px;'>"
                        if (order.commentIsDel != 0){
                            html += "<a href='javascript:toAssessProduct("+order.productId+")' style='background-color: transparent;color: #0D8BBD;'>评价晒单</a> | "
                        }
                        html += "<a href='javascript:updateShopCarIsDel(\""+order.productId+"\",\""+order.skuId+"\")' style='background-color: transparent;color: #0D8BBD;'>再次购买</a>"+"</td>"
                    }
                    if (order.orderStatus=="已取消"){
                        html += "<td style='text-align: center;font-size: 10px;'>"+order.cancelTime+"</td>"
                        html += "<td style='text-align: center;font-size: 10px;'>"+order.orderStatus+" | "+"<a href='javascript:updateShopCarIsDel(\""+order.productId+"\",\""+order.skuId+"\")' style='background-color: transparent;color: #0D8BBD;'>再次购买</a>"+"</td>"
                    }
                    html += "</tr>"
                }
                var pageNo="";
                pageNo += "<td style='text-align: center;font-size: 10px;'>"+"<a href='javascript:jiazai(\""+orderStatus+"\")' style='background-color: transparent;color: #0D8BBD;'>加载更多</a>"+"</td>"
                $("#page_a").html(pageNo);
                $("#tbd").html(html);
            })
    }

    layui.use('element', function() {
        var element = layui.element;
        //一些事件监听
        element.on('tab(docDemoTabBrief)', function (data) {
            console.log(this.innerText); //当前Tab标题所在的原始DOM元素
            if (this.innerText == "待付款") {
                $("#productName").show();
                $("#productInfo").hide();
                $("#productRate").hide();
                $("#payTime").hide();
                $("#cancelTime").hide();
                show_1();
            }
            if (this.innerText != "待付款") {
                $("#productName").hide();
                $("#productInfo").show();
                $("#productRate").show();
                if (this.innerText=="已取消"){
                    $("#cancelTime").show();
                    $("#payTime").hide();
                }else {
                    $("#cancelTime").hide();
                    $("#payTime").show();
                }
                show_2(this.innerText);
            }
        });
    })

    function jiazai() {

    }

    function getOrderInfo(parentOrderNo,orderStatus) {
        //弹出一个iframe层
        layer.open({
            type: 2,
            area: ['550px', '550px'],
            //不显示关闭
            closeBtn: 1 ,
            title:['新增收货地址','padding-left:260px;color:white;background-color:#169bd5;font-size:10px;'],
            /* ['点击A按钮触发','color:#fff;background-color:#01AAED;'], */
            content: "<%=request.getContextPath()%>/order/toLineItem?TOKEN="+getToken()+"&parentOrderNo="+parentOrderNo+"&orderStatus="+orderStatus  /* //这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no'] */
        });
    }

    /* 修改总订单状态 */
    function updateStatusByParentOrderNo(parentOrderNo,orderStatus) {
        token_post("<%=request.getContextPath()%>/order/updateStatusByParentOrderNo?TOKEN="+getToken(),
            {"parentOrderNo":parentOrderNo,"orderStatus":orderStatus},
            function (result) {
                layer.msg(orderStatus,function () {
                    parent.window.location.reload();
                })
            })
    }

    /* 修改详情订单状态 */
    function updateStatusByChildOrderNo(detailId,childOrderNo,orderStatus) {
        token_post("<%=request.getContextPath()%>/order/updateStatusByChildOrderNo?TOKEN="+getToken(),
            {"detailId":detailId,"childOrderNo":childOrderNo,"orderStatus":orderStatus},
            function (result) {
            console.log(result);
                layer.msg(result.msg,function () {
                    parent.window.location.reload();
                })
            })
    }

    /* 再次购买 */
    function updateShopCarIsDel(spuId,skuId) {
        token_post("<%=request.getContextPath()%>/order/updateShopCarIsDel?TOKEN="+getToken(),
            {"skuId":skuId,"spuId":spuId},
            function (result) {
            console.log(result);
                layer.msg(result.msg,function () {
                    parent.window.location.reload();
                })
            })
    }

    /**
     * 去提醒
     * @param detailId
     * @param orderStatus
     */
    function remind(detailId,orderStatus) {
        token_post("<%=request.getContextPath()%>/order/remind?TOKEN="+getToken(),
            {"detailId":detailId,"orderStatus":orderStatus},
            function (result) {
                layer.msg(result.msg,function () {
                    parent.window.location.reload();
                })
            })
    }

    function toAssessProduct(productId) {
        //弹出一个iframe层
        layer.open({
            type: 2,
            area: ['380px', '340px'],
            //不显示关闭
            closeBtn: 1 ,
            title:['评价此件商品','padding-left:160px;color:white;background-color:#169bd5;font-size:10px;'],
            /* ['点击A按钮触发','color:#fff;background-color:#01AAED;'], */
            content: "<%=request.getContextPath()%>/comment/toAssessProduct?TOKEN="+getToken()+"&productId="+productId  /* //这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no'] */
        });
    }
</script>
</html>
