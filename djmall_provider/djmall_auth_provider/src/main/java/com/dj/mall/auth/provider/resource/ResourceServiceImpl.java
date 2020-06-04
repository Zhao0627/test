package com.dj.mall.auth.provider.resource;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.mall.auth.api.resource.ResourceService;
import com.dj.mall.auth.dto.resource.ResourceDTO;
import com.dj.mall.auth.entity.resource.Resource;
import com.dj.mall.auth.mapper.resource.ResourceMapper;
import com.dj.mall.model.base.BusinessException;
import com.dj.mall.model.util.DozerUtil;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 资源管理实现类
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements ResourceService {

    /**
     * 查找所有资源
     * @return
     * @throws BusinessException
     */
    @Override
    public List<ResourceDTO> findAll() throws BusinessException {
        return DozerUtil.mapList(super.list(),ResourceDTO.class);
    }


    /**
     * 新增资源
     *
     * @param resourceDTO 资源DTO类
     * @throws BusinessException
     */
    @Override
    public void save(ResourceDTO resourceDTO) throws BusinessException {
        QueryWrapper<Resource> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("p_id",resourceDTO.getPId()).or()
                .eq("resource_name",resourceDTO.getResourceName()).or()
                .eq("url",resourceDTO.getUrl())
        ;
        if (getOne(queryWrapper) != null){
            throw new BusinessException("资源重复");
        }
        save(DozerUtil.map(resourceDTO, Resource.class));
    }

    /**
     * 修改资源
     *
     * @param resourceDTO
     * @throws BusinessException
     */
    @Override
    public void update(ResourceDTO resourceDTO) throws BusinessException {
        updateById(DozerUtil.map(resourceDTO, Resource.class));
    }

    /**
     * 删除资源
     *
     * @param ids
     * @throws BusinessException
     */
    @Override
    public void delete(List<Integer> ids) throws BusinessException {
        removeByIds(ids);
    }

    /**
     * 通过父级id查询用户
     * @param id
     * @return
     * @throws BusinessException
     */
    @Override
    public List<ResourceDTO> findByParentId(Integer id) throws BusinessException {
        QueryWrapper<Resource> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("p_id",id);
        return DozerUtil.mapList(super.list(queryWrapper), ResourceDTO.class);
    }

    /**
     * 通过id查询用户
     * @param resourceId
     * @return
     * @throws BusinessException
     */
    @Override
    public ResourceDTO findById(Integer resourceId) throws BusinessException {
        return DozerUtil.map(this.getById(resourceId),ResourceDTO.class);
    }

}
