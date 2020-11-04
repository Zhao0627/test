<%--
  Created by IntelliJ IDEA.
  User: dj
  Date: 2020/7/30
  Time: 1:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/static/layui/css/layui.css"  media="all">
<link rel="stylesheet" href="<%=request.getContextPath()%>/static/bootstrap/css/bootstrap.css"  media="all">
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/layui/layui.js" charset="utf-8"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/layui/layui.all.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/token.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/cookie.js"></script>
<%--地区插件--%>

<script src="<%=request.getContextPath()%>/static/bootstrap/js/bootstrap.js"></script>
<script src="<%=request.getContextPath()%>/static/dist/distpicker.data.js"></script>
<script src="<%=request.getContextPath()%>/static/dist/distpicker.js"></script>
<script src="<%=request.getContextPath()%>/static/dist/main.js"></script>
<html>
<head>
    <style>
        input{
            margin-top: 10px;
            outline-style: none ;
            border: 1px solid #ccc;
            border-radius: 3px;
            padding: 13px 14px;
            width: 430px;
            font-size: 14px;
            font-weight: 700;
            font-family: "Microsoft soft";
        }
    </style>
    <title>Title</title>
</head>
<body>
<div style="margin-left: 20px;margin-top: 20px;">
    <br>
    <form id="fm">
        <label style="margin-left: 30px;">收件人</label>
        <input type="text" style="width: 40px;border: none;background-color: transparent;" readonly="readonly"/>
        <input type="text" placeholder="请输入收件人姓名" name="receiverName"/><br>
        <label style="margin-left: 30px;">手机号</label>
        <input type="text" style="width: 40px;border: none;background-color: transparent;" readonly="readonly"/>
        <input type="text" placeholder="请输入收件人手机号" name="receiverPhone"/><br>
        <div data-toggle="distpicker">
            <label style="margin-left: 30px;">省市县</label>
            <input type="text" style="width: 40px;border: none;background-color: transparent;" readonly="readonly"/>
            <select id="province1" name="receiverProvince" style="width:140px;height:40px;text-align: center;border: 1px solid #ccc"></select>
            <select id="city1" name="receiverCity" style="width:140px;height:40px;text-align: center;border: 1px solid #ccc"></select>
            <select id="district1" name="receiverCounty" style="width:140px;height:40px;text-align: center;border: 1px solid #ccc"></select>
        </div>
        <label>街道小区详细位置</label>&nbsp;
        <input type="text" placeholder="请输入详细地址"  name="receiverDetail" /><br><br>
        <div align="center">
            <input type="button" value="提交" onclick="tijiao()" style="background-color: #169bd5;border-radius: 5px;border: none;width: 100px;height: 40px;" >
        </div>
    </form>
</div>
</body>
<script type="text/javascript">
    function tijiao() {
        token_post("<%=request.getContextPath()%>/order/receiver/addSite?TOKEN="+cookie.get("TOKEN"),
            $("#fm").serialize(),
            function (result) {
                layer.msg(result.msg,function () {
                    parent.location.reload();//刷新父窗口
                    window.close();//关闭当前窗口
                })
        })
    }
</script>
</html>
