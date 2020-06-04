package com.dj.mall.auth.provider.user;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.mall.auth.api.user.UserService;
import com.dj.mall.auth.dto.user.UserDTO;
import com.dj.mall.auth.entity.user.User;
import com.dj.mall.auth.mapper.user.UserMapper;
import com.dj.mall.model.base.BusinessException;
import com.dj.mall.model.util.DozerUtil;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    /**
     * 根据用户名获取信息（登录）
     *
     * @param userDTO 用户传递信息（用户名、手机号、邮箱和密码）
     * @return
     * @throws BusinessException
     */
    @Override
    public UserDTO getUserByUserName(UserDTO userDTO) throws BusinessException {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name",userDTO.getUserName()).or().eq("user_phone",userDTO.getUserName()).or().eq("user_email",userDTO.getUserName());
        User user = super.getOne(queryWrapper);
        if (null == user){
            throw new BusinessException("用户名/手机号/邮箱错误");
        }
        if (!user.getUserPwd().equals(userDTO.getUserPwd())) {
            throw new BusinessException("密码错误");
        }
        return DozerUtil.map(user,UserDTO.class);
    }
}
