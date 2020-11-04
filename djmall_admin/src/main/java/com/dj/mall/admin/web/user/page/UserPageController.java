package com.dj.mall.admin.web.user.page;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.admin.config.ResourceConstant;
import com.dj.mall.admin.vo.role.RoleVoResp;
import com.dj.mall.admin.vo.user.UserVoResp;
import com.dj.mall.auth.api.role.RoleService;
import com.dj.mall.auth.api.user.UserService;
import com.dj.mall.auth.dto.role.RoleDTO;
import com.dj.mall.dict.api.basedata.BaseDataService;
import com.dj.mall.dict.dto.basedata.BaseDataDTO;
import com.dj.mall.model.util.DozerUtil;
import com.dj.mall.model.util.SystemConstant;
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
     * 基础数据接口
     */
    @Reference
    private BaseDataService baseDataService;

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
    @RequiresPermissions(ResourceConstant.USER_MANAGER)
    public String toShow(Model model){
        List<RoleDTO> roleAll = roleService.findRoleAll();
        List<BaseDataDTO> basedataSex = baseDataService.getBasedataByPCode(SystemConstant.SEX_P_CODE);
        List<BaseDataDTO> basedataStatus = baseDataService.getBasedataByPCode(SystemConstant.STATUS_P_CODE);
        model.addAttribute("role", DozerUtil.mapList(roleAll, RoleVoResp.class));
        model.addAttribute("basedataStatus", basedataStatus);
        model.addAttribute("basedataSex", basedataSex);
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
        List<BaseDataDTO> basedataSex = baseDataService.getBasedataByPCode(SystemConstant.SEX_P_CODE);
        model.addAttribute("basedataSex",basedataSex);
        model.addAttribute("role",role);
        return "user/insert";
    }

    /**
     * 去修改用户信息
     * @return
     */
    @RequestMapping("toUpdate")
    @RequiresPermissions(ResourceConstant.USER_UPDATE_BTN)
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
    @RequiresPermissions(ResourceConstant.USER_REST_PWD_BTN)
    public String toUpdatePwd(String userPhone, Model model){
        model.addAttribute("userPhone",userPhone);
        return "/user/update_pwd";
    }

    /**
     * 重置密码
     * @return
     */
    @RequestMapping("toMandate")
    @RequiresPermissions(ResourceConstant.USER_ROLE_BTN)
    public String toMandate(Integer id, Model model){
        model.addAttribute("id",id);
        return "/user/mandate";
    }

}
