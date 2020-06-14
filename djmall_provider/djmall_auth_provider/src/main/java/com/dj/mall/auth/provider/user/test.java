package com.dj.mall.auth.provider.user;

import com.dj.mall.model.util.PasswordSecurityUtil;

public class test {
    public static void main(String[] args) throws Exception{
        String userPwd = PasswordSecurityUtil.enCode32(PasswordSecurityUtil.enCode32("123") + "aba7c59cc7b62ab6294763b0d663bdda");
        System.out.println(userPwd);
    }
}
