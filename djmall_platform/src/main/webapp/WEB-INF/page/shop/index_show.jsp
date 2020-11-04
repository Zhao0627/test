<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">--%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/layui/css/admin.css" media="all">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/layui/css/template.css" media="all">
    <style type="text/css">
        a{
            text-decoration: none;
            font-size: 12px;
        }
        dd{
            text-align: center;
        }
    </style>
    <title>Insert title here</title>
</head>
<body>
<%-- <a href="<%=request.getContextPath()%>/user/toLogin">我的美团</a> --%>
<%--<div class="layui-layout layui-layout-admin" >--%>
    <div class="layui-header" style="margin-top: 10px;background-color: transparent">
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;

            <a id="login" href="javascript:toLogin()" style="color: red">登录</a>&nbsp;&nbsp;
            <a id="quit" hidden="hidden" href="javascript:toQuit()"style="color: black">退出</a>
            <a id="insert" href="<%=request.getContextPath()%>/consumer/toInsert"style="color: black">注册</a>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <a href="" style="color: black;">切换城市</a>&nbsp;&nbsp;
        <a href="" style="color: black">北京</a>&nbsp;&nbsp;
        <a href="" style="color: black">上海</a>&nbsp;&nbsp;
        <a href="" style="color: black">广州</a>&nbsp;&nbsp;
        <a href="" style="color: black">深圳</a>&nbsp;&nbsp;
        <ul class="layui-nav layui-layout-left" style="margin-top: -20px; margin-left: 800px;background-color: transparent">
            <li class="layui-nav-item">
                <a href="" style="font-size: 12px; color: black;" >个人中心</a>
                <dl class="layui-nav-child">
                    <dd><a href="javascript:myresume();" style="font-size: 12px;color: black;">个人信息</a></dd>
                    <dd><a href="javascript:addReceiverSite();" style="font-size: 12px;color: black;">收货地址</a></dd>
                    <dd><a href="javascript:toMyOrderShow();" style="font-size: 12px;color: black;">我的订单</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item">
                <a href="javascript:;" style="font-size: 12px;color: black;">我的商品&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%--▼--%></a>
                <dl class="layui-nav-child">
                    <dd><a href="javascript:myShopCar()" style="font-size: 12px;color: black;">我的购物车</a></dd>
                    <dd><a href="javascript:Shop()" style="font-size: 12px;color: black;">我的评价</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item">
                <a href="javascript:;" style="font-size: 12px;color: black;">我是商家&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
                <dl class="layui-nav-child">
                    <dd><a href="" style="font-size: 12px;color: black;">登陆商家中心</a></dd>
                    <dd><a href="" style="font-size: 12px;color: black;">我想合作</a></dd>
                    <dd><a href="" style="font-size: 12px;color: black;">商家营销平台</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item">
                <a href="javascript:;" style="font-size: 12px;color: black;">手机版&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
                <dl class="layui-nav-child">
                    <dd><a href="" style="font-size: 12px;color: black;">App下载</a></dd>
                    <dd><a href="" style="font-size: 12px;color: black;">邮件订阅</a></dd>
                    <dd><a href="" style="font-size: 12px;color: black;">邀请好友</a></dd>
                </dl>
            </li>
        </ul>
        <hr style="margin-top: 25px">
    </div>
<%--</div>--%>
<div style="width:1300px;height:600px; border-color:red;margin-left: 100px;margin-top: -10px;">
    <div style="">
        <font color="#2dc298" size="20px">djmall|<font color="#2dc298" size="5px;">专题</font></font>
        <div style="font-size: 5px;color: #2dc298;margin-top:0px;">platform.com</div>
    </div>
    <form id="query">
        <div style="margin-top: -70px;margin-left: 300px">
            <input type="text" placeholder="请输入商品名称、地址等" name="productName" style="padding-left:10px;width:350px;height:40px;border: none;border-radius: 10px;"/>
            <input type="button" onclick="showProduct()" style="height: 40px;width: 70px;background-color: #2dc298;border: none;border-radius: 10px;" value="搜索"/>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <label style="color: #2dc298;font-size: 18px;">价格：</label>&nbsp;&nbsp;&nbsp;<input type="text" placeholder="请输入起始价" oninput="showProduct()" name="startPrice" style="border:0 ;width:100px;height:35px;border-color: #2dc298;border-radius: 10px;"/>&nbsp;&nbsp;&nbsp;~&nbsp;&nbsp;&nbsp;<input type="text" placeholder="请输入结尾价格  " oninput="showProduct()" name="endPrice" style="border:0 ;width:100px;height:35px;border-color: #2dc298;border-radius: 10px;"/>
        </div>
        <div style="margin-left: 300px; margin-top: 5px;">
            <c:forEach items="${productType}" var="productType">
            <label style="color: #2dc298;font-size: 15px;">${productType.name}：</label><input type="checkbox" class="userSex" name="type" onclick="showProduct()" value="${productType.code}" title="${productType.name}" />&nbsp;&nbsp;&nbsp;
            </c:forEach>
        </div>
        <input type="hidden" name="page" id="pages" />
    </form>
    <div style=" border-color: red;background-color: #2dc298; height: 50px;margin-top: 10px;">
        <br>
        &nbsp;&nbsp;&nbsp;&nbsp;<a href="" style="font-size: 15px;color: white">首页</a>
        &nbsp;&nbsp;&nbsp;&nbsp;<a href="" style="font-size: 15px;color: white">美食</a>
        &nbsp;&nbsp;&nbsp;&nbsp;<a href="" style="font-size: 15px;color: white">电影</a>
        &nbsp;&nbsp;&nbsp;&nbsp;<a href="" style="font-size: 15px;color: white">身边外卖</a>
        <!-- 名店抢购 手机客户端 周边游 -->
        &nbsp;&nbsp;&nbsp;&nbsp;<a href="" style="font-size: 15px;color: white">名店抢购</a>
        &nbsp;&nbsp;&nbsp;&nbsp;<a href="" style="font-size: 15px;color: white">手机客户端</a>
        &nbsp;&nbsp;&nbsp;&nbsp;<a href="" style="font-size: 15px;color: white">周边游</a>
    </div>
    <%--<table class="layui-hide" id="test" lay-filter="test"></table>
    &lt;%&ndash;分页&ndash;%&gt;
    <div id="demo7" ></div>--%>
    <div class="layui-row layui-col-space30" style="width: 1300px;margin-top: 0px;margin-left: 0px" id="space">
    </div>
    <div id="demo7" style="margin-top: 20px;" align="center"></div>
</div>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-1.12.4.min.js"></script>
<script src="<%=request.getContextPath()%>/static/layui/layui.js" charset="utf-8"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/token.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/cookie.js"></script>
<script type="text/javascript">
    $(function () {
        showProduct();
    })
    function showProduct(){
        token_post("<%=request.getContextPath()%>/shop/show2",
            $("#query").serialize(),
            function (result) {
                var html="";
                for (var i = 0; i < result.data.length; i++) {
                    var product = result.data[i];
                    html+='<div class="layui-col-md2 layui-col-sm4" style="float: left;background-color: white" >'
                    html+='<div class="cmdlist-container" style="float: left;height: 350px;" >'
                    html+="<a href='javascript:Details("+product.productSkuId+");'><img alt='' style='width: 150px;height: 150px;'  src='http://qekjuppsu.bkt.clouddn.com/"+product.img+"?imageView2/1/w/150/h/150/q/75' /></a>";
                    html+='<a href="javascript:Details('+product.productSkuId+');">' +
                                '<div class="cmdlist-text"><p class="info">'+product.productDescribe+'</p>' +
                                    '<div class="name"><span style="font-size: 15px;">'+product.productName+'</span>&nbsp;&nbsp;<br>' +
                                        '<div style="float: right;">库存<span style="font-size: 12px;color: red;">'+product.skuCount+'</span>件' +
                                        '</div>' +
                                    '</div>' +
                                    '<div class="freight" style="margin-top: 3px;"><label>类型：</label><span style="font-size: 10px;color: black;">'+product.name+'</span>' +
                                        '<div style="float: right;">快递<span style="font-size: 15px;color: red;">'+product.freight+'</span>' +
                                        '</div>' +
                                    '</div><br>' +
                                    '<div class="price" style="float: left;"><b>￥'+product.skuPrice+'</b><p>打折'+product.skuRate+'%</p>' +
                                    '</div>' +
                                '</div>' +
                           '</a>' +
                           '<span style="float: right;"><i class="layui-icon layui-icon-rate" onclick="assess()"></i>433' +
                           '</span>' +

                           '</div>'
                     html+='</div>'

                }
                $("#space").html(html);
            })
    }

</script>
<script type="text/javascript">
    /* 分页 */
    layui.use(['laypage', 'layer'], function(){
        var laypage = layui.laypage
            ,layer = layui.layer;

        //总页数低于页码总数
        laypage.render({
            elem: 'demo7'
            ,count: 7
            ,layout: ['prev', 'next']
            ,limit:6
            ,jump: function(obj, first){
                //首次不执行
                if(!first){
                    var page=obj.curr;
                    $("#pages").val(page);
                    showProduct();
                }
            }
        });
    });

    /*判断登陆人是否登录*/
    $(function () {
        // 是否登录
        if (check_login()) {
            var nickName = cookie.get("NICK_NAME");
            $("#login").html(nickName);
            $("#quit").show();
            $("#insert").hide();
            $("#login").attr("href", "javascript:myresume();");
        }
    });

    /* 个人简介 */
    function myresume() {
        //弹出一个iframe层
        layer.open({
            type: 2,
            area: ['270px', '325px'],
            //不显示关闭
            closeBtn: 1 ,
            title:['个人信息','padding-left:110px;color:white;background-color:#169bd5;font-size:10px;'],
            /* ['点击A按钮触发','color:#fff;background-color:#01AAED;'], */
            content: "<%=request.getContextPath()%>/consumer/toPersonal?TOKEN="+getToken()  /* //这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no'] */
        });
    }

    /* 退出 */
    function toQuit(){
        /*logout();*/
        window.location.href="<%=request.getContextPath()%>/shop/toIndexShow?TOKEN=" +cookie.get("TOKEN");
        cookie.clear();
    }

    /* 退出 */
    /*function assess(){
        token_post("<%=request.getContextPath()%>/shop/",
            {},
            function () {

        })
    }*/
    layui.use('element', function(){
        var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块

        //监听导航点击
        element.on('nav(demo)', function(elem){
            //console.log(elem)
            layer.msg(elem.text());
        });
    });

    /**
     * 登录
     */
    function toLogin() {
        //弹出一个iframe层
        layer.open({
            type: 2,
            area: ['270px', '325px'],
            //不显示关闭
            closeBtn: 1 ,
            title:['用户登录','padding-left:110px;color:white;background-color:#169bd5;font-size:10px;'],
            /* ['点击A按钮触发','color:#fff;background-color:#01AAED;'], */
            content: "<%=request.getContextPath()%>/consumer/toLogin"  /* //这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no'] */
        });
    }

    /* 去我的购物车 */
    function myShopCar() {
        window.open("http://localhost:8085/platform/shop/car/toShopCar?TOKEN="+getToken());
    }

    /* 去新增我的收货地址 */
    function addReceiverSite() {
        window.open("http://localhost:8085/platform/order/receiver/toAddReceiverSite?TOKEN="+getToken());
    }


    /* 去我的订单 */
    function toMyOrderShow() {
        window.open("http://localhost:8085/platform/order/toMyOrderShow?TOKEN="+getToken());
    }

    /**
     * 商品信息
     * @param productSkuId
     * @constructor
     */
    function Details(productSkuId) {
        window.open("http://localhost:8085/platform/shop/toDetails?productSkuId="+productSkuId);
    }
</script>
</body>
</html>