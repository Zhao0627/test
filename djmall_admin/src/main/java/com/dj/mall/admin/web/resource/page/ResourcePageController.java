package com.dj.mall.admin.web.resource.page;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.auth.api.resource.ResourceService;
import com.dj.mall.auth.dto.resource.ResourceDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/auth/resource/")
@Controller
public class ResourcePageController {

    @Reference
    private ResourceService resourceService;

    @RequestMapping("toShow")
    public String toShow(){
        return "resource/show";
    }

    @RequestMapping("toSaveResource")
    public String toSaveResource(Model model){
        List<ResourceDTO> all = resourceService.findAll();
        model.addAttribute("resource",all);
        return "resource/insert";
    }

    @RequestMapping("toUpdateResource")
    public String toUpdateResource(Model model){
        List<ResourceDTO> all = resourceService.findAll();
        model.addAttribute("resourceList",all);
        return "resource/insert";
    }

}