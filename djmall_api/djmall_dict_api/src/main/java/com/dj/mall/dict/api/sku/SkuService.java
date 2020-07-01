package com.dj.mall.dict.api.sku;

import com.dj.mall.dict.dto.sku.SkuDTO;
import com.dj.mall.model.base.BusinessException;

import java.util.List;

/**
 * sku接口
 */
public interface SkuService {
    /**
     * sku展示
     * @return
     */
    List<SkuDTO> findAllSku() throws BusinessException;

    /**
     * sku新增
     * @param attrId
     * @throws BusinessException
     */
    void save(Integer[] attrId,String productType) throws BusinessException;

    /**
     * 通过productType查询Sku
     * @param productType
     * @return
     */
    List<SkuDTO> findSkuByProductType(String productType)throws BusinessException;
}
