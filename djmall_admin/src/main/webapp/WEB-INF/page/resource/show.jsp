<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
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
/*        check:{
            enable: true,
            chkStyle: "checkbox",
            chkboxType: { "Y" : "s", "N" : "s" }
        },*/
/*		view:{
			addHoverDom:addHoverDom,
			removeHoverDom: removeHoverDom,
            selectedMulti:false,
			fontCss: {color:"red"} 
		},
		async:{
            enable: true,
		},
        edit: {
            enable: true,
            removeTitle:"刪除节点",
            renameTitle:"编辑节点名称",
            showRenameBtn:true,
            drag:{
            	isCopy:false,
            	siMove:false
            }
        },
        
        callback:{
        	beforeRename:zTreeBeforeRename,
        	beforeRemove: myBeforeRemove
        },*/
        
		data:{
			simpleData: {
				//开启简单数据格式
				enable:true,
				//parentId
				idKey:"resourceId",
				pIdKey:"pid",
                target : "da"
			},
			key: {
				//名字
				name:"resourceName",
			},
		}
		
	}
	
	$(function(){
		$.post("<%=request.getContextPath()%>/auth/resource/show",
				{},
				function(data){
					$.fn.zTree.init($("#ztree"),setting,data.data);
				})
		
	})
	
	function zTreeBeforeRename(treeId,treeNode,newName,isCancel){
		var id=treeNode.resourceId;
		$.post("<%=request.getContextPath()%>/auth/resource/updateResource",
				{"resourceName":newName,"resourceId":id},
				function(data){
					if (data.code==200) {
						layer.msg(data.msg);
						return true;
					}
				return false;
				});
	}
	
	function myBeforeRemove(treeId, treeNode) {
		$.post("<%=request.getContextPath()%>/auth/resource/deleteResource",
				{"resourceId":treeNode.resourceId},
				function(data){
					if (data.code==200) {
						layer.msg(data.msg);
						return true;
					}
				return false;
				});
	}

	
	/* 新增 */
    var newCount = 1;
    function addHoverDom(treeId,treeNode){
        var sObj = $("#" + treeNode.tId + "_span");
        if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
        var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
            + "' title='添加子节点' onfocus='this.blur();'></span>";
        sObj.after(addStr);
        var btn = $("#addBtn_"+treeNode.tId);
        if (btn) btn.bind("click", function(){
        	var typeName = prompt("请输入类别名称");
        	if (typeName==null) {
				return false;
			}
        	if (typeName.length<=0) {
				layer.msg("请输入正确的类别名称");
				return false;
			}
            $.post("<%=request.getContextPath()%>/auth/resource/saveResource",
            		{"pId":treeNode.resourceId,"resourceName":typeName},
            		function(data){
            			if (data.code==200) {
	            			layer.msg(data.msg)
	            			window.location.href="<%=request.getContextPath()%>/auth/resource/toShow";
					       return true;
						}
						return false;
            		})
        });
    }
    
    function removeHoverDom(treeId, treeNode) {
		$("#addBtn_"+treeNode.tId).unbind().remove();
	};
	
	function save(){
        var treeObj = $.fn.zTree.getZTreeObj("ztree");
        var nodes = treeObj.getSelectedNodes();
        var pId = 0 ;
        if (nodes.length>0 ){
            pId = nodes[0].resourceId;
        }
        layer.open({
            type: 2,
            title: '新增',
            shadeClose: true,
            shade: 0.8,
            area: ['380px', '90%'],
            content: "<%=request.getContextPath()%>/auth/resource/toSaveResource?resourceId="+pId //iframe的url
        });
	}
	
	function del() {
        var treeObj = $.fn.zTree.getZTreeObj("ztree");
        var nodes = treeObj.getSelectedNodes();
        if (nodes.length<=0){
            layer.msg("选择删除");
            return;
        }
        var resourceId=nodes[0].resourceId;
        $.post("<%=request.getContextPath()%>/auth/resource/deleteResource",
            {"resourceId":resourceId},
            function(data){
                if (data.code==200) {
                    layer.msg(data.msg)
                    window.location.href="<%=request.getContextPath()%>/auth/resource/toShow";
                    return true;
                }
                return false;
            })
    }

	function update() {

        var treeObj = $.fn.zTree.getZTreeObj("ztree");
        var nodes = treeObj.getSelectedNodes();
        if (nodes.length<=0){
            layer.msg("选择修改");
            return;
        }
        layer.open({
            type: 2,
            title: '修改',
            shadeClose: true,
            shade: 0.8,
            area: ['380px', '90%'],
            content: "<%=request.getContextPath()%>/auth/resource/toUpdateResource?resourceId="+nodes[0].resourceId //iframe的url
        });
        /*window.location.href="<%=request.getContextPath()%>/auth/resource/deleteResource?resourceId="+;*/
    }
</script>
</head>
<body>
<div class="zTreeDemoBackground left">
    <shiro:hasPermission name="RESOURCE_SAVE_BTN">
        <input type="button" value="新增" onclick="save()" />
    </shiro:hasPermission>
    <shiro:hasPermission name="RESOURCE_UPDATE_BTN">
	    <input type="button" value="编辑" onclick="update()" />
    </shiro:hasPermission>
    <shiro:hasPermission name="RESOURCE_DELETE_BTN">
	    <input type="button" value="删除" onclick="del()" />
    </shiro:hasPermission>
 	<div id="ztree" class="ztree">
    </div>
</div>
</body>
</html>