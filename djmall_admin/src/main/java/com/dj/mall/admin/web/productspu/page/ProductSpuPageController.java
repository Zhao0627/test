package com.dj.mall.admin.web.productspu.page;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.admin.config.ResourceConstant;
import com.dj.mall.admin.vo.BaseDataVoResp;
import com.dj.mall.admin.vo.LogisticsVoResp;
import com.dj.mall.dict.api.basedata.BaseDataService;
import com.dj.mall.dict.api.logistics.LogisticsService;
import com.dj.mall.dict.dto.basedata.BaseDataDTO;
import com.dj.mall.model.util.DozerUtil;
import com.dj.mall.model.util.SystemConstant;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/product/spu/")
public class ProductSpuPageController {

    @Reference
    private BaseDataService baseDataService;

    @Reference
    private LogisticsService logisticsService;

    @RequestMapping("toShow")
    @RequiresPermissions(ResourceConstant.RESOURCE_MANAGER)
    public String toShow(Model model){
        List<BaseDataDTO> productType1 = baseDataService.getBasedataByPCode(SystemConstant.PRODUCT_TYPE);
        List<BaseDataVoResp> productType = DozerUtil.mapList(productType1, BaseDataVoResp.class);
        model.addAttribute("productType",productType);
        return "product/show";
    }

    @RequestMapping("toSave")
    @RequiresPermissions(ResourceConstant.RESOURCE_MANAGER)
    public String toSave(Model model){
        model.addAttribute("productType",DozerUtil.mapList(baseDataService.getBasedataByPCode(SystemConstant.PRODUCT_TYPE),BaseDataVoResp.class));
        model.addAttribute("logistics",DozerUtil.mapList(logisticsService.findLogAll(), LogisticsVoResp.class));
        return "product/save";
    }

}
