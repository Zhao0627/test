package com.dj.mall.auth.provider.user;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.mall.auth.api.user.UserLoginTimeService;
import com.dj.mall.auth.dto.user.UserLoginTimeDTO;
import com.dj.mall.auth.entity.user.UserLoginTime;
import com.dj.mall.auth.mapper.user.UserLoginTimeMapper;
import com.dj.mall.model.util.DozerUtil;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户登录实现类
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserLoginTimeServiceImpl extends ServiceImpl<UserLoginTimeMapper, UserLoginTime> implements UserLoginTimeService {

    /**
     * 新增用户登录记录
     * @param userLoginTimeDTO
     */
    @Override
    public void save(UserLoginTimeDTO userLoginTimeDTO) {
        save(DozerUtil.map(userLoginTimeDTO,UserLoginTime.class));
    }
}
