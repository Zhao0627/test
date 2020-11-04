package com.dj.mall.task.direct;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.dj.mall.auth.api.email.MailService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 单播-消费者
 */
@Component
public class DirectConsumer {

    @Reference
    private MailService mailService;

    /**
     * 消费者1
     * @param message 消息体
     * @throws Exception
     */
    @RabbitHandler
    @RabbitListener(queues = "directQueue1")
    public void process1(Message message) throws Exception {
        String s = new String(message.getBody(), "UTF-8");
        JSONObject jsonObject = JSONObject.parseObject(s);
        String userEmail = jsonObject.getString("userEmail");
        String nickName = jsonObject.getString("nickName");
        String userId = jsonObject.getString("userId");
        String content = "'恭喜："+nickName+"注册成功，"+"点击<a href='http://127.0.0.1:8081/admin/auth/user/toUpdateState?id=\n"+Integer.valueOf(userId)+"\n'>这里</a>激活";
        mailService.sendHtmlMail(userEmail, "注册激活登录", content);
    }
    /**
     * 消费者2
     * @param message 消息体
     * @throws Exception
     */
    @RabbitHandler
    @RabbitListener(queues = "directQueue2")
    public void process2(Message message) throws Exception {
        String s = new String(message.getBody(), "UTF-8");
        JSONObject jsonObject = JSONObject.parseObject(s);
        String userEmail = jsonObject.getString("userEmail");
        String nickName = jsonObject.getString("nickName");
        String userName = jsonObject.getString("userName");
        String newDate = jsonObject.getString("newDate");
        String resetPwd = jsonObject.getString("resetPwd");
        String content = "尊敬的"+nickName+"，<br>"+"您的密码已被管理员"+userName+"于"+newDate+"重置为<font color='red'>"+resetPwd+"</font>。<br>为了您的账户安全，请及时修改。"+
                "<a href='http://127.0.0.1:8081/admin/auth/user/toLogin' style='color: red;text-decoration:none;'>点我去登录</a>";
        mailService.sendHtmlMail(userEmail, "重置密码", content);
    }
}
