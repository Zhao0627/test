package com.dj.mall.admin.web.user.page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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
}
