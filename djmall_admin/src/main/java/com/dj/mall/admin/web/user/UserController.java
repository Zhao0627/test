package com.dj.mall.admin.web.user;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.admin.vo.UserVoResp;
import com.dj.mall.auth.api.user.UserService;
import com.dj.mall.auth.dto.user.UserDTO;
import com.dj.mall.model.base.ResultModel;
import com.dj.mall.model.util.DozerUtil;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

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
    public ResultModel<Object> login(UserVoResp userVoResp, HttpSession session){
        Assert.hasLength(userVoResp.getUserName(), "用户名不能为空");
        Assert.hasLength(userVoResp.getUserPwd(), "密码不能为空");
        UserDTO userDto = userService.getUserByUserName(DozerUtil.map(userVoResp,UserDTO.class));
        session.setAttribute("user",DozerUtil.map(userDto,UserVoResp.class));
        return new ResultModel<>().success("登陆成功");
    }

}
