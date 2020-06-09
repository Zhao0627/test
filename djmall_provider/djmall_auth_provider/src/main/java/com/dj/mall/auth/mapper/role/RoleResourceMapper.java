package com.dj.mall.auth.mapper.role;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dj.mall.auth.entity.role.RoleResource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

/**
 * 角色资源关联mapper
 */
public interface RoleResourceMapper extends BaseMapper<RoleResource> {

    /**
     * 批量删除
     * @param resourceIds
     * @param roleId
     * @throws Exception
     */
    void deletBatch(@Param("resourceIds") List<Integer> resourceIds, @Param("roleId") Integer roleId) throws Exception;

}
