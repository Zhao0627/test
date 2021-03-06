var token_get = function (url, callback) {
    $.ajax({
        type: "GET",
        url: url,
        beforeSend: function (request) {
            request.setRequestHeader("TOKEN", cookie.get("TOKEN"));
        },
        success: callback
    });
};
var token_post = function (url, param, callback) {
    $.ajax({
        type: "POST",
        url: url,
        data: param,
        beforeSend: function (request) {
            request.setRequestHeader("TOKEN", cookie.get("TOKEN"));
        },
        success: callback
    });
};

// 设置登录的cookie信息
var set_login = function (TOKEN, NICK_NAME) {
    cookie.set("TOKEN", TOKEN, 21);
    cookie.set("NICK_NAME", NICK_NAME, 21);
}
// 验证是否登陆
var check_login = function () {
    return (cookie.get("TOKEN") != undefined && cookie.get("NICK_NAME") != undefined && cookie.get("NICK_NAME") != "" && cookie.get("TOKEN") != "");
};
// 退出登录
var logout = function () {
    cookie.delete("TOKEN");
    cookie.delete("NICK_NAME");
    /*cookie.clear();*/
    toLogin();
}

function getToken() {
    return cookie.get("TOKEN");
}

// 去登录
function toLogin() {
    //弹出一个iframe层
    layer.open({
        type: 2,
        area: ['270px', '325px'],
        //不显示关闭
        closeBtn: 1 ,
        title:['用户登录','padding-left:110px;color:white;background-color:#169bd5;font-size:10px;'],
        /* ['点击A按钮触发','color:#fff;background-color:#01AAED;'], */
        content: "http://localhost:8085/platform/consumer/toLogin"  /* //这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no'] */
    });
}

// Ajax请求 返回拦截
$.ajaxSetup({
    type: "POST",
    error: function (jqXHR, textStatus, errorMsg) {  // 出错时默认的处理函数
        // jqXHR 是经过jQuery封装的XMLHttpRequest对象
        // textStatus 可能为： null、"timeout"、"error"、"abort"或"parsererror"
        // errorMsg 可能为： "Not Found"、"Internal Server Error"等
        switch (jqXHR.status) {
            case(500):
                alert("服务器系统内部错误");
                break;
            case(666):
                alert("未登录");
                logout();
                break;
            case(403):
                alert("当前用户没有权限");
                break;
            case(408):
                alert("请求超时");
                break;
            default:
                alert("未知错误");
        }
    }
});
$.ajaxSetup({
    type: "GET",
    error: function (jqXHR, textStatus, errorMsg) {  // 出错时默认的处理函数
        // jqXHR 是经过jQuery封装的XMLHttpRequest对象
        // textStatus 可能为： null、"timeout"、"error"、"abort"或"parsererror"
        // errorMsg 可能为： "Not Found"、"Internal Server Error"等
        switch (jqXHR.status) {
            case(500):
                alert("服务器系统内部错误");
                break;
            case(666):
                alert("未登录");
                logout();
                break;
            case(403):
                alert("当前用户没有权限");
                break;
            case(408):
                alert("请求超时");
                break;
            default:
                alert("未知错误");
        }
    }
});