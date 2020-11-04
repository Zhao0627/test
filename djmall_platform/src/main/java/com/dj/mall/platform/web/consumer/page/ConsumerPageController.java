package com.dj.mall.platform.web.consumer.page;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.auth.dto.user.UserDTO;
import com.dj.mall.cmpt.api.RedisService.RedisService;
import com.dj.mall.dict.api.basedata.BaseDataService;
import com.dj.mall.dict.dto.basedata.BaseDataDTO;
import com.dj.mall.model.util.SystemConstant;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/consumer/")
public class ConsumerPageController {

    @Reference
    private RedisService redisService;

    @Reference
    private BaseDataService baseDataService;

    @RequestMapping("toLogin")
    public String toLogin(){
        return "consumer/login";
    }

    @RequestMapping("toPersonal")
    public String toPersonal(String TOKEN, Model model) {
        UserDTO user = redisService.get(SystemConstant.TOKEN + TOKEN);
        List<Map<String,String>> sex = redisService.getHashValues(SystemConstant.SEX_P_CODE);
        model.addAttribute("sex",sex);
        model.addAttribute("user",user);
        return "consumer/personal";
    }

}
