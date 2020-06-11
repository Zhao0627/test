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

    /**
     * 级联删除
     * @param ids
     */
    void deleteUserByIds(@Param("ids") Integer[] ids);

    /**
     * 级联批量修改
     * @param ids
     */
    void updateUserIsDelByIds(@Param("ids")Integer []ids,@Param("isDel") Integer isDel);
}
