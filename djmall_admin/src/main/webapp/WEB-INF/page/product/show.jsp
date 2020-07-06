<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>Layui</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/static/layui/css/layui.css"  media="all">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/static/bootstrap/css/bootstrap.css"  media="all">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/static/radio.css"  media="all">
  <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-1.12.4.min.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/static/bootstrap/js/bootstrap.js"></script>
  <!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
<style type="text/css">
	.fmtable{
		margin-left: 30px;
		
	}
    .layui-table-cell {
        height: 40px;
        line-height: 40px;
    }
	.divtable{
		white-space: nowrap;
	}
	.select1{
		margin-right: 30px;
		margin-left:6px;
		width: 150px;
		height:27px;
		background: #95ecab;
		text-align:center;
		text-align-last:center;
	}
	.laber{
		background: black;
		color:white;
		font-size: 16px;
	}
	.option1{
		text-align-last:center;
	}
    .radio_type{
        width: 20px;
        height:20px;
    }
</style>
</head>
<body>
<script src="<%=request.getContextPath()%>/static/layui/layui.js" charset="utf-8"></script>
<br>
<form class="fmtable" id="fm" >
    <label class="laber">名称</label>
    <input type="text" placeholder="    商品名称" id="productName" style="background-color: #95ecab; height: 30px;border-radius:20px;">
    &nbsp;&nbsp;&nbsp;&nbsp;
    <label class="laber">分类</label>
    &nbsp;&nbsp;&nbsp;
    <c:forEach items="${productType}" var="productType">
        ${productType.name}：<input type="radio" class="userSex" name="type" value="${productType.code}" title="${productType.name}">&nbsp;&nbsp;&nbsp;
    </c:forEach>
</form>
<div><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <shiro:hasPermission name="PRODUCT_SAVE_BTN">
        <button style="height: 20px;width: 50px" id="save">新增</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    </shiro:hasPermission>

    <shiro:hasPermission name="PRODUCT_UPDATE_BTN">
        <button style="height: 20px;width: 50px" id="update">修改</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    </shiro:hasPermission>

    <shiro:hasPermission name="PRODUCT_STAND_DOWN_BTN">
    <button style="height: 20px;width: 70px" id="updown">上/下架</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    </shiro:hasPermission>

    <shiro:hasPermission name="PRODUCT_COMMENT_BTN">
    <button style="height: 20px;width: 70px" id="del">查看评论</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    </shiro:hasPermission>

    <shiro:hasPermission name="PRODUCT_DOWNLOAD_BTN">
    <button style="height: 20px;width: 100px" id="mandate">下载导入模板</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    </shiro:hasPermission>

    <shiro:hasPermission name="PRODUCT_IMPORT_BTN">
    <button style="height: 20px;width: 50px" id="mandate">导入</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    </shiro:hasPermission>

    <shiro:hasPermission name="PRODUCT_INCREMENT_BTN">
    <button style="height: 20px;width: 80px" id="mandate">增量索引</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    </shiro:hasPermission>

    <shiro:hasPermission name="PRODUCT_REFACTOR_BTN">
    <button style="height: 20px;width: 80px" id="mandate">重构索引</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    </shiro:hasPermission>
</div><br>
<table class="layui-hide" id="test" lay-filter="test"></table>
<!-- 分页部分  -->
<div id="demo7"></div>
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<script type="text/javascript">
layui.use('table', function(){
  var table = layui.table;
  //分页
   var laypage = layui.laypage
  ,layer = layui.layer;
  table.render({
    elem: '#test'
    ,id:"test"	//主键id
    ,url:'<%=request.getContextPath()%>/product/spu/Show'
   /*  ,where:{'token':token} */
    ,title: '用户数据表'
    /* 该参数可自由配置头部工具栏右侧的图标按钮  */
    ,defaultToolbar: ['', '', '']
    ,cols: [[
        {type: 'checkbox', fixed: 'left'}
      ,{field:'productId', title:'编号', width:60, fixed: 'left'}
      ,{field:'productName', title:'名称', width:100, fixed: 'left'}
      ,{field:'name', title:'类型', width:80}
      ,{field:'statusShow', title:'状态', width:80}
      ,{field:'freight', title:'邮费', width:60}
      ,{field:'img', title:'商品图片', width:90, templet: function(d){
              return '<div><img alt="" src=" http://qd0w08o2k.bkt.clouddn.com/'+d.img+'?imageView2/5/w/20/h/32/q/75" ></div>';
              }}
      ,{field:'productDescribe', title:'描述', width:500}
      ,{field:'praise', title:'点赞量', width:80}
      ,{field:'orderNumber', title:'订单量', width:80}
    ]]
    /* 单元格点击事件名 */
   	,even:true
   	/* 表格外观 */
    ,skin:"nob"
    /*,initSort : {
            field : "productId",
            type : "desc"
    }*/
  });
    /* 查询事件 */
  	$('#fm').on('change',function(){
        var type= $('input[name="type"]:checked').val();
        var productName = $('#productName').val();
        table.reload('test', {
            method: 'post'
            , where: {
                'type': type,
               'productName': productName
            }
        });
  	})

    //商品新增
    $('#save').on('click',function(){
        //弹出一个iframe层
        layer.open({
            type: 2,
            area: ['1000px', '500px'],
            //不显示关闭
            closeBtn: 1 ,
            title:['商品新增','padding-left:170px;color:white;background-color:#169bd5;font-size:10px;'],
            /* ['点击A按钮触发','color:#fff;background-color:#01AAED;'], */
            content: "<%=request.getContextPath()%>/product/spu/toSave"  /* //这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no'] */
        });
    })

    //上下架
    $('#updown').on('click',function(){
        var checkStatus = table.checkStatus('test'),
            data = checkStatus.data;
        var ids = [];
        var statu = [];
        for (var i = 0; i < data.length; i++) {
            ids.push(data[i].productId)
            statu.push(data[i].status)
        }
        if (ids.length==0){
            layer.msg("请选择一个");
            return;
        }
        if (ids.length>1){
            layer.msg("只能选择一个");
            return;
        }
        var sta=0;
        if (statu[0] == "PRODUCT_DOWN"){
            sta+=1;
        }else{
            sta+=0;
        }
        $.post("<%=request.getContextPath()%>/product/spu/productDown",
            {"productId":ids[0],"sta":sta},
            function (data) {
                if (data.code==200){
                    layer.msg(data.msg,function () {
                        window.location.href="<%=request.getContextPath()%>/product/spu/toShow";
                    });
                }
        })
    })
})
</script>
</body>
</html>