package com.dj.mall.auth.api.role;


import com.dj.mall.auth.dto.role.RoleDTO;
import com.dj.mall.model.base.BusinessException;

import java.util.List;

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

    RoleDTO findRoleById(Integer id) throws BusinessException;;

}
