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
        <div class="layui-tab-item layui-show"><table id="test" lay-filter="test"></table></div>
        <div class="layui-tab-item"><table class="test" id="test1" lay-filter="test1"></table></div>
        <div class="layui-tab-item"><table class="test" id="test2" lay-filter="test2"></table></div>
        <div class="layui-tab-item"><table class="test" id="test3" lay-filter="test3"></table></div>
<%--
        <div class="layui-tab-item">内容3</div>
        <div class="layui-tab-item">内容4</div>
--%>
    </div>
</div>
<input type="hidden" id="" />
</body>
<script type="text/html" id="barDemo1">
    <a class="layui-btn layui-btn-xs" lay-event="toCancel" style="background-color: transparent;color: #0D8BBD;">确认收货</a>
    <a class="layui-btn layui-btn-xs" lay-event="toCancel" style="background-color: transparent;color: #0D8BBD;">提醒卖家发货</a>
    <a class="layui-btn layui-btn-xs" lay-event="toCancel" style="background-color: transparent;color: #0D8BBD;">已提醒</a>
</script>
<script type="text/html" id="barDemo2">
    <a class="layui-btn layui-btn-xs" lay-event="toPayment" style="background-color: transparent;color: #0D8BBD;">评价晒单</a>|
    <a class="layui-btn layui-btn-xs" lay-event="toCancel" style="background-color: transparent;color: #0D8BBD;">再次购买</a>
</script>
<script type="text/html" id="barDemo3">
    <a class="layui-btn layui-btn-xs" lay-event="toPayment" style="background-color: transparent;color: #0D8BBD;">再次购买</a>
</script>
<script type="text/javascript">

    layui.use(['element', 'table'], function(){
        var element = layui.element;
        var table = layui.table;
        //分页
        var laypage = layui.laypage
            ,layer = layui.layer;
        //一些事件监听
        element.on('tab(docDemoTabBrief)', function(data){
            console.log(this.innerText); //当前Tab标题所在的原始DOM元素
            if (this.innerText!="待付款"){
                table.reload('test1'/*['test1','test2','test3']*/, {
                    method: 'post'
                    , where: {
                        'orderStatus': this.innerText
                    }
                },function () {
                    alert("aaaa");
                });
                table.reload('test2'/*['test1','test2','test3']*/, {
                    method: 'post'
                    , where: {
                        'orderStatus': this.innerText
                    }
                });
                table.reload('test3'/*['test1','test2','test3']*/, {
                    method: 'post'
                    , where: {
                        'orderStatus': this.innerText
                    }
                });
            }
        });


        table.render({
            elem: /*['#test1','#test2','#test3']*/ '#test1'
            ,id:/*['test1','test2','test3']*/ 'test1'  //主键id
            ,url:'<%=request.getContextPath()%>/order/myOrderShow?TOKEN='+getToken()
            ,title: '用户数据表'
            /* 该参数可自由配置头部工具栏右侧的图标按钮  */
            ,defaultToolbar: ['', '', '']
            ,width:1355
            ,cols: [[
                {field:'childOrderNo', event: 'childOrderNo', templet: "#childOrderNoId", title:'订单号',align:'center',width:210,fixed: 'left'}
                ,{field:'productName', title:'商品信息', align:'center',width:190,templet:function (d) {
                        return d.productName+':'+d.skuInfo;
                    }}
                ,{field:'buyCount', title:'购买数量',align:'center' , width:90}
                ,{field:'skuRate', title:'折扣',align:'center' , width:90,templet:function (d) {
                        return (parseInt(d.skuRate)*0.01).toFixed(2);
                    }}
                ,{field:'payMoney', title:'付款金额（包含邮费）',align:'center' , width:170,templet: function(d){
                        return d.payMoney+'元';
                    }}
                ,{field:'name', title:'支付方式', width:100,align:'center' }
                ,{field:'freightPrice', title:'邮费',align:'center' , width:60, templet: function(d){
                        if (d.freightPrice==0){return '包邮';}else {return d.freightPrice+'元'}
                    }}
                ,{field:'createTime', title:'下单时间',align:'center' , width:160}
                ,{field:'payTime', title:'付款时间',align:'center' , width:160}
                ,{fixed: 'right' ,field:'orderStatus',title:'订单状态', toolbar: '#barDemo1', align:'center' , width:120,rowspan:"3"}
            ]]
            /* 单元格点击事件名 */
            ,even:true
            /* 表格外观 */
            ,skin:"nob"
        });

/*        table.on('tool(test1)', function (obj) {
            let event = obj.event;
            console.log(obj.data);
            console.log(obj.data.childOrderNo);
        }*/

        table.render({
            elem: /*['#test1','#test2','#test3']*/ '#test2'
            ,id:/*['test1','test2','test3']*/ 'test2'  //主键id
            ,url:'<%=request.getContextPath()%>/order/myOrderShow?TOKEN='+getToken()
            ,title: '用户数据表'
            /* 该参数可自由配置头部工具栏右侧的图标按钮  */
            ,defaultToolbar: ['', '', '']
            ,width:1355
            ,cols: [[
                {field:'childOrderNo', event: 'childOrderNo', templet: "#childOrderNoId", title:'订单号',align:'center',width:210,fixed: 'left'}
                ,{field:'productName', title:'商品信息', align:'center',width:190,templet:function (d) {
                        return d.productName+':'+d.skuInfo;
                    }}
                ,{field:'buyCount', title:'购买数量',align:'center' , width:90}
                ,{field:'skuRate', title:'折扣',align:'center' , width:90,templet:function (d) {
                        return (parseInt(d.skuRate)*0.01).toFixed(2);
                    }}
                ,{field:'payMoney', title:'付款金额（包含邮费）',align:'center' , width:170,templet: function(d){
                        return d.payMoney+'元';
                    }}
                ,{field:'name', title:'支付方式', width:100,align:'center' }
                ,{field:'freightPrice', title:'邮费',align:'center' , width:60, templet: function(d){
                        if (d.freightPrice==0){return '包邮';}else {return d.freightPrice+'元'}
                    }}
                ,{field:'createTime', title:'下单时间',align:'center' , width:160}
                ,{field:'payTime', title:'付款时间',align:'center' , width:160}
                ,{fixed: 'right' ,field:'orderStatus',title:'订单状态', toolbar: '#barDemo2', align:'center' , width:120,rowspan:"3"}
            ]]
            /* 单元格点击事件名 */
            ,even:true
            /* 表格外观 */
            ,skin:"nob"
        });

        table.render({
            elem: /*['#test1','#test2','#test3']*/ '#test3'
            ,id:/*['test1','test2','test3']*/ 'test3'  //主键id
            ,url:'<%=request.getContextPath()%>/order/myOrderShow?TOKEN='+getToken()
            ,title: '用户数据表'
            /* 该参数可自由配置头部工具栏右侧的图标按钮  */
            ,defaultToolbar: ['', '', '']
            ,width:1355
            ,cols: [[
                {field:'childOrderNo', event: 'childOrderNo', templet: "#childOrderNoId", title:'订单号',align:'center',width:210,fixed: 'left'}
                ,{field:'productName', title:'商品信息', align:'center',width:190,templet:function (d) {
                        return d.productName+':'+d.skuInfo;
                    }}
                ,{field:'buyCount', title:'购买数量',align:'center' , width:90}
                ,{field:'skuRate', title:'折扣',align:'center' , width:90,templet:function (d) {
                        return (parseInt(d.skuRate)*0.01).toFixed(2);
                    }}
                ,{field:'payMoney', title:'付款金额（包含邮费）',align:'center' , width:170,templet: function(d){
                        return d.payMoney+'元';
                    }}
                ,{field:'name', title:'支付方式', width:100,align:'center' }
                ,{field:'freightPrice', title:'邮费',align:'center' , width:60, templet: function(d){
                        if (d.freightPrice==0){return '包邮';}else {return d.freightPrice+'元'}
                    }}
                ,{field:'createTime', title:'下单时间',align:'center' , width:160}
                ,{field:'payTime', title:'付款时间',align:'center' , width:160}
                ,{fixed: 'right' ,field:'orderStatus',title:'订单状态', toolbar: '#barDemo3', align:'center' , width:120,rowspan:"3"}
            ]]
            /* 单元格点击事件名 */
            ,even:true
            /* 表格外观 */
            ,skin:"nob"
        });

    });
</script>
<script type="text/html" id="barDemo">
    <span style="color:black;font-size: 12px;" >
        待支付,
    </span>
    <a class="layui-btn layui-btn-xs" lay-event="toPayment" style="background-color: transparent;color: #0D8BBD;">去支付</a>
    <a class="layui-btn layui-btn-xs" lay-event="toCancel" style="background-color: transparent;color: #0D8BBD;">取消订单</a>
</script>
<script type="text/javascript">
    layui.use('table', function(){
        var element = layui.element;
        var table = layui.table;
        //分页
        var laypage = layui.laypage
            ,layer = layui.layer;
        table.render({
            elem: '#test'
            ,id:'test'	//主键id
            ,url:'<%=request.getContextPath()%>/order/myOrderShow?TOKEN='+getToken()
            ,title: '用户数据表'
            /* 该参数可自由配置头部工具栏右侧的图标按钮  */
            ,defaultToolbar: ['', '', '']
            ,width:1239
            ,cols: [[
                {field:'parentOrderNo', event: 'childOrderNo', templet: "#childOrderNoId", title:'订单号', align:'center' , width:210, fixed: 'left'}
                ,{field:'productNameList', title:'商品名称', align:'center' , width:150}
                ,{field:'orderTotalBuyCount', title:'购买数量',align:'center' , width:90}
                ,{field:'orderTotalPayMoney', title:'付款金额（包含邮费）',align:'center' , width:170,templet: function(d){
                        return d.orderTotalPayMoney+'元'
                    }}
                ,{field:'name', title:'支付方式', width:150,align:'center' }
                ,{field:'orderTotalFreight;', title:'邮费',align:'center' , width:70, templet: function(d){
                        if (d.orderTotalFreight==0){return '包邮';}else {return d.orderTotalFreight+'元'}
                    }}
                ,{field:'createTime', title:'下单时间',align:'center' , width:200}
                ,{fixed: 'right' ,title:'订单状态', toolbar: '#barDemo', align:'center' , width:195,rowspan:"3"}
            ]]
            /* 单元格点击事件名 */
            ,even:true
            /* 表格外观 */
            ,skin:"nob"
        });

        table.on('tool(test)', function (obj) {
            let event = obj.event;
            console.log(obj.data);
            console.log(obj.data.childOrderNo);

            /**
             * 订单详情
             */
            if (event === "childOrderNo"){
                //弹出一个iframe层
                layer.open({
                    type: 2,
                    area: ['550px', '550px'],
                    //不显示关闭
                    closeBtn: 1 ,
                    title:['新增收货地址','padding-left:260px;color:white;background-color:#169bd5;font-size:10px;'],
                    /* ['点击A按钮触发','color:#fff;background-color:#01AAED;'], */
                    content: "<%=request.getContextPath()%>/order/toLineItem?TOKEN="+cookie.get("TOKEN")+"&parentOrderNo="+obj.data.parentOrderNo+"&orderStatus="+obj.data.orderStatus  /* //这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no'] */
                });
            }

            /**
             * 去支付
             */
            if(event==="toPayment"){
                token_post("<%=request.getContextPath()%>/order/updateOrderStatus",
                    {"orderNo":obj.data.parentOrderNo,"orderStatus":"待发货"},
                    function (result) {
                        layer.msg("订单已支付",function () {
                            parent.window.location.reload();
                        })
                })
            }
            /**
             * 取消订单
             */
            if(event==="toCancel"){
                token_post("<%=request.getContextPath()%>/order/updateOrderStatus",
                    {"orderNo":obj.data.parentOrderNo,"orderStatus":"已取消"},
                    function (result) {
                        layer.msg("已取消订单",function () {
                            parent.window.location.reload();
                    })
                    })
            }
        })
    });
</script>
</html>
