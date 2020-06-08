package com.dj.mall.model.util;


import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

import java.util.Random;

/**
 * 短信验证码测试
 *
 * @author chengfan
 * 2019年12月18日
 */
public class MessageVerifyUtils {
    /**
     * 产品名称:云通信短信API产品,开发者无需替换
     */
    static final String PRODUCT = "Dysmsapi";
    /**
     * 产品域名,开发者无需替换
     */
    static final String DOMAIN = "dysmsapi.aliyuncs.com";

    /**
     * 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
     */
    static final String ACCESSKEY_ID = "LTAI4Fge8PsTHj2Vej8EZC2W";
    static final String ACCESSKEY_SECRET = "IlJTPK7aCWiGvGC2ESqEOC0yLpYK3A";

    public static SendSmsResponse sendSms(String userPhone, String userCode) throws ClientException {
        // 可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        // 初始化acsClient,暂不支持region化 不能更改
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", ACCESSKEY_ID, ACCESSKEY_SECRET);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", PRODUCT, DOMAIN);
        IAcsClient acsClient = new DefaultAcsClient(profile);
        // 组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        // 必填:待发送手机号
        request.setPhoneNumbers(userPhone);
        // 必填:短信签名-可在短信控制台中找到
        request.setSignName("飞机倒档工程");
        // 必填:短信模板-可在短信控制台中找到
        request.setTemplateCode("SMS_184616437");
        //验证码为：${code}，您正在登录，若非本人操作，请勿泄露。
        request.setTemplateParam("{\"code\":\"" + userCode + "\"}");
        // hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        if (sendSmsResponse.getCode() != null && "OK".equals(sendSmsResponse.getCode())) {
            System.out.println("短信发送成功！");
        } else {
            System.out.println("短信发送失败！");
        }
        return sendSmsResponse;
    }

    //随机生成验证码
    public static String getNewcode() {
        //每次调用生成一个六位数的随机数
        return String.valueOf(new Random().nextInt(899999) + 100000);
    }

}