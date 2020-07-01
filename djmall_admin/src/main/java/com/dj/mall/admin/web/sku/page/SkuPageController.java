package com.dj.mall.admin.web.sku.page;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.dict.api.sku.SkuService;
import com.dj.mall.dict.dto.sku.SkuDTO;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/dict/sku")
public class SkuPageController {

    @Reference
    private SkuService skuService;

    /**
     * 去sku属性展示
     * @return
     */
    @RequestMapping("/toShow")
    @RequiresPermissions("SKU_MANAGER")
    public String toShow(){
        return "attr/sku_show";
    }

    /**
     * 去sku属性展示
     * @return
     */
    @RequestMapping("/toSkuAttr")
    @RequiresPermissions("SKU_ATTR_BUTTON")
    public String toSkuAttr(String productType,Model model){
        List<SkuDTO> skuDTOList = skuService.findSkuByProductType(productType);
        List<Integer> ArrIds = new ArrayList<>();
        for (SkuDTO skus:skuDTOList) {
            ArrIds.add(skus.getAttrId());
        }
        model.addAttribute("productType",productType);
        model.addAttribute("ArrIds",ArrIds);
        return "attr/sku_attr";
    }


}
