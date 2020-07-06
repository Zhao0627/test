package com.dj.mall.product.api.sku;

import com.dj.mall.product.dto.sku.ProductSkuDTO;

import java.util.List;

public interface ProductSkuService{
    /**
     * sku批量新增
     * @param skuList
     */
    void saveBeach(List<ProductSkuDTO> skuList);
}
