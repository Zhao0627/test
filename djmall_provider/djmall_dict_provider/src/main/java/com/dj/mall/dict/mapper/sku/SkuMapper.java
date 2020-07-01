package com.dj.mall.dict.mapper.sku;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dj.mall.dict.bo.SkuBo;
import com.dj.mall.dict.entity.sku.Sku;

import java.util.List;

public interface SkuMapper extends BaseMapper<Sku> {

    List<SkuBo> findAllSku();
}
