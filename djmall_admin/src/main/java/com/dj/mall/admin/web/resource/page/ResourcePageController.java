package com.dj.mall.admin.web.resource.page;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.admin.vo.ResourceVoResp;
import com.dj.mall.auth.api.resource.ResourceService;
import com.dj.mall.auth.dto.resource.ResourceDTO;
import com.dj.mall.model.util.DozerUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
    @RequiresPermissions("RESOURCE_MANAGER")
    public String toShow(){
        return "resource/show";
    }

    /**
     * 去新增
     * @param model
     * @return
     */
    @RequestMapping("toSaveResource")
    @RequiresPermissions("RESOURCE_SAVE_BTN")
    public String toSaveResource(Model model, Integer resourceId){
        ResourceVoResp resourceVoResp = new ResourceVoResp();
        if (resourceId!=0){
            ResourceDTO resourceDTO = resourceService.findById(resourceId);
            resourceVoResp.setResourceName(resourceDTO.getResourceName());
            resourceVoResp.setResourceId(resourceId);
        }
        if (resourceId==0){
            resourceVoResp.setResourceName("暂无上级");
            resourceVoResp.setResourceId(resourceId);
        }
        model.addAttribute("resource",resourceVoResp);
        return "resource/insert";
    }

    /**
     * 去修改
     * @param model
     * @param resourceId
     * @return
     */
    @RequestMapping("toUpdateResource")
    @RequiresPermissions("RESOURCE_UPDATE_BTN")
    public String toUpdateResource(Model model, Integer resourceId){
        List<ResourceDTO> all = resourceService.findAll();
        model.addAttribute("resourceList",all);
        model.addAttribute("resource",DozerUtil.map(resourceService.findById(resourceId), ResourceVoResp.class));
        return "resource/update";
    }

}
