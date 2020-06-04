package com.dj.mall.admin.web.role;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.admin.vo.RoleVoReq;
import com.dj.mall.admin.vo.RoleVoResp;
import com.dj.mall.auth.api.role.RoleService;
import com.dj.mall.auth.dto.role.RoleDTO;
import com.dj.mall.model.base.ResultModel;
import com.dj.mall.model.util.DozerUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 角色处理类
 */
@RestController
@RequestMapping("/auth/role/")
public class RoleController {

    @Reference
    private RoleService roleService;

    /**
     * 角色展示
     * @return
     */
    @RequestMapping("show")
    public ResultModel<Object> show(){
        List<RoleDTO> roleAll = roleService.findRoleAll();
        return new ResultModel<>().success(roleAll);
    }

    /**
     * 角色编辑操作
     * @return
     */
    @RequestMapping("updateRole")
    public ResultModel<Object> updateRole(RoleVoResp roleVoResp){
        roleService.update(DozerUtil.map(roleVoResp, RoleDTO.class));
        return new ResultModel<>().success("修改成功");
    }

    /**
     * 角色新增操作
     * @return
     */
    @RequestMapping("saveRole")
    public ResultModel<Object> saveRole(RoleVoResp roleVoResp){
        roleService.save(DozerUtil.map(roleVoResp, RoleDTO.class));
        return new ResultModel<>().success("新增成功");
    }

    /**
     * 角色新增操作
     * @return
     */
    @RequestMapping("deleteRole")
    public ResultModel<Object> deleteRole(Integer id){
        roleService.delete(id);
        return new ResultModel<>().success("删除成功");
    }

}
