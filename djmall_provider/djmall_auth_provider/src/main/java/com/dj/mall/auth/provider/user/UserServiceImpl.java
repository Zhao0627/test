package com.dj.mall.auth.provider.user;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.aliyuncs.exceptions.ClientException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.mall.auth.api.email.MailService;
import com.dj.mall.auth.api.role.RoleService;
import com.dj.mall.auth.api.user.UserLoginTimeService;
import com.dj.mall.auth.api.user.UserRoleService;
import com.dj.mall.auth.api.user.UserService;
import com.dj.mall.auth.bo.user.UserBo;
import com.dj.mall.auth.dto.role.RoleDTO;
import com.dj.mall.auth.dto.user.UserDTO;
import com.dj.mall.auth.dto.user.UserLoginTimeDTO;
import com.dj.mall.auth.dto.user.UserRoleDTO;
import com.dj.mall.auth.entity.user.User;
import com.dj.mall.auth.entity.user.UserLoginTime;
import com.dj.mall.auth.mapper.user.UserMapper;
import com.dj.mall.model.base.BusinessException;
import com.dj.mall.model.util.DozerUtil;
import com.dj.mall.model.util.MessageVerifyUtils;
import com.dj.mall.model.util.PasswordSecurityUtil;
import com.dj.mall.model.util.SystemConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Wrapper;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 用户实现类
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    /**
     * email接口
     */
    @Autowired
    private MailService mailService;

    /**
     * 角色接口
     */
    @Autowired
    private RoleService roleService;

    /**
     * mapper
     */
    @Autowired
    private UserMapper userMapper;

    /**
     * 用户登录接口
     */
    @Autowired
    private UserLoginTimeService userLoginTimeService;

    /**
     * 用户登录接口
     */
    @Autowired
    private UserRoleService userRoleService;

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
        queryWrapper.eq("is_del",SystemConstant.IS_DEL_NO);
        queryWrapper.and(Wrapper -> Wrapper.eq("user_name",userDTO.getUserName()).or().eq("user_phone",userDTO.getUserName()).or().eq("user_email",userDTO.getUserName()));
        User user = super.getOne(queryWrapper);
        if (null == user){
            throw new BusinessException("用户名/手机号/邮箱错误");
        }
        UserRoleDTO userRoleDTO = userRoleService.getByUserId(user.getId());
        //判断输入密码了没有  （多个方法调用原因）
        if (userDTO.getUserPwd()!=null){
            if (user.getIsDel().equals(SystemConstant.IS_DEL_YES)){
                throw new BusinessException("用户未注册");
            }
            if(!userDTO.getUserPwd().equals(user.getUserPwd())){
                throw new BusinessException("密码错误");
            }
            //判断是否是商户
            if (userRoleDTO.getRoleId() == SystemConstant.ROLE_ID){
                if (user.getActivatedState().equals(SystemConstant.EMAIL_STATES_1)){
                    throw new BusinessException("邮箱未激活，请前往邮箱激活");
                }
            }
            UserLoginTimeDTO userLoginTime = new UserLoginTimeDTO();
            userLoginTime.setLoginTime(new Date());
            userLoginTime.setUserId(user.getId());
            userLoginTimeService.save(userLoginTime);
        }
        UserDTO userDTO1 = DozerUtil.map(user, UserDTO.class);
        if (userRoleDTO != null){
            userDTO1.setUserLevel(userRoleDTO.getRoleId());
        }

        return userDTO1;
    }

    /**
     * 注册去重
     * @param userDTO
     * @return
     * @throws BusinessException
     */
    @Override
    public UserDTO findUserNamePhoneEmail(UserDTO userDTO) throws BusinessException {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_del",SystemConstant.IS_DEL_NO);
        queryWrapper.and(Wrapper -> Wrapper.eq("user_name", userDTO.getUserName()).or().eq("user_phone",userDTO.getUserPhone()).or().eq("user_email",userDTO.getUserEmail()));
        UserDTO userDTO1 = DozerUtil.map(getOne(queryWrapper), UserDTO.class);
        if (userDTO1!=null){
            UserRoleDTO userRoleDTO = userRoleService.getByUserId(userDTO1.getUserId());
            if (null!=userRoleDTO){
                userDTO1.setUserLevel(userRoleDTO.getRoleId());
            }
        }
        return userDTO1;
    }

    /**
     * 注册
     *
     * @param userDTO
     */
    @Override
    public void insertUser(UserDTO userDTO) throws BusinessException,Exception {
        User user1 = DozerUtil.map(userDTO, User.class);
        String salt = PasswordSecurityUtil.generateSalt();
        user1.setSalt(salt);

        user1.setUserPwd(PasswordSecurityUtil.enCode32(PasswordSecurityUtil.enCode32(userDTO.getUserPwd()) + salt));
        user1.setSaveTime(new Date());
        user1.setIsDel(SystemConstant.IS_DEL_NO);
        save(user1);
        userRoleService.insert(user1.getId(),userDTO.getUserLevel());

        if (userDTO.getUserLevel()==SystemConstant.COMMERCIAL_ID){
            //邮件激活
            String to = user1.getUserEmail();
            String subject = "注册激活登录";
            String content = "'恭喜："+user1.getNickName()+"注册成功，"+"点击<a href='http://127.0.0.1:8081/admin/auth/user/toUpdateState?id=\n"+user1.getId()+"\n'>这里</a>激活";
            mailService.sendHtmlMail(to, subject, content);
        }
    }


    /**
     * 注册激活修改激活码
     *
     * @param ids
     * @throws BusinessException
     */
    @Override
    public void updateActivatedState(Integer[] ids) throws BusinessException {
        List<User> userList = new ArrayList<>();
        for (Integer id:ids) {
            User user = new User();
            user.setId(id);
            user.setActivatedState(SystemConstant.EMAIL_STATES_2);
            userList.add(user);
        }
        updateBatchById(userList);
    }


    /**
     * 查询所有用户
     *
     * @param userDTO
     * @return
     */
    @Override
    public List<UserDTO> findUserAll(UserDTO userDTO) throws BusinessException {
        UserBo userBo = DozerUtil.map(userDTO, UserBo.class);
        userBo.setIsDel(SystemConstant.IS_DEL_NO);
        return DozerUtil.mapList(userMapper.findUserAll(userBo),UserDTO.class);
    }

    /**
     * 重置密码
     *
     * @param ids 重置ids
     * @throws BusinessException
     */
    @Override
    public void resetPwd(Integer ids[],String emails[], String userName) throws Exception {
        List<User> userList = new ArrayList<>();
        for (Integer id: ids) {
            String pwd = PasswordSecurityUtil.generateRandom(6);
            User user = new User();
            user.setId(id);
            user.setUserPwd(PasswordSecurityUtil.enCode32(pwd));
            user.setResetPwd(pwd);
            userList.add(user);
        }
        updateBatchById(userList);
        for (String email: emails) {
            //邮件发送
            UserDTO userDTO = new UserDTO();
            userDTO.setUserEmail(email);
            UserDTO userDTO1 = findUserNamePhoneEmail(userDTO);
            String newDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
            String to = email;
            String subject = "重置密码";
            String content = "尊敬的"+userDTO1.getNickName()+"，<br>"+"您的密码已被管理员"+userName+"于"+newDate+"重置为<font color='red'>"+userDTO1.getResetPwd()+"</font>。<br>为了您的账户安全，请及时修改。"+
                    "<a href='http://127.0.0.1:8081/admin/auth/user/toLogin' style='color: red;text-decoration:none;'>点我去登录</a>";
            mailService.sendHtmlMail(to, subject, content);
        }

    }

    /**
     * 修改用户
     * @param userDTO
     */
    @Override
    public void updateUser(UserDTO userDTO) throws BusinessException {
        try {
            userDTO.setUserPwd(PasswordSecurityUtil.enCode32(PasswordSecurityUtil.enCode32(userDTO.getUserPwd())+userDTO.getSalt()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        updateById(DozerUtil.map(userDTO,User.class));
    }

    /**
     * 获取验证码以及发送验证码
     *
     * @param userDTO 获取来的手机号
     */
    @Override
    public String getPhoneCode(UserDTO userDTO) throws BusinessException {
        String newcode = MessageVerifyUtils.getNewcode();
        try {
            MessageVerifyUtils.sendSms(userDTO.getUserPhone(),newcode);
            return newcode;
        } catch (ClientException e) {
            e.printStackTrace();
            throw new BusinessException("验证码发送失败");
        }

    }

    /**
     * 通过手机号修改密码发送邮件
     *
     * @param userDTO
     */
    @Override
    public void updatePwdByPhone(UserDTO userDTO) throws BusinessException {
        UserDTO userNamePhoneEmail = findUserNamePhoneEmail(userDTO);
        if (userNamePhoneEmail==null){
            throw new BusinessException("手机号未注册");
        }
        User user = new User();
        try {
            System.out.println(userNamePhoneEmail.getSalt());
            String userPwd = PasswordSecurityUtil.enCode32(PasswordSecurityUtil.enCode32(userDTO.getUserPwd()) + userNamePhoneEmail.getSalt());
            user.setUserPwd(userPwd);
            user.setId(userNamePhoneEmail.getUserId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        updateById(user);
        String newDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
        String to = userNamePhoneEmail.getUserEmail();
        String subject = "修改密码";
        String content = "'您的帐户"+userNamePhoneEmail.getNickName()+"于"+newDate+"时进行密码修改成功。";
        mailService.sendHtmlMail(to, subject, content);

    }

    /**
     * 通过id查
     *
     * @param id
     * @return
     */
    @Override
    public UserDTO findUserById(Integer id) throws BusinessException {
        return DozerUtil.map(getById(id),UserDTO.class);
    }

    /**
     * 批量级联删除
     *
     * @param ids
     */
    @Override
    public void deleteUserByIds(Integer[] ids) throws BusinessException {
        userMapper.deleteUserByIds(ids);
    }

    /**
     * 批量级联修改
     *
     * @param ids
     */
    @Override
    public void updateUserIsDelByIds(Integer[] ids) throws BusinessException {
        userMapper.updateUserIsDelByIds(ids,SystemConstant.IS_DEL_YES);
    }

}
