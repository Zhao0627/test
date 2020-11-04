<%--
  Created by IntelliJ IDEA.
  User: dj
  Date: 2020/7/29
  Time: 23:44
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
<html>
<head>
    <title>Title</title>
</head>
<body>
<div align="center">
    <div>
        <div style="width: 150px;margin-left: -560px;">
            <input type="button" style="border: none;border-radius: 3px;background-color: #169bd5;height: 45px;width: 150px;" value="新增收货地址" onclick="addSite()"/>
        </div>
        <div align="center" style="width: 800px">
            <table class="layui-hide" id="test" lay-filter="test"></table>
        </div>
    </div>
</div>
</body>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="update" style="background-color: transparent;color: #0D8BBD;">修改</a>
    <a class="layui-btn layui-btn-xs" lay-event="delete" style="background-color: transparent;color: #0D8BBD;">删除</a>
</script>
<script type="text/javascript">
    layui.use('table', function(){
        var table = layui.table;
        //分页
        var laypage = layui.laypage
            ,layer = layui.layer;
        table.render({
            elem: '#test'
            ,id:'test'	//主键id
            ,url:'<%=request.getContextPath()%>/order/receiver/siteShow?userId=${user.userId}'
            ,title: '用户数据表'
            /* 该参数可自由配置头部工具栏右侧的图标按钮  */
            ,defaultToolbar: ['', '', '']
            ,width:714
            ,cols: [[
                {field:'receiverId', title:'编号', align:'center' , width:60, fixed: 'left'}
                ,{field:'receiverName', title:'收货人', align:'center' , width:100}
                ,{field:'site', title:'详细地址', width:300,templet: function(d){
                        return '<div>'+d.receiverProvince+d.receiverCity+d.receiverCounty+d.receiverDetail+'</div>';
                    }}
                ,{field:'receiverPhone', title:'手机号', width:130}
                ,{fixed: 'right', title:'操作', toolbar: '#barDemo', align:'center' , width:120,rowspan:"2"}
            ]]
            /* 单元格点击事件名 */
            ,even:true
            /* 表格外观 */
            ,skin:"nob"
        });

        table.on('tool(test)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）
            if(layEvent === 'update'){ //查看
                alert(data.id);
                return;
                //弹出一个iframe层
                layer.open({
                    type: 2,
                    area: ['600px', '500px'],
                    //不显示关闭
                    closeBtn: 1 ,
                    title:['新增收货地址','padding-left:260px;color:white;background-color:#169bd5;font-size:10px;'],
                    /* ['点击A按钮触发','color:#fff;background-color:#01AAED;'], */
                    content: "<%=request.getContextPath()%>/order/receiver/toAddSite?TOKEN="+cookie.get("TOKEN")  /* //这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no'] */
                });
            }
            if(layEvent === 'delete'){ //查看
                $.post("<%=request.getContextPath()%>/acc/delete",
                    {"id":obj.data.id,"obj":0},
                    function(data){
                        layer.msg(data.msg,function(){
                            window.location.href="<%=request.getContextPath()%>/acc/toAccShow";
                        });
                    })
            }



        })
    })

    /**
     * 新增收货地址
     */
    function addSite() {
        //弹出一个iframe层
        layer.open({
            type: 2,
            area: ['600px', '500px'],
            //不显示关闭
            closeBtn: 1 ,
            title:['新增收货地址','padding-left:260px;color:white;background-color:#169bd5;font-size:10px;'],
            /* ['点击A按钮触发','color:#fff;background-color:#01AAED;'], */
            content: "<%=request.getContextPath()%>/order/receiver/toAddSite?TOKEN="+cookie.get("TOKEN")  /* //这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no'] */
        });
    }
</script>
</html>
