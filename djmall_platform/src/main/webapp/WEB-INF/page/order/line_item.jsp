<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: dj
  Date: 2020/8/1
  Time: 10:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<link rel="stylesheet" href="<%=request.getContextPath()%>/static/layui/css/layui.css"  media="all">
<script src="<%=request.getContextPath()%>/static/layui/layui.js" charset="utf-8"></script>
<script src="<%=request.getContextPath()%>/static/js/jquery-1.12.4.min.js" charset="utf-8"></script>
<div style="margin-left: 20px;">
    <c:forEach items="${OrderDetail}" var="OrderDetail">
        <br>
        <label>订单编号：</label><span style="margin-left: 25px;">${OrderDetail.childOrderNo}</span><br>
        <label>收货信息：</label><span style="margin-left: 25px;">${OrderDetail.receiverName}${OrderDetail.receiverPhone}</span><br>
        <label style="margin-left: 10px;">地&nbsp;&nbsp;址：</label><span style="margin-left: 40px;">${OrderDetail.receiverProvince}-${OrderDetail.receiverCity}-${OrderDetail.receiverCounty}-${OrderDetail.receiverDetail}</span><br>
        <p>
            <label>商品信息：</label>
            <table class="layui-table" style="text-align: center;margin-left: 103px;margin-top: -10px;width: 400px;"  >
                <colgroup>
                    <col width="10">
                    <col width="40">
                    <col width="20">
                    <col width="30">
                    <col width="40">
                </colgroup>
                <thead>
                    <tr>
                        <th style="text-align: center;font-size: 12px;" width="30;">编号</th>
                        <th style="text-align: center;font-size: 12px;" width="100;">商品信息</th>
                        <th style="text-align: center;font-size: 12px;" width="30">数量</th>
                        <th style="text-align: center;font-size: 12px;">实际金额</th>
                        <th style="text-align: center;font-size: 12px;">折扣</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${OrderDetailList}" var="OrderDetailList">
                        <tr>
                            <td>1</td>
                            <td style="text-align: center;font-size: 12px;" width="60;">${OrderDetailList.skuInfo}</td>
                            <td style="text-align: center;font-size: 12px;">${OrderDetailList.buyCount}个</td>
                            <td style="text-align: center;font-size: 12px;">${OrderDetailList.skuPrice}元</td>
                            <td style="text-align: center;font-size: 12px;">${OrderDetailList.skuRate}%</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </p>
        <label>下单时间：</label><span style="margin-left: 23px;">${OrderDetail.createTimeShow}</span><br>
        <label>支付方式：</label><span style="margin-left: 23px;">${OrderDetail.name}</span><br>
        <label>支付时间：</label><span style="margin-left: 23px;">${OrderDetail.payTimeShow}</span><br>
        <label>商品总金额：</label><span style="margin-left: 7px;">￥${OrderDetail.orderTotalMoney}元</span><br>
        <label style="margin-left: 10px;">运&nbsp;&nbsp;费：</label><span style="margin-left: 38px;">￥${OrderDetail.orderTotalFreight}元</span><br>
        <label>实付金额：</label><span style="margin-left: 23px;">￥${OrderDetail.orderTotalPayMoney}元</span>
    </c:forEach>
</div>
</body>
</html>
