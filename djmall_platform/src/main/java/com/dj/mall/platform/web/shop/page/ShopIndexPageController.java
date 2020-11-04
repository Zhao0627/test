package com.dj.mall.platform.web.shop.page;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.cmpt.api.RedisService.RedisService;
import com.dj.mall.dict.api.basedata.BaseDataService;
import com.dj.mall.model.util.DozerUtil;
import com.dj.mall.model.util.SystemConstant;
import com.dj.mall.platform.vo.basedata.BaseDataVoResp;
import com.dj.mall.platform.vo.product.ProductSkuVoResp;
import com.dj.mall.platform.vo.product.ProductSpuVoReq;
import com.dj.mall.platform.vo.product.ProductSpuVoResp;
import com.dj.mall.product.api.sku.ProductSkuService;
import com.dj.mall.product.api.spu.ProductSpuService;
import com.dj.mall.product.dto.sku.ProductSkuDTO;
import com.dj.mall.product.dto.spu.ProductSpuDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 商品表的业务控制层
 */
@Controller
@RequestMapping("/shop/")
public class ShopIndexPageController {

    /**
     * 字典数据的接口
     */
    @Reference
    private BaseDataService baseDataService;

    /**
     * redis的接口
     */
    @Reference
    private RedisService redisService;


    @Reference
    private ProductSpuService productSpuService;

    @Reference
    private ProductSkuService productSkuService;

    @RequestMapping("toIndexShow")
    public String toIndexShow(Model model,String TOKEN){
        List<BaseDataVoResp> productType = DozerUtil.mapList(baseDataService.getBasedataByPCode(SystemConstant.PRODUCT_TYPE), BaseDataVoResp.class);
        if (TOKEN != null || TOKEN != ""){
            redisService.del(SystemConstant.TOKEN+TOKEN);
        }
        model.addAttribute("productType",productType);
        return "shop/index_show";
    }

    @RequestMapping("toDetails")
    public String toDetails(Integer productSkuId,Model model){
        ProductSpuVoReq productSpuVoReq = new ProductSpuVoReq();
        productSpuVoReq.setProductSkuId(productSkuId);
        ProductSpuDTO productSpuDTO = productSpuService.findShopByProductSkuId(DozerUtil.map(productSpuVoReq, ProductSpuDTO.class));
        List<ProductSkuDTO> productSkuDTOList = productSkuService.findSkuByProductId(productSpuDTO.getProductId());

        model.addAttribute("spu",DozerUtil.map(productSpuDTO, ProductSpuVoResp.class));
        model.addAttribute("skuList",DozerUtil.mapList(productSkuDTOList, ProductSkuVoResp.class));
        return "shop/shop_details";
    }

}

