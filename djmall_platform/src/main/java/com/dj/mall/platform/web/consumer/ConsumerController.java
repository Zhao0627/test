package com.dj.mall.platform.web.consumer;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.auth.api.user.UserService;
import com.dj.mall.auth.dto.user.UserDTO;
import com.dj.mall.cmpt.api.RedisService.RedisService;
import com.dj.mall.model.base.ResultModel;
import com.dj.mall.model.util.DozerUtil;
import com.dj.mall.model.util.PasswordSecurityUtil;
import com.dj.mall.model.util.SystemConstant;
import com.dj.mall.platform.vo.user.UserTokenVoResp;
import com.dj.mall.platform.vo.user.UserVoReq;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/consumer/")
public class ConsumerController {

    @Reference
    private UserService userService;

    @Reference
    private RedisService redisService;

    @RequestMapping("login")
    public ResultModel login(UserVoReq userVoReq){
        UserDTO userDTO = userService.getUserByUserName(DozerUtil.map(userVoReq, UserDTO.class));
        if (!userDTO.getUserLevel().equals(SystemConstant.BUYER_ID)){
            return new ResultModel().error("非买家不得登录");
        }
        //生成token
        String token = UUID.randomUUID().toString().replace("-","");
        redisService.set(SystemConstant.TOKEN+token,userDTO,21*24*60*60);
        //数据放入UserTokenVo
        UserTokenVoResp userTokenVoResp = new UserTokenVoResp();
        userTokenVoResp.setToken(token);
        userTokenVoResp.setUserName(userDTO.getUserName());
        userTokenVoResp.setNickName(userDTO.getNickName());
        userTokenVoResp.setUserId(userDTO.getUserId());
        return new ResultModel().success(userTokenVoResp);
    }

    @RequestMapping("getPhoneCode")
    public ResultModel getPhoneCode(UserVoReq userVoReq){
        return new ResultModel().success(userService.getPhoneCode(DozerUtil.map(userVoReq,UserDTO.class)));
    }

    @RequestMapping("getUserSalt")
    public ResultModel getUserSalt(UserVoReq userVoReq){
        return new ResultModel().success(userService.findUserNamePhoneEmail(DozerUtil.map(userVoReq,UserDTO.class)));
    }

    @RequestMapping("phoneLogin")
    public ResultModel phoneLogin(UserVoReq userVoReq){
        UserDTO userDTO = userService.findUserNamePhoneEmail(DozerUtil.map(userVoReq, UserDTO.class));
        if (!userDTO.getUserLevel().equals(SystemConstant.BUYER_ID)){
            return new ResultModel().error("非买家不得登录");
        }
        //生成token
        String token = UUID.randomUUID().toString().replace("-","");
        redisService.set(SystemConstant.TOKEN+token,userDTO,21*24*60*60);
        //数据放入UserTokenVo
        UserTokenVoResp userTokenVoResp = new UserTokenVoResp();
        userTokenVoResp.setToken(token);
        userTokenVoResp.setUserName(userDTO.getUserName());
        userTokenVoResp.setNickName(userDTO.getNickName());
        userTokenVoResp.setUserId(userDTO.getUserId());
        return new ResultModel().success(userTokenVoResp);
    }

    @RequestMapping("updateUser")
    public ResultModel updateUser(String TOKEN,UserVoReq userVoReq){
        if (userVoReq.getNickName()==null){
            userVoReq.setNickName("DJ"+ PasswordSecurityUtil.generateRandom(6));
        }
        userService.updateUser(DozerUtil.map(userVoReq,UserDTO.class));
        return new ResultModel().success("修改成功");
    }

}
