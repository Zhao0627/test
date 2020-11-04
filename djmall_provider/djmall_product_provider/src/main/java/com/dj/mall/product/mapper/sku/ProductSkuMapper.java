package com.dj.mall.product.mapper.sku;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dj.mall.product.dto.sku.ProductSkuDTO;
import com.dj.mall.product.entity.sku.ProductSku;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface ProductSkuMapper extends BaseMapper<ProductSku> {

    void updateSkuCount(@Param("skuList") List<ProductSku> skuList) throws DataAccessException;

    void updateSkuCountAdd(@Param("skuList") List<ProductSkuDTO> skuList) throws DataAccessException;
}
