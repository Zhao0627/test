package com.dj.mall.admin.web.user.page;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.auth.api.role.RoleService;
import com.dj.mall.auth.api.user.UserService;
import com.dj.mall.auth.dto.role.RoleDTO;
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

    @Reference
    private RoleService roleService;

    @Reference
    private UserService userService;

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
    @RequestMapping("toInsert")
    public String toRegister(Model model){
        List<RoleDTO> role = roleService.findRoleAll();
        model.addAttribute("role",role);
        return "user/insert";
    }

    /**
     * 去修改用户信息
     * @return
     */
    @RequestMapping("toUpdate")
    public String toUpdate(){
        return "user/update";
    }

    /**
     * 注册激活
     * @return
     */
    @RequestMapping("toUpdateState")
    public String toUpdateState(Integer id){
        userService.updateActivatedState(id);
        return "user/login";
    }

}
