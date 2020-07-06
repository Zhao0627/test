<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: dj
  Date: 2020/7/6
  Time: 11:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/layui/layui.all.js"></script>
<script type="text/javascript">
    function save() {
        $.post("<%=request.getContextPath()%>/dict/log/save",
            $("#frm").serialize(),
            function (data) {
                    if (data.code==200){
                        layer.msg(data.msg)
                        window.location.href="<%=request.getContextPath()%>/dict/log/toShow";
                    }
        })
    }
    
    function update(freightId) {
        //弹出一个iframe层
        layer.open({
            type: 2,
            area: ['230px', '150px'],
            //不显示关闭
            closeBtn: 1 ,
            title:['修改运费','padding-left:80px;color:white;background-color:#169bd5;font-size:10px;'],
            /* ['点击A按钮触发','color:#fff;background-color:#01AAED;'], */
            content: "<%=request.getContextPath()%>/dict/log/toUpdate?freightId="+freightId  /* //这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no'] */
        });
    }
</script>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form id="frm">
    <shiro:hasPermission name="LOGISTICS_SAVE_BTN">
<label>物流公司</label>
<select name="logisticsCompany">
    <c:forEach items="${logistics}" var="logistics">
        <option value="${logistics.code}">${logistics.name}</option>
    </c:forEach>
</select>
<label>运费</label>
<input name="freight" placeholder="请输入价格" >
<input type="button" value="新增" onclick="save()">
    </shiro:hasPermission>
</form>
<form id="fm">
    <table>
        <tr>
            <th>物流公司</th>
            <th>运费</th>
            <shiro:hasPermission name="LOGISTICS_UPDATE_BTN">
                <th>操作</th>
            </shiro:hasPermission>
        </tr>
        <tbody>
            <c:forEach var="logs" items="${logs}">
                <tr>
                    <td>${logs.logisticsCompanyShow}</td>
                    <td>${logs.freight}</td>
                    <shiro:hasPermission name="LOGISTICS_UPDATE_BTN">
                        <td><input type="button" value="修改" onclick="update('+${logs.freightId}+')" /></td>
                    </shiro:hasPermission>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</form>
</body>
</html>
