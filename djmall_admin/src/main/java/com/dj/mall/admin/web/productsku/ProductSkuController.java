package com.dj.mall.admin.web.productsku;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.admin.vo.sku.ProductSkuVoResp;
import com.dj.mall.model.base.ResultModel;
import com.dj.mall.model.util.DozerUtil;
import com.dj.mall.product.api.sku.ProductSkuService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product/sku/")
public class ProductSkuController {

    @Reference
    private ProductSkuService productSkuService;

    @RequestMapping("show")
    public ResultModel show(Integer productId){
        List<ProductSkuVoResp> productSkuVoResps = DozerUtil.mapList(productSkuService.findSkuByProductId(productId), ProductSkuVoResp.class);
        for (ProductSkuVoResp sku:productSkuVoResps) {
            if (sku.getSkuStatus().equals("PRODUCT_DOWN")){
                sku.setDownBtn("已下架");
            }else{
                sku.setDownBtn("下架");
            }
        }
        return new ResultModel().success(productSkuVoResps);
    }


}
