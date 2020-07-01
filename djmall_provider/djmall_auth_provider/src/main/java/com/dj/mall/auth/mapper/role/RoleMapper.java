package com.dj.mall.auth.mapper.role;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dj.mall.auth.entity.resource.Resource;
import com.dj.mall.auth.entity.role.Role;
import com.dj.mall.auth.entity.role.RoleResource;
import com.dj.mall.model.base.BusinessException;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * 角色资源接口
 */
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 通过roleId查询资源
     *
     * @param roleId
     * @return
     */
    List<Resource> findResourceByRoleId(Integer roleId) throws DataAccessException;
}
