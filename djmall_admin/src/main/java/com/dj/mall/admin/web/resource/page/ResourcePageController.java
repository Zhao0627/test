package com.dj.mall.admin.web.resource.page;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.admin.vo.ResourceVoResp;
import com.dj.mall.auth.api.resource.ResourceService;
import com.dj.mall.auth.dto.resource.ResourceDTO;
import com.dj.mall.model.util.DozerUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 资源跳转处理类
 */
@RequestMapping("/auth/resource/")
@Controller
public class ResourcePageController {

    @Reference
    private ResourceService resourceService;

    /**
     * 去展示
     * @return
     */
    @RequestMapping("toShow")
    public String toShow(){
        return "resource/show";
    }

    /**
     * 去新增
     * @param model
     * @return
     */
    @RequestMapping("toSaveResource")
    public String toSaveResource(Model model, Integer resourceId){
        ResourceDTO resourceDTO = resourceService.findById(resourceId);
        model.addAttribute("resourceName",resourceDTO.getResourceName());
        model.addAttribute("resourceId",resourceId);
        return "resource/insert";
    }

    /**
     * 去修改
     * @param model
     * @param resourceId
     * @return
     */
    @RequestMapping("toUpdateResource")
    public String toUpdateResource(Model model, Integer resourceId){
        List<ResourceDTO> all = resourceService.findAll();
        model.addAttribute("resourceList",all);
        model.addAttribute("resource",DozerUtil.map(resourceService.findById(resourceId), ResourceVoResp.class));
        return "resource/update";
    }

}
