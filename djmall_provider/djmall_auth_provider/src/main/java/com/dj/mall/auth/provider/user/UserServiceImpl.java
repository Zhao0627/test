package com.dj.mall.auth.provider.user;

import com.alibaba.dubbo.config.annotation.Service;
import com.aliyuncs.exceptions.ClientException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.mall.auth.api.email.MailService;
import com.dj.mall.auth.api.role.RoleService;
import com.dj.mall.auth.api.user.UserService;
import com.dj.mall.auth.dto.role.RoleDTO;
import com.dj.mall.auth.dto.user.UserDTO;
import com.dj.mall.auth.entity.user.User;
import com.dj.mall.auth.mapper.user.UserMapper;
import com.dj.mall.model.base.BusinessException;
import com.dj.mall.model.util.DozerUtil;
import com.dj.mall.model.util.MessageVerifyUtils;
import com.dj.mall.model.util.PasswordSecurityUtil;
import com.dj.mall.model.util.SystemConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

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

    @Autowired
    private MailService mailService;

    @Autowired
    private RoleService roleService;

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
/*            try {
                if (PasswordSecurityUtil.enCode32(PasswordSecurityUtil.enCode32(user.getResetPwd())+user.getSalt()).equals(userDTO.getUserPwd())){
                    throw new BusinessException("")
                }
            } catch (Exception e) {
                e.printStackTrace();
            }*/
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
            userDTO.setSaveTime(new Date());
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

        PasswordSecurityUtil.generateRandom(6);

        List<User> users = new ArrayList<>();//后台带的
        List<User> userss = new ArrayList<>();//真实new的
        for (User u: users) {
            User us = new User();
            us.setId(u.getId());
            us.setActivatedState(u.getActivatedState());
            us.setUserSex(u.getUserSex());
            userss.add(us);
        }
        updateBatchById(userss);
    }


    /**
     * 查询所有用户
     *
     * @param userDTO
     * @return
     */
    @Override
    public List<UserDTO> findUserAll(UserDTO userDTO) throws BusinessException {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(userDTO.getUserSex())){
            queryWrapper.eq("user_sex",userDTO.getUserSex());
        }
        if (!StringUtils.isEmpty(userDTO.getUserLevel())){
            queryWrapper.eq("user_level",userDTO.getUserLevel());
        }
        if (!StringUtils.isEmpty(userDTO.getActivatedState())){
            queryWrapper.eq("activated_state",userDTO.getActivatedState());
        }
        if (""!= userDTO.getUserName() && !StringUtils.isEmpty(userDTO.getUserName())){
            queryWrapper.like("user_name",userDTO.getUserName()).or().like("user_phone",userDTO.getUserName()).or().like("user_email",userDTO.getUserName());
        }
        List<User> userList = list(queryWrapper);
        List<UserDTO> userDTOS = DozerUtil.mapList(userList, UserDTO.class);
        for (int i = 0; i < userDTOS.size(); i++) {
            RoleDTO role = roleService.findRoleById(userDTOS.get(i).getUserLevel());
            userDTOS.get(i).setUserLevelShow(role.getRoleName());
        }
        return userDTOS;
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
            String content = "'尊敬的"+userDTO1.getNickName()+",您的密码已被管理员"+userName+"于"+newDate+"重置为"+userDTO1.getResetPwd()+"。为了您的账户安全，请及时修改。"+
                    "<a href='http://127.0.0.1:8081/admin/auth/user/toLogin'>点我去登录</a>";
            mailService.sendHtmlMail(to, subject, content);
        }

    }

    /**
     * 修改用户
     * @param userDTO
     */
    @Override
    public void updateUser(UserDTO userDTO) throws BusinessException {
        User user = getById(userDTO.getUserId());
        try {
            userDTO.setUserPwd(PasswordSecurityUtil.enCode32(PasswordSecurityUtil.enCode32(userDTO.getUserPwd())+user.getSalt()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        updateById(DozerUtil.map(userDTO,User.class));
    }

    /**
     * 获取验证码以及发送验证码
     *
     * @param userDTO
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
}
