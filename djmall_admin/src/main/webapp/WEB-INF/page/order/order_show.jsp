<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Layui</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/layui/css/layui.css"  media="all">
    <!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
</head>
<body>
<div class="layui-form">
    <table class="layui-table" >
        <colgroup>
            <col width="150">
            <col width="150">
            <c:if test="${user.userLevel==3}">
                <col width="100">
            </c:if>
            <col width="160">
            <col width="40">
            <col width="160">
            <col width="170">
            <col width="80">
            <col width="180">
            <col width="110">
            <col width="110">
            <col width="200">
            <col width="200">
            <col width="150">
            <col width="200">
            <col>
        </colgroup>
        <thead>
            <tr>
                <th style="font-size: 10px;text-align: center;">订单号</th>
                <th style="font-size: 10px;text-align: center;">商品名称</th>
                <c:if test="${user.userLevel==3}">
                    <th style="font-size: 10px;text-align: center;">商家</th>
                </c:if>
                <th style="font-size: 10px;text-align: center;">购买数量</th>
                <th style="font-size: 10px;text-align: center;">折扣</th>
                <th style="font-size: 10px;text-align: center;">付款金额<br>（包含邮费）</th>
                <th style="font-size: 10px;text-align: center;">支付方式</th>
                <th style="font-size: 10px;text-align: center;">邮费</th>
                <th style="font-size: 10px;text-align: center;">收货人信息</th>
                <th style="font-size: 10px;text-align: center;">下单人</th>
                <th style="font-size: 10px;text-align: center;">下单人电话</th>
                <th style="font-size: 10px;text-align: center;">下单时间</th>
                <th style="font-size: 10px;text-align: center;">付款时间</th>
                <th style="font-size: 10px;text-align: center;">买家消息</th>
                <th style="font-size: 10px;text-align: center;" colspan="2">订单状态</th>
            </tr>
        </thead>
        <tbody id="tbd">
        </tbody>
    </table>
</div>
<script src="<%=request.getContextPath()%>/static/layui/layui.js" charset="utf-8"></script>
<script src="<%=request.getContextPath()%>/static/js/jquery-1.12.4.min.js" charset="utf-8"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/layui/layui.all.js"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<script>
    $(function () {
        show();
    })
    function show() {
        $.post("<%=request.getContextPath()%>/order/orderShow",
            {},
            function (data) {
                var html="";
                for (var i = 0; i < data.data.length; i++) {
                        var order = data.data[i];
                        html += "<tr >"
                        html += "<td style='text-align: center;font-size: 10px;'>"+order.childOrderNo+"</td>"
                        html += "<td style='text-align: center;font-size: 10px;'>"+order.productName+"</td>"
                        /* 用户管理员 */
                        if (data.user.userLevel==3){
                            html += "<td style='text-align: center;font-size: 10px;'>"+order.userName+"</td>"
                        }
                        html += "<td style='text-align: center;font-size: 10px;'>"+order.buyCount+"个"+"</td>"
                        html += "<td style='text-align: center;font-size: 10px;'>"+order.skuRate+"%"+"</td>"
                        html += "<td style='text-align: center;font-size: 10px;'>"+order.payMoney.toFixed(2)+"元"+"</td>"
                        html += "<td style='text-align: center;font-size: 10px;'>"+order.name+"</td>"
                        if (order.freightPrice==0){
                            html += "<td style='text-align: center;font-size: 10px;'>包邮</td>"
                        }else {
                            html += "<td style='text-align: center;font-size: 10px;'>"+order.freightPrice+"元"+"</td>"
                        }
                        html += "<td style='text-align: center;font-size: 10px;'>"+order.receiverName+"-"+order.receiverPhone+"<br>"+order.receiverProvince+order.receiverCity+order.receiverCounty+order.receiverDetail+"</td>"
                        html += "<td style='text-align: center;font-size: 10px;'>"+order.receiverName+"</td>"
                        html += "<td style='text-align: center;font-size: 10px;'>"+order.receiverPhone+"</td>"
                        html += "<td style='text-align: center;font-size: 10px;'>"+order.createTime+"</td>"
                        html += "<td style='text-align: center;font-size: 10px;'>"+order.payTime+"</td>"
                        html += "<td style='text-align: center;font-size: 10px;'>"+order.buyerMessage+"</td>"
                        if (order.orderStatus!="已完成"&&order.orderStatus!="待支付"&&order.orderStatus!="已取消"&&order.orderStatus!="待收货"){
                            html += "<td style='text-align: center;font-size: 10px;'>"+order.orderStatus+
                                "<br><a href='javascript:updateStatusByChildOrderNo(\""+order.detailId+"\",\""+order.buyerId+"\",\""+order.childOrderNo+"\",\""+"待发货"+"\")' style='background-color: transparent;color: #0D8BBD;'>去发货</a>"+
                                "</td>"
                        }else {
                            html += "<td style='text-align: center;font-size: 10px;'>"+order.orderStatus+"</td>"
                        }
                    html += "</tr>"
                }
                $("#tbd").html(html);
            })
    }
    
    function updateStatusByChildOrderNo(detailId,buyerId,childOrderNo,orderStatus) {
        $.post("<%=request.getContextPath()%>/order/updateStatusByChildOrderNo",
            {"detailId":detailId,"buyerId":buyerId,"childOrderNo":childOrderNo,"orderStatus":orderStatus},
            function (result) {
                layer.msg(result.msg,function () {
                    parent.window.location.reload();
                })
            })
    }
    
</script>

</body>
</html>