<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: dj
  Date: 2020/7/15
  Time: 11:43
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
<fieldset class="layui-elem-field site-demo-button" style="float: left;margin-top: 30px;margin-left: 30px;width: 576px;height: 448px;font-family: 楷体">
    <legend >商品信息</legend>
    <form id="fm">
        <input type="hidden" name="productSpuId" id="productId" value="${spu.productId}"/>
        <div style="width: 184px;height: 272px;margin-left: 20px;margin-top: 20px">
            <img alt="" src=" http://qekjuppsu.bkt.clouddn.com/${spu.img}?imageView2/1/w/184/h/272/q/75" width="184px;" height="272px;" >
            <input type="hidden" name="img" value="${spu.img}"/>
        </div>
        <div style="width: 270px;height: 365px;float: right;margin-top: -276px;margin-right: 50px">
            <div  style="float: left;margin-top: 0px;width: 120px">
                <label>名称:</label>&nbsp;&nbsp;${spu.productName}
            </div>
            <div style="float: right;margin-top: 0px;width: 120px">
                <label>原价:</label>&nbsp;&nbsp;￥${spu.skuPrice}
            </div>
            <div  style="float: left;margin-top: 10px;width: 100px">
                <label>折扣:</label>&nbsp;&nbsp;${spu.skuRate}
            </div>
            <div style="float: right;margin-top: 10px;width: 100px">
                <label>邮费:</label>&nbsp;&nbsp;￥${spu.freight}
            </div>
            <div style="float: left;margin-top: 10px">
                <label>商品描述：</label><br>
                <textarea style="width:270px;height: 80px;border: none;resize:none;"readonly>
                    ${spu.productDescribe}
                </textarea>
            </div>
            <div  style="float: left;margin-top: 10px;width: 100px">
                <label>点赞量:</label>&nbsp;&nbsp;${spu.skuRate}
            </div>
            <div style="float: right;margin-top: 10px;width: 100px">
                <label>评论量:</label>&nbsp;&nbsp;100
            </div>

            <div style="float: left;margin-top: 10px">
                <label>选择商品信息:</label><br><br>
                <c:forEach items="${skuList}" var="skuList">
                    <div style="margin-left: 15px;float: left" >
                        <input type="radio" value="${skuList.id}" name="productSkuId" onclick="savn(this.value)" <c:if test="${skuList.isDefault==DEFAULT}">checked</c:if>/><label style="margin-left: 5px">${skuList.skuAttrValueNames}</label>
                    </div>
                    <input type="hidden" value="${skuList.skuCount}" id="skuCount_${skuList.id}" />
                </c:forEach>
            </div>

            <input type="hidden" name="skuCount" id="skuCount" value=""/>
            <div style="float: left;margin-top: 15px;width: 270px;">
                <center>
                    <label>购买数量：</label>
                    <input type="button" value="-" style="height: 20px;width: 20px;border: none;background-color:#169bd5;outline: none;" onclick="countChange(0)" />
                    <input type="text" id="shopCount" name="shopCount" style="width: 60px;text-align: center" value="0" oninput="shopCountfun(this.value)" />
                    <input type="button" value="+" style="height: 20px;width: 20px;border: none;background-color:#169bd5;outline: none;" onclick="countChange(1)" />
                </center>
            </div>
            <div style="float: left;margin-left: 10px;margin-top: 10px;width: 100px;height: 20px">
                <div id="shopStatus" style="color: red"></div>
            </div><br>
            <div style="float: left;margin-top: 10px;width: 270px;">
                <center>
                    <input type="button" value="加入购物车" onclick="saveShopCar()" style="border-radius: 10px;height: 30px;width: 80px;border: none;background-color:#169bd5;outline: none;" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <input type="button" value="立即购买" style="border-radius: 10px;height: 30px;width: 80px;border: none;background-color:#169bd5;outline: none;" />
                </center>
            </div>
        </div>
    </form>
</fieldset>
<fieldset class="layui-elem-field site-demo-button" style="float: left;margin-top: 30px;margin-left: 30px;width: 576px;height: 448px;font-family: 楷体;">
    <legend>商品评论</legend>
    <div>
        <label style="margin-left: 50px;margin-top: 20px;font-size: 20px;float: left">好评率：</label>
        <div style="font-size: 30px;color: red;float: left;margin-top: 15px;">50%</div>
        <div style="float: left;margin-left: -150px;margin-top: 60px;">
            <input type="radio" name="commentScore" onclick="getComment()" checked>所有评论
            <input type="radio" name="commentScore" onclick="getComment('gt')" style="margin-left: 20px;">好评
            <input type="radio" name="commentScore" onclick="getComment('eq')" style="margin-left: 20px;">中评
            <input type="radio" name="commentScore" onclick="getComment('lt')" style="margin-left: 20px;">差评
        </div>
    </div>
    <div style="margin-top: 90px;">
        <hr class="layui-bg-black">
    </div>
    <div id="test" style="margin-left: 20px;">

    </div>

</fieldset>
</body>
<script type="text/javascript">
    $(function () {
        getComment();
    })

    function getComment(obj) {
        var productId=$("#productId").val();
        $("#test").empty();
        token_post("<%=request.getContextPath()%>/comment/getCommentByProductId",
            {"productId":productId,"obj":obj},
            function (data) {
                $("#test").html("");
                for (var i = 0; i < data.data.length; i++) {
                    layui.use('rate', function(){
                        var rate=layui.rate;
                        var comment=data.data[i];
                        var html="<div style='margin-top: 40px;border: 1px solid red'>"
                        html+="<div style='float: left'>"+comment.commentPeople+":</div>"
                        html+="<div style='float: left;margin-left: 120px;'>"+comment.commentContent+"</div>"
                        html+="<br><br><div>"+comment.commentDate+"</div>"
                        html+="<div id='test_"+comment.commentId+"' style='float: left;margin-left: 180px;margin-top: -90px;'></div>"
                        html+="</div>"
                        html+="<hr class='layui-bg-black' style='size: 576px'>"
                        $("#test").append(html);
                        rate.render({
                            elem: '#test_'+comment.commentId
                            ,value: comment.commentScore
                            ,theme: 'orange'
                            ,text: true
                            ,readonly:true
                        })
                    })
                }
        })
    }
    
    /* 加减法修改内存 */
    function countChange(obj) {
        var sku = $("#shopCount").val();
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
        $("#shopCount").val(sku);
    }

    function savn(id) {
        var skuCount = $("#skuCount_"+id).val();
        $("#skuCount").val(skuCount);
        if (skuCount==0){
            $("#shopStatus").html("(无货)");
        }if (skuCount<$("#shopCount").val()){
            $("#shopStatus").html("(库存不足)");
        }if (skuCount>=$("#shopCount").val()){
            $("#shopStatus").html("(有货)");
        }
    }

    function shopCountfun(shopCount) {
        var skucount = $("#skuCount").val();
        if (parseInt(skucount)<parseInt(shopCount)){
            $("#shopStatus").html("(库存不足)");
        }if (parseInt(skucount)>=parseInt(shopCount)){
            $("#shopStatus").html("(有货)");
        }
    }

    /**
     * 加入购物车
     */
    function saveShopCar() {
        if ($("#shopCount").val()<=0){
            layer.msg("购买数量不得小于0",function () {
                return;
            });
            return;
        }else if ($("#shopCount").val()>200){
            layer.msg("购买数量最大不可超过200件",function () {
                return;
            });
            return;
        }else if ($("#shopStatus").html()!="(有货)") {
            layer.msg($("#shopStatus").html(),function () {
                return;
            })
            return;
        }
        var token = cookie.get("TOKEN");
        token_post("<%=request.getContextPath()%>/shop/car/saveShopCar?token="+token,
            $("#fm").serialize(),
            function (data) {
                layer.msg(data.msg,function () {
                    window.open("http://localhost:8085/platform/shop/car/toShopCar?TOKEN="+cookie.get("TOKEN"));
                });
        })
    }
</script>
</html>
