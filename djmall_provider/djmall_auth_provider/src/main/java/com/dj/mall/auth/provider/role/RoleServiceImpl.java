package com.dj.mall.auth.provider.role;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.mall.auth.api.resource.ResourceService;
import com.dj.mall.auth.api.role.RoleService;
import com.dj.mall.auth.dto.ZtreeDataDTO;
import com.dj.mall.auth.dto.resource.ResourceDTO;
import com.dj.mall.auth.dto.role.RoleDTO;
import com.dj.mall.auth.entity.resource.Resource;
import com.dj.mall.auth.entity.role.Role;
import com.dj.mall.auth.entity.role.RoleResource;
import com.dj.mall.auth.mapper.role.RoleMapper;
import com.dj.mall.auth.service.RoleResourceService;
import com.dj.mall.model.base.BusinessException;
import com.dj.mall.model.util.DozerUtil;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 角色实现类
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    /**
     * 资源接口
     */
    @Autowired
    private ResourceService resourceService;

    /**
     * 资源角色接口
     */
    @Autowired
    private RoleResourceService roleResourceService;

    /**
     * 查询所有角色
     *
     * @return
     * @throws BusinessException
     */
    @Override
    public List<RoleDTO> findRoleAll() throws BusinessException {
        List<Role> list = super.list();
        List<RoleDTO> roleDTOS = DozerUtil.mapList(list, RoleDTO.class);
        return roleDTOS;
    }

    /**
     * 编辑角色
     *
     * @param roleDTO
     * @throws BusinessException
     */
    @Override
    public void update(RoleDTO roleDTO) throws BusinessException {
        updateById(DozerUtil.map(roleDTO,Role.class));
    }

    /**
     * 新增角色
     *
     * @param roleDTO
     * @throws BusinessException
     */
    @Override
    public void save(RoleDTO roleDTO) throws BusinessException {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_name",roleDTO.getRoleName());
        Role role = getOne(queryWrapper);
        if (role != null){
            throw new BusinessException("新增重复");
        }
        save(DozerUtil.map(roleDTO, Role.class));
    }

    /**
     * 删除角色
     *
     * @param id
     */
    @Override
    public void delete(Integer id) throws BusinessException {
        super.removeById(id);
    }

    /**
     * 通过id查找信息
     * @param id 角色id
     * @return
     * @throws BusinessException
     */
    @Override
    public RoleDTO findRoleById(Integer id) throws BusinessException {
        Role role = getById(id);
        return DozerUtil.map(role,RoleDTO.class);
    }

    /**
     * 查询关联资源
     *
     * @return
     */
    @Override
    public List<ZtreeDataDTO> getRoleResource(Integer id) throws BusinessException {
        //查询所有资源
        List<ResourceDTO> all = resourceService.findAll();
        //查询所有已关联的资源
        QueryWrapper<RoleResource> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id",id);
        List<RoleResource> roleResourceList = roleResourceService.list(queryWrapper);
        //放进树里面
        List<ZtreeDataDTO> ztreeList = new ArrayList<>();
        for (ResourceDTO resourceDTO: all) {
            ZtreeDataDTO ztreeData = new ZtreeDataDTO().builder().
                    id(resourceDTO.getResourceId()).
                    name(resourceDTO.getResourceName()).
                    pId(resourceDTO.getPId()).build();
            //判断是否勾选
            for (RoleResource roleResource: roleResourceList) {
                //如果勾选则给他true
                if (resourceDTO.getResourceId() == roleResource.getResourceId()){
                    ztreeData.setChecked(true);
                    break;
                }
            }
            ztreeList.add(ztreeData);
        }
        return ztreeList;
    }

    /**
     * 保存管连资源
     *
     * @param roleDTO
     * @throws BusinessException
     */
    @Override
    public void saveRoleAndResource(RoleDTO roleDTO) throws Exception {
        //先查出来所有role_id是
        QueryWrapper<RoleResource> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id",roleDTO.getRoleId());
        List<RoleResource> roleResourceList = roleResourceService.list(queryWrapper);
        //放一个容器 resourceIds
        List<Integer> orderResourceIds = new ArrayList<>();
        for (RoleResource roleResource : roleResourceList) {
            orderResourceIds.add(roleResource.getResourceId());
        }

        /*当前勾选了的 resourceIds*/
        Set newSet = new HashSet(roleDTO.getResourceIds());

        /* 未修改数据库时勾选的ids*/
        Set orderSet = new HashSet(orderResourceIds);

        /*去掉重复新增的数据  需要增*/
        newSet .removeAll(orderSet);//set1 : a

        /*去掉重复多余的数据  需要删*/
        Set set = new HashSet(roleDTO.getResourceIds());
        orderSet .removeAll(set);//set2 : d
        
        /*需要增的数据*/
        List<RoleResource> roleResourceList1 = new ArrayList<>();
        Object[] objects = newSet.toArray(new Object[0]);
        for (int i=0; i< objects.length; i++) {
            RoleResource roleResource = new RoleResource();
            roleResource.setRoleId(roleDTO.getRoleId());
            roleResource.setResourceId(Integer.valueOf(objects[i].toString()));
            roleResourceList1.add(roleResource);
        }
        //判断是否有多余 多余的删掉
        if (orderSet.size()>0){
            List<Integer> resourceIds = new ArrayList<>(orderSet);
            /*roleResourceService.delete(resourceIds,roleDTO.getRoleId());*/

            QueryWrapper<RoleResource> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq("role_id",roleDTO.getRoleId());
            queryWrapper1.in("resource_id",resourceIds);
            roleResourceService.remove(queryWrapper1);
        }
        //判断是否有新增
        if (newSet.size()>0){
            roleResourceService.saveBatch(roleResourceList1);
        }


/*        //存放多个roleResource的list
        List<RoleResource> roleResourceList = new ArrayList<>();
        //把dto的ids拿出来遍历
        for (Integer resourceIds: roleDTO.getResourceIds()) {
            //把id的值放到一个roleResource的容器中
            RoleResource roleResource = new RoleResource();
            roleResource.setResourceId(resourceIds);
            roleResource.setRoleId(roleDTO.getRoleId());
            //存放到到list中
            roleResourceList.add(roleResource);
        }

        //先删后加
        QueryWrapper<RoleResource> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id",roleDTO.getRoleId());
        roleResourceService.remove(queryWrapper);

        //批量新增
        roleResourceService.saveBatch(roleResourceList);*/
    }


}
