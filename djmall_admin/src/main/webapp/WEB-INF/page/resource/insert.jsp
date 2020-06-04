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
	function yesOrNo(Obj){
		if (Obj==1) {
			$("#pId").hide();
		}
		if (Obj==0) {
			$("#pId").show();
		}
	}
	
	function insert(){
        var index = layer.load();
        $.post("<%=request.getContextPath()%>/auth/resource/saveResource",
        		$("#fm").serialize(),
        		function(data){
        			if (data.code==200) {
                        layer.close(index);
                        layer.msg(data.msg, {icon: 1}, function(){
                            parent.location.href="<%=request.getContextPath()%>/auth/resource/toShow"
                        });
					}
        		})
	}
</script>
<title>Insert title here</title>
</head>
<body>
<form id="fm">
	<p>
	<label for="resourceName">资源名称</label>
	<input type="text" name="resourceName" id="resourceName">
	</p>

    <p>
	<label for="url">资源URL</label>
	<input type="text" name="url" id="url">
	</p>

    <p>
        <label for="resourceType">资源类型</label>
        <select id="resourceType" name="resourceType">
            <option value="1">菜单</option>
            <option value="2">按钮</option>
        </select>
    </p>

	<p>
	是否上级
	<label for="yes">是</label>
	<input type="radio" id="yes" name="yes" onclick="yesOrNo(1)" >
	<label for="no">否</label>
	<input type="radio" id="no" name="yes" onclick="yesOrNo(0)" >
	</p>
	<p>
	<select name="pId" hidden="" id="pId">
		<option value="0">请选择父级</option>
		<c:forEach items="${resource}" var="resource">
			<option value="${resource.resourceId}">${resource.resourceName}</option>
		</c:forEach>
	</select>
	</p>
	<input type="button" value="提交" onclick="insert()" /> 
</form>
</body>
</html>