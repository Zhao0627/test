package com.dj.mall.admin.web.role;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.admin.config.ResourceConstant;
import com.dj.mall.admin.vo.role.RoleVoResp;
import com.dj.mall.auth.api.role.RoleService;
import com.dj.mall.auth.dto.ZtreeDataDTO;
import com.dj.mall.auth.dto.role.RoleDTO;
import com.dj.mall.model.base.ResultModel;
import com.dj.mall.model.util.DozerUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 角色处理类
 */
@RestController
@RequestMapping("/auth/role/")
public class RoleController {

    /**
     * 角色接口
     */
    @Reference
    private RoleService roleService;

    /**
     * 角色展示
     * @return
     */
    @RequestMapping("show")
    @RequiresPermissions(ResourceConstant.ROLE_MANAGER)
    public ResultModel<Object> show() throws Exception{
        List<RoleDTO> roleAll = roleService.findRoleAll();
        return new ResultModel<>().success(DozerUtil.mapList(roleAll, RoleVoResp.class));
    }

    /**
     * 角色编辑操作
     * @return
     */
    @RequestMapping("updateRole")
    @RequiresPermissions(ResourceConstant.ROLE_UPDATE_BTN)
    public ResultModel<Object> updateRole(RoleVoResp roleVoResp) throws Exception{
        roleService.update(DozerUtil.map(roleVoResp, RoleDTO.class));
        return new ResultModel<>().success("修改成功");
    }

    /**
     * 角色新增操作
     * @return
     */
    @RequestMapping("saveRole")
    @RequiresPermissions(ResourceConstant.ROLE_SAVE_BTN)
    public ResultModel<Object> saveRole(RoleVoResp roleVoResp) throws Exception{
        Assert.hasLength(roleVoResp.getRoleName(), "角色名不能为空");
        roleService.save(DozerUtil.map(roleVoResp, RoleDTO.class));
        return new ResultModel<>().success("新增成功");
    }

    /**
     * 角色删除操作
     * @return
     */
    @RequestMapping("deleteRole")
    @RequiresPermissions(ResourceConstant.ROLE_DELETE_BTN)
    public ResultModel<Object> deleteRole(Integer id) throws Exception{
        roleService.delete(id);
        return new ResultModel<>().success("删除成功");
    }

    /**
     * 资源关联
     * @return
     */
    @RequestMapping("getRoleResource")
    @RequiresPermissions(ResourceConstant.ROLE_RESOURCE_BTN)
    public ResultModel<Object> getRoleResource(Integer id) throws Exception{
        List<ZtreeDataDTO> ztreeDataDTOList = roleService.getRoleResource(id);
        return new ResultModel<>().success(ztreeDataDTOList);
    }

    /**
     * 保存资源
     * @return
     */
    @RequestMapping("save")
    public ResultModel<Object> save(RoleVoResp roleVoResp) throws Exception{
        roleService.saveRoleAndResource(DozerUtil.map(roleVoResp,RoleDTO.class));
        return new ResultModel<>().success("保存成功");
    }
}
