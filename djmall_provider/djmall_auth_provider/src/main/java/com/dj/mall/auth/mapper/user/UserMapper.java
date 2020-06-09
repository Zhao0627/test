package com.dj.mall.auth.mapper.user;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dj.mall.auth.bo.user.UserBo;
import com.dj.mall.auth.dto.user.UserDTO;
import com.dj.mall.auth.entity.user.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户Mapper
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 查询所有用户
     * @param userBo
     * @return
     */
    List<UserBo> findUserAll(@Param("userBo") UserBo userBo);

}
