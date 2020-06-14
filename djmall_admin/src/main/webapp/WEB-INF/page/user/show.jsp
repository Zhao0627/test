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
    <label class="laber">模糊搜索</label>
    <input type="text" placeholder="用户名/手机号/邮箱" id="namePhoneEmail" style="background-color: #95ecab; height: 30px;border-radius:20px;">
    &nbsp;&nbsp;&nbsp;&nbsp;
    <label class="laber">性别查询</label>
    &nbsp;&nbsp;&nbsp;
    男：<input type="radio" class="userSex" name="userSex" value="1" title="男">&nbsp;&nbsp;&nbsp;
    女：<input type="radio" class="userSex" name="userSex" value="2" title="女">

    &nbsp;&nbsp;&nbsp;
    <label class="laber">级别查询</label>
    &nbsp;&nbsp;&nbsp;
    <c:forEach items="${role}" var="role">
        ${role.roleName}&nbsp;&nbsp;&nbsp;<input type="radio" name="userLevel" value="${role.roleId}" class="userLevel">&nbsp;&nbsp;&nbsp;
    </c:forEach>

    &nbsp;&nbsp;&nbsp;
<label class="laber">状态搜索:</label>
	<select name='activatedState' id="activatedState" class="select1">
		<option value="" >请选择！</option>
		<option value="2" class="option1">正常</option>
		<option value="1" class="option1">未激活</option>
</select>
</form>
<div><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <shiro:hasPermission name="USER_UPDATE_BTN">
        <button style="height: 20px;width: 50px" id="update">修改</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    </shiro:hasPermission>
    <shiro:hasPermission name="USER_ACTIVATE_BTN">
        <button style="height: 20px;width: 50px" id="activate">激活</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    </shiro:hasPermission>
    <shiro:hasPermission name="USER_REST_PWD_BTN">
    <button style="height: 20px;width: 70px" id="update_pwd">重置密码</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    </shiro:hasPermission>
    <shiro:hasPermission name="USER_DELETE_BTN">
    <button style="height: 20px;width: 50px" id="del">删除</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    </shiro:hasPermission>
    <shiro:hasPermission name="USER_ROLE_BTN">
    <button style="height: 20px;width: 50px" id="mandate">授权</button>
    </shiro:hasPermission>
</div>
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
    ,url:'<%=request.getContextPath()%>/auth/user/show'
   /*  ,where:{'token':token} */
    ,title: '用户数据表'
    /* 该参数可自由配置头部工具栏右侧的图标按钮  */
    ,defaultToolbar: ['', '', ''] 
    ,cols: [[
        {type: 'checkbox', fixed: 'left'}
      ,{field:'userId', title:'ID', width:50, fixed: 'left'}
      ,{field:'userName', title:'用户名', width:80}
      ,{field:'nickName', title:'昵称', width:80}
      ,{field:'userPhone', title:'手机号', width:130}
      ,{field:'userEmail', title:'邮箱', width:190}
      ,{field:'userSexShow', title:'性别', width:80}
      ,{field:'userLevelShow', title:'用户级别', width:100,}
      ,{field:'stateShow', title:'状态', width:100,}
      ,{field:'saveTimeShow', title:'注册时间', width:180,}
      ,{field:'loginTime', title:'登录时间', width:180,}
/*      ,{fixed: 'right', title:'操作', toolbar: '#barDemo', width:150,rowspan:"3"}*/
    ]]
    /* 单元格点击事件名 */
   	,even:true
   	/* 表格外观 */
    ,skin:"nob"
    ,initSort : {
            field : "userId",
            type : "desc"
    }
  });
  
    /* 查询事件 */
  	$('#fm').on('click',function(){
        var userSex= $('input[name="userSex"]:checked').val();
        var userLevel= $('input[name="userLevel"]:checked').val();
        var userName = $('#namePhoneEmail').val();
        var activatedState = $('#activatedState').val();
        table.reload('test', {
            method: 'post'
            , where: {
                'userSex': userSex,
               'userName': userName,
                'activatedState': activatedState,
                'userLevel': userLevel,
            }
        });
  	})

    //用户修改
    $('#update').on('click',function(){
        var checkStatus = table.checkStatus('test')
            ,data = checkStatus.data;
        var ids = [];
        for (var i = 0; i < data.length; i++) {
            ids.push(data[i].userId)
        }
        if (ids.length==0){
            layer.msg("请选择一个");
            return;
        }
        if (ids.length>1){
            layer.msg("只能选择一个");
            return;
        }
        //弹出一个iframe层
        layer.open({
            type: 2,
            area: ['350px', '430px'],
            //不显示关闭
            closeBtn: 1 ,
            title:['用户修改','padding-left:170px;color:white;background-color:#169bd5;font-size:10px;'],
            /* ['点击A按钮触发','color:#fff;background-color:#01AAED;'], */
            content: "<%=request.getContextPath()%>/auth/user/toUpdate?id="+ids[0]  /* //这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no'] */
        });
    })

    //激活
    $('#activate').on('click',function(){
        var checkStatus = table.checkStatus('test')
            ,data = checkStatus.data;
        var ids = [];
        var userNames=[];
        for (var i = 0; i < data.length; i++) {
            if (data[i].activatedState===2){
                userNames.push(data[i].userName);
            }
        }
        for (var i = 0; i < data.length; i++) {
            if (data[i].activatedState===1){
                ids.push(data[i].userId);
            }
        }
        if (userNames.length>0){
            layer.msg(userNames+"已激活", {
                time: 2000 //2秒关闭（如果不配置，默认是3秒）
            }, function(){
                if (ids.length>0){
                    layer.confirm('您确定要激活用户吗？', {
                        btn: ['确定','取消'] //按钮
                    }, function(){
                        $.post("<%=request.getContextPath()%>/auth/user/updateState",
                            {"ids":ids.join(',')},
                            function(data){
                                layer.msg(data.msg,function(){
                                    window.location.href="<%=request.getContextPath()%>/auth/user/toShow";
                                    return;
                                });
                            })
                    }, function(){
                        layer.msg('已取消');
                    });
                    return;
                }
            });
            return;
        }
        if (ids.length>0){
            layer.confirm('您确定要激活用户吗？', {
                btn: ['确定','取消'] //按钮
            }, function(){
                $.post("<%=request.getContextPath()%>/auth/user/updateState",
                    {"ids":ids.join(',')},
                    function(data){
                        layer.msg(data.msg,function(){
                            window.location.href="<%=request.getContextPath()%>/auth/user/toShow";
                            return;
                        });
                    })
            }, function(){
                layer.msg('已取消');
            });
        }
    })

    //重置密码
    $('#update_pwd').on('click',function(){
        //获取选中数据
        var checkStatus = table.checkStatus('test')
            ,data = checkStatus.data;
        //获取选中ids
        var ids = [];
        for (var i = 0; i < data.length; i++) {
            ids.push(data[i].userId)
        }
        //获取选中emails
        var emails = [];
        for (var i = 0; i < data.length; i++) {
            emails.push(data[i].userEmail)
        }
        layer.confirm('您确定要重置密码吗？重置后的密码以邮件的方式进行通知', {
            btn: ['确定','取消'] //按钮
        }, function(){
            debugger;
            $.post("<%=request.getContextPath()%>/auth/user/resetPwd",
                {"ids":ids.join(','),"emails":emails.join(',')},
                function(data){
                    layer.msg(data.msg,function(){
                        window.location.href="<%=request.getContextPath()%>/auth/user/toShow";
                    });
            })
        }, function(){
            layer.msg('已取消');
        });
    })

    //删除
    $('#del').on('click',function(){
        //获取选中数据
        var checkStatus = table.checkStatus('test')
            ,data = checkStatus.data;
        //获取选中ids
        var ids = [];
        for (var i = 0; i < data.length; i++) {
            ids.push(data[i].userId)
        }
        layer.confirm('您确定要删除该用户及其用户全部信息吗', {
            btn: ['确定','取消'] //按钮
        }, function(){
                $.post("<%=request.getContextPath()%>/auth/user/delUser",
                {"ids":ids.join(',')},
                function(data){
                    layer.msg(data.msg,function(){
                        window.location.href="<%=request.getContextPath()%>/auth/user/toShow";
                    });
            })
        }, function(){
            layer.msg('已取消');
        });
    })
})
</script>
</body>
</html>