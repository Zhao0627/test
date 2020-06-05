<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/layui/layui.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/layui/layui.all.js"></script>
<script type="text/javascript">
	function insert(){
        var index = layer.load();
        $.post("<%=request.getContextPath()%>/auth/resource/saveResource",
        		$("#fm").serialize(),
        		function(data){
        			if (data.code==200) {
                        layer.close(index);
                        layer.msg(data.msg, {icon: 6}, function(){
                            parent.location.href="<%=request.getContextPath()%>/auth/resource/toShow"
                        });
                        return;
					}
                    layer.msg(data.msg, {icon: 5}, function(){
                        parent.location.href="<%=request.getContextPath()%>/auth/resource/toShow"
                    });
        		})
	}
</script>
<title>Insert title here</title>
</head>
<body>
<form id="fm">
    <input type="hidden" value="${resourceId}" name="pId" >
    <label for="resourceName_01">上级名称：</label>
    <input type="text" value="${resourceName}" id="resourceName_01" style="outline:none;border: 0px"/>
	<p>
	<label for="resourceName">资源名称：</label>
	<input type="text" name="resourceName" id="resourceName">
	</p>

    <p>
	<label for="url">资源URL：</label>
	<input type="text" name="url" id="url">
	</p>

    <p>
        <label for="resourceType">资源类型：</label>
        <select id="resourceType" name="resourceType">
            <option value="1">菜单</option>
            <option value="2">按钮</option>
        </select>
    </p>
	<input type="button" value="提交" onclick="insert()" /> 
</form>
</body>
</html>