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

</script>
</html>
