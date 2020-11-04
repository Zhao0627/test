package com.dj.mall.admin.web.order.page;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.admin.config.ResourceConstant;
import com.dj.mall.auth.dto.user.UserDTO;
import com.dj.mall.order.api.order.OrderApi;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/order/")
public class OrderPageController {

    @Reference
    private OrderApi orderApi;

    @RequestMapping("toOrderShow")
    @RequiresPermissions(ResourceConstant.ORDER_MANAGER)
    public String toOrderShow(Model model,HttpSession session){
        UserDTO user = (UserDTO) session.getAttribute("user");
        model.addAttribute("user",user);
        return "order/order_show";
    }
}
