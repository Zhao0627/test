package com.dj.mall.platform.web.order.page;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.auth.dto.user.UserDTO;
import com.dj.mall.cmpt.api.RedisService.RedisService;
import com.dj.mall.model.util.SystemConstant;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order/receiver/")
public class OrderReceiverPageController {

    @Reference
    private RedisService redisService;

    @RequestMapping("toAddReceiverSite")
    public String toAddReceiverSite(String TOKEN, Model model){
        UserDTO user = redisService.get(SystemConstant.TOKEN + TOKEN);
        model.addAttribute("user",user);
        return "receiver/site_show";
    }

    @RequestMapping("toAddSite")
    public String addSite(String TOKEN, Model model){
        return "receiver/add_site";
    }

}
