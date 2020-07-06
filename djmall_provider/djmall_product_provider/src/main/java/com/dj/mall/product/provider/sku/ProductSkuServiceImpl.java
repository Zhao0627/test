package com.dj.mall.product.provider.sku;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.mall.model.util.DozerUtil;
import com.dj.mall.product.api.sku.ProductSkuService;
import com.dj.mall.product.dto.sku.ProductSkuDTO;
import com.dj.mall.product.entity.sku.ProductSku;
import com.dj.mall.product.mapper.sku.ProductSkuMapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class ProductSkuServiceImpl extends ServiceImpl<ProductSkuMapper, ProductSku> implements ProductSkuService {

    /**
     * sku批量新增
     *
     * @param skuList
     */
    @Override
    public void saveBeach(List<ProductSkuDTO> skuList) {
        saveBatch(DozerUtil.mapList(skuList,ProductSku.class));
    }
}
