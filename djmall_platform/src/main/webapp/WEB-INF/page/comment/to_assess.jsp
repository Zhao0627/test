<%--
  Created by IntelliJ IDEA.
  User: dj
  Date: 2020/8/18
  Time: 18:33
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
<div>
    <div  style="margin-left: 50px;margin-top: 20px;">
        <input type="hidden" id="productId" value="${productId}"/>
        <label style="font-family: 楷体;font-size: 14px;">商品评分：</label>
        <div id="commentScore" style="margin-left: 15px;"></div>
    </div>
    <div  style="margin-left: 50px;margin-top: 20px;width: 277px;height: 120px">
        <label style="font-family: 楷体;font-size: 14px;float: left;">商品评价：</label>
        <div contenteditable="true" style="text-indent:2em;border: 1px solid black;width: 190px;height: 120px;float: left;margin-left: 15px;font-family: 楷体;font-size: 12px;" id="commentContent"></div>
    </div>
    <div  style="margin-left: 50px;margin-top: 20px;width: 277px;" >
        <div align="center">
            <input type="button" value="发表评论" style="border: none;outline: none;background-color: #169bd5;width: 150px;height: 35px;border-radius: 10px" onclick="publish()">
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    layui.use(['rate'], function(){
        var rate = layui.rate;
        rate.render({
            elem: '#commentScore'
            ,value: 5
            ,theme: 'orange'
            ,text: true
            /*,setText: function(value){
                this.span.text(value);
            }*/
        })
    })

    function publish() {
        var commentContent=$("#commentContent").text();
        var commentScore=$("#commentScore").text();
        var productId=$("#productId").val();
        token_post("<%=request.getContextPath()%>/comment/addComment?TOKEN="+getToken(),
            {"commentContent":commentContent,"commentScore":commentScore,"productId":productId},
            function (result) {

        })

    }

</script>
</html>
