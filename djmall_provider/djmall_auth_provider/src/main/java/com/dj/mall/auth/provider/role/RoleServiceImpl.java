package com.dj.mall.auth.provider.role;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.mall.auth.api.role.RoleService;
import com.dj.mall.auth.dto.role.RoleDTO;
import com.dj.mall.auth.entity.role.Role;
import com.dj.mall.auth.mapper.role.RoleMapper;
import com.dj.mall.model.base.BusinessException;
import com.dj.mall.model.util.DozerUtil;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

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
}
