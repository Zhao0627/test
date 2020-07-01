package com.dj.mall.auth.api.role;


import com.dj.mall.auth.dto.ZtreeDataDTO;
import com.dj.mall.auth.dto.resource.ResourceDTO;
import com.dj.mall.auth.dto.role.RoleDTO;
import com.dj.mall.auth.dto.role.RoleResourceDTO;
import com.dj.mall.model.base.BusinessException;

import java.util.List;

/**
 * 角色信息接口
 */
public interface RoleService {
    /**
     * 查询所有角色
     * @return
     * @throws BusinessException
     */
    List<RoleDTO> findRoleAll() throws BusinessException;;

    /**
     * 编辑角色
     * @param roleDTO
     * @throws BusinessException
     */
    void update(RoleDTO roleDTO) throws BusinessException;;

    /**
     * 新增角色
     * @param roleDTO
     * @throws BusinessException
     */
    void save(RoleDTO roleDTO) throws BusinessException;;

    /**
     * 删除角色
     * @param id
     */
    void delete(Integer id) throws BusinessException;;

    /**
     * 通过id查询role表数据
     * @param id
     * @return
     * @throws BusinessException
     */
    RoleDTO findRoleById(Integer id) throws BusinessException;;

    /**
     * 查询关联资源
     * @return
     */
    List<ZtreeDataDTO> getRoleResource(Integer id) throws BusinessException;;

    /**
     * 保存管连资源
     * @param roleDTO
     * @throws BusinessException
     */
    void saveRoleAndResource(RoleDTO roleDTO) throws Exception;

    /**
     * 通过roleId查询资源
     * @param roleId
     * @return
     */
    List<ResourceDTO> findResourceByRoleId(Integer roleId);
}
