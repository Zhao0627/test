package com.dj.mall.auth.api.resource;

import com.dj.mall.auth.dto.resource.ResourceDTO;
import com.dj.mall.model.base.BusinessException;

import java.util.List;

/**
 * 资源管理接口
 */
public interface ResourceService {

    /**
     * 查找所有资源
     * @return
     * @throws BusinessException
     */
    List<ResourceDTO> findAll() throws BusinessException;

    /**
     * 新增资源
     * @param resourceDTO 资源DTO类
     * @throws BusinessException
     */
    void save(ResourceDTO resourceDTO) throws BusinessException;

    /**
     * 修改资源
     * @param resourceDTO
     * @throws BusinessException
     */
    void update(ResourceDTO resourceDTO)throws BusinessException;

    /**
     * 删除资源
     * @param ids
     * @throws BusinessException
     */
    void delete(List<Integer> ids) throws BusinessException;

    /**
     * 通过父id查询
     * @param id
     * @return
     * @throws BusinessException
     */
    List<ResourceDTO> findByParentId(Integer id)throws BusinessException;

    /**
     * 通过id查找
     * @param resourceId
     * @return
     * @throws BusinessException
     */
    ResourceDTO findById(Integer resourceId) throws BusinessException;

    /**
     * 通过用户查询
     * @param userId
     * @return
     * @throws BusinessException
     */
    List<ResourceDTO> getResourceByUserId(Integer userId) throws BusinessException;
}
