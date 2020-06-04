<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <meta charset="utf-8">
    <title>登入 - 知名商城</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
<link rel="stylesheet" href="<%=request.getContextPath()%>/static/zTree_v3/css/zTreeStyle/zTreeStyle.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/static/zTree_v3/css/metroStyle/metroStyle.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/static/zTree_v3/css/demo.css" />

<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-1.12.4.min.js"></script>

<script type="text/javascript" src="<%=request.getContextPath()%>/static/zTree_v3/js/jquery.ztree.core.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/zTree_v3/js/jquery.ztree.exedit.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/zTree_v3/js/jquery.ztree.excheck.js"></script>
<title>Insert title here</title>
<script type="text/javascript">
			var setting = {
					data:{
						simpleData: {
							//开启简单数据格式
							enable:true,
							//parentId
							idKey:"id",
							pIdKey:"parentId",
						},
					},
					async: {
						enable: true,
						target:"target",
						url:"url"
					}
			};
 			var zNodes =[
                {
                    name:"运营平台", open:false,
                        children:[
                            {name: "用户管理", open: false, },
                            { name:"基础管理", open:false,
                                children: [
                                    { name:"运费",target:"right",url:"<%=request.getContextPath()%>/base/toBaseShow"},
                                    { name:"字典数据",target:"right",url:"<%=request.getContextPath()%>/base/toBaseShow"},
                                    { name:"商品属性维护",target:"right",url:"<%=request.getContextPath()%>/base/toBaseShow"},
                                    { name:"通用SKU维护",target:"right",url:"<%=request.getContextPath()%>/base/toBaseShow"}
                                ]},
                            {name: "订单管理", open: false, },
                            {name: "商品管理", open: false, },
                            { name:"权限管理", open:false,
                                children: [
                                    {
                                        name:"资源管理",target: "right", url:"<%=request.getContextPath()%>/auth/resource/toShow", open:false,
                                            children :[
                                                { name:"新增资源",target:"right",url:"<%=request.getContextPath()%>/base/toBaseShow"},
                                            ]

                                    },
                                    {
                                        name:"角色管理",target: "right", url:"<%=request.getContextPath()%>/auth/role/toShow", open:false,
                                        children :[
                                            { name:"新增角色",target:"right",url:"<%=request.getContextPath()%>/auth/role/toShow"},
                                            { name:"关联资源",target:"right",url:"<%=request.getContextPath()%>/base/toBaseShow"}
                                        ]

                                    },
                                ]}
                        ]
                },

		];
		
/*		$(function(){
			$.post("<%=request.getContextPath()%>/menu/menuShow?token="+token,
					{},
					function(data){
						$.fn.zTree.init($("#treeDemo"),setting,data.data);
					})
			
		})*/
            $(function(){
            $.fn.zTree.init($("#treeDemo"),setting,zNodes);
            })
</script>	
</head>
<body>
	<div id="treeDemo" class="ztree"></div></body>
</body>
</html>