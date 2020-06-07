<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/zTree_v3/css/zTreeStyle/zTreeStyle.css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/zTree_v3/css/metroStyle/metroStyle.css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/zTree_v3/css/demo.css" />

    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/layui/layui.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/layui/layui.all.js"></script>

    <script type="text/javascript" src="<%=request.getContextPath()%>/static/zTree_v3/js/jquery.ztree.core.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/zTree_v3/js/jquery.ztree.exedit.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/zTree_v3/js/jquery.ztree.excheck.js"></script>
<script type="text/javascript">
	var setting={
        check:{
            enable: true,
            chkStyle: "checkbox",
            chkboxType: { "Y" : "s", "N" : "s" }
        },
        edit: {
            enable: true,
        },
        
		data:{
			simpleData: {
				//开启简单数据格式
				enable:true,
                pIdKey:"pid"
			},
		}
		
	}
	
	$(function(){
		$.post("<%=request.getContextPath()%>/auth/role/getRoleResource",
				{"id":${id}},
				function(data){
					$.fn.zTree.init($("#ztree"),setting,data.data);
				})
		
	})

    function save() {
	    /* 获取树对象 */
        var zTreeObj = $.fn.zTree.getZTreeObj("ztree");
        /*获取节点*/
        var checkedNodes = zTreeObj.getCheckedNodes();
        /* 拼接id */
        var resourceIds = "";
        for (var i = 0; i < checkedNodes.length; i++) {
            resourceIds+=checkedNodes[i].id+",";
        }
        resourceIds = resourceIds.substring(0,resourceIds.length-1);
        var index = layer.load();
        $.post("<%=request.getContextPath()%>/auth/role/save",
            {"resourceIds":resourceIds,roleId:${id}},function (data) {
                if(data.code == 200){
                    layer.close(index);
                    layer.msg(data.msg, {icon: 6}, function(){
                        parent.location.href="<%=request.getContextPath()%>/auth/role/toShow"
                    });
                    return;
                }
                layer.msg(data.msg,{icon: 5})

        })
    }

</script>
</head>
<body>
<div class="zTreeDemoBackground left">

 	<div id="ztree" class="ztree">
    </div>
    <input type="button" value="保存" onclick="save()" />
</div>
</body>
</html>