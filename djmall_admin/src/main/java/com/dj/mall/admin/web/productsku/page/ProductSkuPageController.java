package com.dj.mall.admin.web.productsku.page;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.product.api.sku.ProductSkuService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product/sku/")
public class ProductSkuPageController {

    @Reference
    private ProductSkuService productSkuService;
}
