package com.dj.mall.auth.provider.user;

import com.dj.mall.model.util.PasswordSecurityUtil;

public class test {

    public static void main(String[] args) throws Exception {
        String userPwd = PasswordSecurityUtil.enCode32(PasswordSecurityUtil.enCode32("1392495684") + "ea4b0949586769ccf53fb25a05f6288d");
        System.out.println(userPwd);
    }
}
