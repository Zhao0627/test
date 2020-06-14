package com.dj.mall.admin.web.user.page;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.admin.vo.RoleVoResp;
import com.dj.mall.admin.vo.UserVoResp;
import com.dj.mall.auth.api.role.RoleService;
import com.dj.mall.auth.api.user.UserService;
import com.dj.mall.auth.dto.role.RoleDTO;
import com.dj.mall.model.util.DozerUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
     * 角色接口
     */
    @Reference
    private RoleService roleService;

    /**
     * 用户接口
     */
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
    @RequiresPermissions("USER_MANAGER")
    public String toShow(Model model){
        List<RoleDTO> roleAll = roleService.findRoleAll();
        model.addAttribute("role", DozerUtil.mapList(roleAll, RoleVoResp.class));
        return "user/show";
    }

    /**
     * 去修改密码
     * @return
     */
    @RequestMapping("toForgetPwd")
    public String toUpdatePwd(){
        return "user/forget_pwd";
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
    @RequiresPermissions("USER_UPDATE_BTN")
    public String toUpdate(Integer id, Model model){
        UserVoResp user = DozerUtil.map(userService.findUserById(id), UserVoResp.class);
        model.addAttribute("user",user);
        return "user/update";
    }

    /**
     * 注册激活
     * @return
     */
    @RequestMapping("toUpdateState")
    public String toUpdateState(Integer id){
        Integer ids[] = new Integer[1];
        ids[0]=id;
        userService.updateActivatedState(ids);
        return "user/login";
    }

    /**
     * 重置密码
     * @return
     */
    @RequestMapping("toUpdatePwd")
    @RequiresPermissions("USER_REST_PWD_BTN")
    public String toUpdatePwd(String userPhone, Model model){
        model.addAttribute("userPhone",userPhone);
        return "/user/update_pwd";
    }

}
