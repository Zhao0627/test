package com.dj.mall.product.mapper.spu;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dj.mall.product.bo.spu.ProductSpuBo;
import com.dj.mall.product.dto.spu.ProductSpuDTO;
import com.dj.mall.product.entity.spu.ProductSpu;
import org.springframework.dao.DataAccessException;

import javax.xml.crypto.Data;
import java.util.List;

public interface ProductSpuMapper extends BaseMapper<ProductSpu> {

    /**
     * 条件查询条件
     * @param productSpuDTO
     * @return
     */
    List<ProductSpuBo> findSpuAll(ProductSpuDTO productSpuDTO) throws DataAccessException;

    /**
     * 级联修改 下架商品
     * @param productSpu
     */
    void updateStatus(ProductSpu productSpu) throws DataAccessException;
}
