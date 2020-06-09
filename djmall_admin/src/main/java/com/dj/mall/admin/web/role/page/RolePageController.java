package com.dj.mall.admin.web.role.page;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.auth.api.role.RoleService;
import com.dj.mall.auth.dto.role.RoleDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 角色跳转处理类
 */
@Controller
@RequestMapping("/auth/role/")
public class RolePageController {

    /**
     * 角色接口
     */
    @Reference
    private RoleService roleService;

    /**
     * 去展示
     * @return
     */
    @RequestMapping("toShow")
    public String toShow(){
        return "role/show";
    }

    /**
     * 去修改角色
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("toUpdateRole")
    public String toUpdateRole(Model model, Integer id){
        RoleDTO role = roleService.findRoleById(id);
        model.addAttribute("role",role);
        return "role/update";
    }

    /**
     * 去新增角色
     * @return
     */
    @RequestMapping("toSaveRole")
    public String toSaveRole(){
        return "role/insert";
    }

    /**
     * 去关联资源
     * @return
     */
    @RequestMapping("toSaveRoleResource")
    public String toSaveRoleResource(Integer id, Model model){
        model.addAttribute("id",id);
        return "role/role_resource";
    }

}
