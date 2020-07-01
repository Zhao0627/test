package com.dj.mall.admin.web.attr.page;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.dict.api.attr.AttrService;
import com.dj.mall.dict.dto.attr.AttrDTO;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("dict/attr")
public class AttrPageController {

    @Reference
    private AttrService attrService;

    /**
     * 去商品属性展示
     * @return
     */
    @RequestMapping("/toShow")
    @RequiresPermissions("ATTR_MANAGER")
    public String toShow(){
        return "attr/attr_show";
    }

    /**
     * 去商品属性值
     * @param attrId
     * @param model
     * @return
     */
    @RequestMapping("/toAttrValue")
    @RequiresPermissions("ATTR_VALUE_BUTTON")
    public String toAttrValue(Integer attrId, Model model){
        AttrDTO attr = attrService.findById(attrId);
        model.addAttribute("attr",attr);
        return "attr/attr_value";
    }

}
