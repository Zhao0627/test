package com.dj.mall.auth.mapper.resource;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dj.mall.auth.dto.resource.ResourceDTO;
import com.dj.mall.auth.entity.resource.Resource;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * 资源mapper接口
 */
public interface ResourceMapper extends BaseMapper<Resource> {

    List<ResourceDTO> getResourceByUserId(Integer userId) throws DataAccessException;

}

