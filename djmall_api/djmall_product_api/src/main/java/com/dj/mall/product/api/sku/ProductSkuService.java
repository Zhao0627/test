package com.dj.mall.product.api.sku;

import com.dj.mall.model.base.BusinessException;
import com.dj.mall.product.dto.sku.ProductSkuDTO;

import java.util.List;

public interface ProductSkuService{
    /**
     * sku批量新增
     * @param skuList
     */
    void saveBeach(List<ProductSkuDTO> skuList) throws BusinessException;

    /**
     * 通过productId查询sku
     * @param productId
     * @return
     * @throws BusinessException
     */
    List<ProductSkuDTO> findSkuByProductId(Integer productId) throws BusinessException;

    /**
     * 通过id查询Sku表
     * @param id
     * @return
     * @throws BusinessException
     */
    ProductSkuDTO findSkuById(Integer id) throws BusinessException;

    /**
     * 修改Sku表
     * @param productSkuDTO
     * @throws BusinessException
     */
    void updateSku(ProductSkuDTO productSkuDTO) throws BusinessException;

    /**
     * 批量修改sku
     * @param skuList
     */
    void updateBatchById(List<ProductSkuDTO> skuList);

    /**
     * 提交订单修改库存
     * @param productSkuDTOList
     */
    void updateSkuCount(List<ProductSkuDTO> productSkuDTOList);

    /**
     * 取消订单减库存
     * @param productSkuDTOList
     */
    void updateSkuCountAdd(List<ProductSkuDTO> productSkuDTOList);
}
