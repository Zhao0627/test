package com.dj.mall.platform.web.comment.page;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.cmpt.api.RedisService.RedisService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/comment/")
public class CommentPageController {

    @Reference
    private RedisService redisService;

    @RequestMapping("toAssessProduct")
    public String toAssessProduct(Integer productId, Model model){
        model.addAttribute("productId",productId);
        return "comment/to_assess";
    }


}
