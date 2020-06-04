package com.dj.mall.admin.web.user.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/auth/user/")
@Controller
public class UserPageController {

    @RequestMapping("toLogin")
    public String toLogin(){
        return "user/login";
    }

    @RequestMapping("toShow")
    public String toShow(){
        return "user/show";
    }

    @RequestMapping("toUpdatePwd")
    public String toUpdatePwd(){
        return "user/update_pwd";
    }

    @RequestMapping("toRegister")
    public String toRegister(){
        return "user/register";
    }

    @RequestMapping("toUpdate")
    public String toUpdate(){
        return "user/update";
    }

}
