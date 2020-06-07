package com.dj.mall.auth.provider.user;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.mall.auth.api.email.MailService;
import com.dj.mall.auth.api.user.UserService;
import com.dj.mall.auth.dto.user.UserDTO;
import com.dj.mall.auth.entity.user.User;
import com.dj.mall.auth.mapper.user.UserMapper;
import com.dj.mall.model.base.BusinessException;
import com.dj.mall.model.util.DozerUtil;
import com.dj.mall.model.util.PasswordSecurityUtil;
import com.dj.mall.model.util.SystemConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 用户实现类
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private MailService mailService;

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
        if (userDTO.getUserPwd()!=null){
            if(!userDTO.getUserPwd().equals(user.getUserPwd())){
                throw new BusinessException("密码错误");
            }
            if (user.getUserLevel() == SystemConstant.EMAIL_STATES_2){
                if (user.getActivatedState().equals(SystemConstant.EMAIL_STATES_1)){
                    throw new BusinessException("邮箱未激活，请前往邮箱激活");
                }
            }
        }
        return DozerUtil.map(user,UserDTO.class);
    }

    /**
     * 注册去重
     *
     * @param userDTO
     * @return
     * @throws BusinessException
     */
    @Override
    public UserDTO findUserNamePhoneEmail(UserDTO userDTO) throws BusinessException {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", userDTO.getUserName()).or().eq("user_phone",userDTO.getUserPhone()).or().eq("user_email",userDTO.getUserEmail());
        return DozerUtil.map(getOne(queryWrapper), UserDTO.class);
    }

    /**
     * 注册
     *
     * @param userDTO
     */
    @Override
    public void insertUser(UserDTO userDTO) throws BusinessException {
        try {
            String salt = PasswordSecurityUtil.generateSalt();
            userDTO.setSalt(salt);
            userDTO.setUserPwd(PasswordSecurityUtil.enCode32(PasswordSecurityUtil.enCode32(userDTO.getUserPwd()) + userDTO.getSalt()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        save(DozerUtil.map(userDTO,User.class));
        if (userDTO.getUserLevel()==SystemConstant.COMMERCIAL_ID){
            //通过邮箱查询
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_email", userDTO.getUserEmail());
            User user = getOne(queryWrapper);
            //邮件激活
            String to = user.getUserEmail();
            String subject = "注册激活登录";
            String content = "'恭喜："+user.getNickName()+"注册成功，"+"点击<a href='http://127.0.0.1:8081/admin/auth/user/toUpdateState?id=\n"+user.getId()+"\n'>这里</a>激活";
            mailService.sendHtmlMail(to, subject, content);
        }
    }


    /**
     * 注册激活修改激活码
     *
     * @param id
     * @throws BusinessException
     */
    @Override
    public void updateActivatedState(Integer id) throws BusinessException {
        User user = new User();
        user.setId(id);
        user.setActivatedState(SystemConstant.EMAIL_STATES_2);
        updateById(user);
    }


    /**
     * 查询有用户
     *
     * @param userDTO
     * @return
     */
    @Override
    public List<UserDTO> findUserAll(UserDTO userDTO) throws BusinessException {
        User user = DozerUtil.map(userDTO, User.class);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (user.getUserName()!=null){
            queryWrapper.like("user_name",user.getUserName()).or().like("user_phone",user.getUserName()).or().like("user_email",user.getUserName());
        }
        if (!StringUtils.isEmpty(user.getUserSex())){
            queryWrapper.eq("user_sex",user.getUserSex());
        }
        if (!StringUtils.isEmpty(user.getUserLevel())){
            queryWrapper.eq("user_level",user.getUserLevel());
        }
        return DozerUtil.mapList(list(queryWrapper),UserDTO.class);
    }

}
