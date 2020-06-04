package com.dj.mall.admin.web.user.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户跳转页面处理类
 */
@RequestMapping("/auth/user/")
@Controller
public class UserPageController {

    /**
     * 去登陆
     * @return
     */
    @RequestMapping("toLogin")
    public String toLogin(){
        return "user/login";
    }

    /**
     * 去展示
     * @return
     */
    @RequestMapping("toShow")
    public String toShow(){
        return "user/show";
    }

    /**
     * 去修改密码
     * @return
     */
    @RequestMapping("toUpdatePwd")
    public String toUpdatePwd(){
        return "user/update_pwd";
    }

    /**
     * 去新增用户
     * @return
     */
    @RequestMapping("toRegister")
    public String toRegister(){
        return "user/register";
    }

    /**
     * 去修改用户信息
     * @return
     */
    @RequestMapping("toUpdate")
    public String toUpdate(){
        return "user/update";
    }

}
