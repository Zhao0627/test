<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
		padding: 0 5%;
	}
	.laber{
		background: black;
		color:white;
		font-size: 16px;
	}
	.option1{
		text-align:center;
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
    <label class="laber">模糊搜索</label>
    <input type="text" placeholder="Username" style="background-color: #95ecab; height: 30px">
    <label class="laber">级别查询</label>
    <input type="radio" class="" title="男">男
    <input type="radio" class="" title="男">男


<label class="laber">性别搜索:</label>
	<select name='userId' class="select1" id="userId" >
		<option value="" class="option1">全部</option>
		<c:forEach items="${accAll}" var='accAll'>
			<option class="option1" value="${accAll.id}">${accAll.userName}</option>
		</c:forEach>
	</select>
</form>
<c:if test="${user.userLevel != 24}">
<div style=" margin:-20px 0px 0px 1200px;">
<input type="button" value=" 填 写 报 销 单 " style="border:none; background:#95ecab;" onclick="insertAcc()" />
</div>
</c:if>
<table class="layui-hide" id="test" lay-filter="test"></table>  

<!-- 加载更多 -->
<center>
<div id="div">
<a id="jiazai" >点击加载更多!</a>

</div>

</center>
<!-- 分页部分  --> 
<div id="demo7"></div>
<script type="text/html" id="barDemo">
     <a class="layui-btn layui-btn-xs" lay-event="top">置顶</a>
     
</script>
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
    ,url:'<%=request.getContextPath()%>/auth/user/show'
    
   /*  ,where:{'token':token} */
    ,title: '用户数据表'
    
    /* 是否开启合计 默认false */
    ,totalRow:true
    
    /* 该参数可自由配置头部工具栏右侧的图标按钮  */
    ,defaultToolbar: ['', '', ''] 
    ,cols: [[
      /* {type: 'checkbox', fixed: 'left'} */
      {field:'userId', title:'ID', width:80, fixed: 'left', totalRowText: '合计行'}
      ,{field:'userName', title:'用户名', width:120}
      ,{field:'nickName', title:'昵称', width:120}
      ,{field:'userPhone', title:'手机号', width:150}
      ,{field:'userEmail', title:'邮箱', width:170}
      ,{field:'userSexShow', title:'性别', width:100,totalRow:true}
      ,{field:'userLevel', title:'用户级别', width:150,}
      ,{fixed: 'right', title:'操作', toolbar: '#barDemo', width:70,rowspan:"3"}
    ]]
   /* 每页显示条数 */
    ,limit:pageSize
    
    /* 单元格点击事件名 */
   	,even:true
   	
   	/* 表格外观 */
    ,skin:"nob"     
    
/*    ,done: function (res, page, count) {
        var that = this.elem.next();
        console.log(that)
        res.data.forEach(function (item, index) {
        	if (item.stickyTime != item.creatTime) {
                var tr = that.find(".layui-table-box tbody tr[data-index='" + index + "']").css("color", "#FF0000");
			}
        });
    }*/

  });
  
  /* 分页 */
/*  laypage.render({
	    elem: 'demo7'
	    ,count:${count}
	    ,layout: ['count', 'prev', 'page', 'next', 'limit', 'refresh', 'skip']
	    ,limit:3
  		,limits:[3, 6, 9, 12, 15]
	    ,jump: function(obj){
		      table.reload('test', {
		            method: 'post'
		            , where: {
		            	'page':obj.curr,
		            	'pageSize':obj.limit
		            }
		        });
	    }
	  });*/
  
  

  /* 双击跳转弹框查看用 */
  table.on('rowDouble(test)', function(obj){
			  //弹出一个iframe层 
			layer.open({
				  type: 2, 
				  area: ['350px', '430px'],
				  //不显示关闭
				  closeBtn: 1 ,
				  title:['报销单','padding-left:170px;color:white;background-color:#169bd5;font-size:10px;'],
			/* ['点击A按钮触发','color:#fff;background-color:#01AAED;'], */
				  content:'<%=request.getContextPath()%>/track/toTrack?id='+obj.data.id   /* //这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no'] */
				});
	}); 
  
    /* 查询事件 */
  	$('#fm').on('click',function(){
        var userId = $("#userId").val();
        var incomeProject = $('#incomeProject').val();
        var incomeState = $('#incomeState').val();
        table.reload('test', {
            method: 'post'
            , where: {
                'incomeProject': incomeProject,
               'userId': userId,
                'incomeState': incomeState
            }
        });
  	})
  
    /* 加载事件 */
    var pageSize = 3;
  	$('#jiazai').on('click',function(){
  		pageSize+=2;
  		if (${count}+2<=pageSize) {
  			var html = "";
  			html+="<span style='color: gray;'>我也是有底线的</span>";
  			$("#div").html(html);
			return ;
		}
        table.reload('test', {
            method: 'post'
            , where: {
                'pageSize':pageSize,
            }
        });
  	})
  	
  	/* 置顶 */
  	table.on('tool(test)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
		  var data = obj.data; //获得当前行数据
		  var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
		  var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）
		  if(layEvent === 'top'){ //查看
			  $.post("<%=request.getContextPath()%>/acc/updateStickyTime",
					  {"id":obj.data.id,"obj":0},
					  function(data){
						  	layer.msg(data.msg,function(){
							  	window.location.href="<%=request.getContextPath()%>/acc/toAccShow";
						  	});
						  })
					  }
		  })
})

	//弹出一个iframe层 新增用
	function insertAcc(){
	/* 填写报销单弹窗 */
		layer.open({
			  type: 2, 
			  area: ['400px', '450px'],
			  //不显示关闭按钮
			  closeBtn: 1 ,
			  /* title:'报销' , */
			  title:['报销 ','padding-left:170px;color:white;background-color:#169bd5;font-size:10px;'],
		/* ['点击A按钮触发','color:#fff;background-color:#01AAED;'], */
			  content:'<%=request.getContextPath()%>/acc/iframeShow'   /* //这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no'] */
			}); 
	}
	//弹出一个iframe层 新增用
	function updateTime(id){
	 	 $.post("<%=request.getContextPath()%>/acc/updateStickyTime",
				  {"id":id,"obj":1},
				  function(data){
					  	layer.msg(data.msg,function(){
						  	window.location.href="<%=request.getContextPath()%>/acc/toAccShow";
					  	});
					  });
	}
</script>
</body>
</html>