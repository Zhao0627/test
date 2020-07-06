package com.dj.mall.product.api.spu;

import com.dj.mall.model.base.BusinessException;
import com.dj.mall.product.dto.spu.ProductSpuDTO;

import java.util.List;

/**
 * 商品展示接口
 */
public interface ProductSpuService {

    /**
     * （查询）展示商品
     * @param productSpuDTO
     * @return
     */
    List<ProductSpuDTO> findSpuAll(ProductSpuDTO productSpuDTO) throws BusinessException;


    /**
     * 新增spu和sku
     * @param productSpuDTO
     * @throws BusinessException
     */
    void insertSpuAndSku(ProductSpuDTO productSpuDTO) throws BusinessException;

    /**
     * 下架商品
     */
    void productDown(ProductSpuDTO productSpuDTO) throws BusinessException;

}
