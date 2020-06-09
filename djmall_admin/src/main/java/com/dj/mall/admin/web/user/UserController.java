package com.dj.mall.admin.web.user;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.admin.vo.UserVoReq;
import com.dj.mall.admin.vo.UserVoResp;
import com.dj.mall.auth.api.user.UserService;
import com.dj.mall.auth.dto.resource.ResourceDTO;
import com.dj.mall.auth.dto.user.UserDTO;
import com.dj.mall.model.base.ResultModel;
import com.dj.mall.model.util.DozerUtil;
import com.dj.mall.model.util.PasswordSecurityUtil;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户处理类
 */
@RequestMapping("/auth/user/")
@RestController
public class UserController {

    @Reference
    private UserService userService;

    /**
     * 用户登录
     * @param userVoResp
     * @param session
     * @return
     */
    @RequestMapping("login")
    public ResultModel<Object> login(UserVoResp userVoResp, HttpSession session) throws Exception{
        Assert.hasLength(userVoResp.getUserName(), "用户名不能为空");
        Assert.hasLength(userVoResp.getUserPwd(), "密码不能为空");
        UserDTO userDto = userService.getUserByUserName(DozerUtil.map(userVoResp,UserDTO.class));
        session.setAttribute("user",DozerUtil.map(userDto,UserVoResp.class));
        return new ResultModel<>().success("登陆成功");
    }

    /**
     * 去重
     * @param userVoResp
     * @param
     * @return
     */
    @RequestMapping("findUserNamePhoneEmail")
    public boolean findUserNamePhoneEmail(UserVoResp userVoResp) throws Exception{
        UserDTO userDto = userService.findUserNamePhoneEmail(DozerUtil.map(userVoResp,UserDTO.class));
        if (userDto == null){
            return true;
        }
        return false;
    }

    /**
     * 用户注册
     * @param userVoResp
     * @param
     * @return
     */
    @RequestMapping("insert")
    public ResultModel<Object> insert(UserVoResp userVoResp) throws Exception{
        Assert.hasText(userVoResp.getUserName(),"用户名不能为空");
        Assert.hasText(userVoResp.getUserPwd(), "密码不能为空");
        Assert.hasText(userVoResp.getNickName(), "昵称不能为空");
        Assert.hasText(userVoResp.getUserPhone(), "手机不能为空");
        Assert.hasText(userVoResp.getUserEmail(), "邮箱不能为空");
        UserDTO userDTO = DozerUtil.map(userVoResp, UserDTO.class);
        userService.insertUser(userDTO);
        return new ResultModel<>().success("注册成功");
    }

    /**
     * 用户管理
     * @param userVoResp
     * @param
     * @return
     */
    @RequestMapping("show")
    public Map<String,Object> show(UserVoResp userVoResp) throws Exception{
        UserDTO userDTO = DozerUtil.map(userVoResp, UserDTO.class);
        Map<String,Object> map = new HashMap<>();
        map.put("data",DozerUtil.mapList(userService.findUserAll(userDTO),UserVoResp.class));
        map.put("code",0);
        return map;
    }

    /**
     * 获取盐
     * @param userVoResp
     * @return
     * @throws Exception
     */
    @RequestMapping("getUserSalt")
    public ResultModel<Object> getUserSalt(UserVoResp userVoResp) throws Exception{
        return new ResultModel<>().success(DozerUtil.map(userService.getUserByUserName(DozerUtil.map(userVoResp,UserDTO.class)),UserVoResp.class));
    }

    /**
     * 修改用户
     * @param userVoResp
     * @return
     * @throws Exception
     */
    @RequestMapping("updateUser")
    public ResultModel<Object> updateUser(UserVoResp userVoResp) throws Exception{
        userService.updateUser(DozerUtil.map(userVoResp,UserDTO.class));
        return new ResultModel<Object>().success("修改成功");
    }

    /**
     * 重置密码
     * @param ids
     * @param emails
     * @param session
     * @return
     * @throws Exception
     */
    @RequestMapping("resetPwd")
    public ResultModel resetPwd(Integer ids[],
                                String emails[],
                                HttpSession session) throws Exception {
        UserVoResp user = (UserVoResp) session.getAttribute("user");
        userService.resetPwd(ids, emails, user.getUserName());
        return new ResultModel().success("重置成功");
    }

    /**
     * 修改密码
     * @param userVoReq
     * @return
     * @throws Exception
     */
/*
    @RequestMapping("updatePwd")
    public ResultModel updatePwd(UserVoReq userVoReq) throws Exception {
        userService.updateUser(DozerUtil.map(userVoReq,UserDTO.class));
        return new ResultModel().success("修改密码成功");
    }
*/

    /**
     * 根据手机号修改密码
     * @param userVoReq
     * @return
     * @throws Exception
     */
    @RequestMapping("updatePwdByPhone")
    public ResultModel updatePwdByPhone(UserVoReq userVoReq) throws Exception {
        userService.updatePwdByPhone(DozerUtil.map(userVoReq,UserDTO.class));
        return new ResultModel().success("修改密码成功");
    }

    /**
     * 发送短信验证码
     * @param userVoReq
     * @return
     * @throws Exception
     */
    @RequestMapping("getPhoneCode")
    public ResultModel getPhoneCode(UserVoReq userVoReq) throws Exception {
        String phoneCode = userService.getPhoneCode(DozerUtil.map(userVoReq, UserDTO.class));
        return new ResultModel().success(phoneCode);
    }

    /**
     * 修改状态
     * @param ids
     * @return
     * @throws Exception
     */
    @RequestMapping("updateState")
    public ResultModel updateState(Integer ids[]) throws Exception {
        userService.updateActivatedState(ids);
        return new ResultModel().success("修改成功");
    }

}
