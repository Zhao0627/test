package com.dj.mall.admin.web.productspu.page;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.admin.config.ResourceConstant;
import com.dj.mall.admin.vo.basedata.BaseDataVoResp;
import com.dj.mall.admin.vo.logistics.LogisticsVoResp;
import com.dj.mall.admin.vo.spu.ProductSpuVoReq;
import com.dj.mall.admin.vo.spu.ProductSpuVoResp;
import com.dj.mall.auth.dto.user.UserDTO;
import com.dj.mall.dict.api.basedata.BaseDataService;
import com.dj.mall.dict.api.logistics.LogisticsService;
import com.dj.mall.model.util.DozerUtil;
import com.dj.mall.model.util.SystemConstant;
import com.dj.mall.product.api.sku.ProductSkuService;
import com.dj.mall.product.api.spu.ProductSpuService;
import com.dj.mall.product.dto.spu.ProductSpuDTO;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/product/spu/")
public class ProductSpuPageController {

    @Reference
    private BaseDataService baseDataService;

    @Reference
    private LogisticsService logisticsService;

    @Reference
    private ProductSpuService productSpuService;

    @Reference
    private ProductSkuService productSkuService;

    @RequestMapping("toShow")
    @RequiresPermissions(ResourceConstant.PRODUCT_SPU_MANAGER)
    public String toShow(Model model, HttpSession session){
        List<BaseDataVoResp> productType = DozerUtil.mapList(baseDataService.getBasedataByPCode(SystemConstant.PRODUCT_TYPE), BaseDataVoResp.class);
        UserDTO user = (UserDTO) session.getAttribute("user");
        ProductSpuVoReq productSpuVoReq = new ProductSpuVoReq();
        if (user.getUserLevel().equals(SystemConstant.COMMERCIAL_ID)){
            productSpuVoReq.setUserId(user.getUserId());
        }
        if (user.getUserLevel().equals(SystemConstant.ADMIN_ID)){
            productSpuVoReq.setUserId(null);
        }
        Integer spuCount = productSpuService.findSpuCount(DozerUtil.map(productSpuVoReq,ProductSpuDTO.class));
        model.addAttribute("productType",productType);
        model.addAttribute("spuCount",spuCount);
        return "product/show";
    }

    @RequestMapping("toSave")
    @RequiresPermissions(ResourceConstant.PRODUCT_SAVE_BTN)
    public String toSave(Model model){
        model.addAttribute("productType",DozerUtil.mapList(baseDataService.getBasedataByPCode(SystemConstant.PRODUCT_TYPE),BaseDataVoResp.class));
        model.addAttribute("logistics",DozerUtil.mapList(logisticsService.findLogAll(), LogisticsVoResp.class));
        return "product/save";
    }

    @RequestMapping("toUpdate")
    @RequiresPermissions(ResourceConstant.PRODUCT_UPDATE_BTN)
    public String toUpdate(Integer productId,Model model){
        ProductSpuVoResp spu = DozerUtil.map(productSpuService.findSpuById(productId), ProductSpuVoResp.class);
        model.addAttribute("logistics",DozerUtil.mapList(logisticsService.findLogAll(), LogisticsVoResp.class));
        model.addAttribute("type",DozerUtil.mapList(baseDataService.getBasedataByPCode(SystemConstant.PRODUCT_TYPE),BaseDataVoResp.class));
        model.addAttribute("spu",spu);
        model.addAttribute("productId",productId);
        return "product/update";
    }

}
