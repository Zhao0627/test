package com.dj.mall.dict.provider.sku;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.mall.dict.api.sku.SkuService;
import com.dj.mall.dict.bo.SkuBo;
import com.dj.mall.dict.dto.sku.SkuDTO;
import com.dj.mall.dict.entity.sku.Sku;
import com.dj.mall.dict.mapper.sku.SkuMapper;
import com.dj.mall.model.base.BusinessException;
import com.dj.mall.model.util.DozerUtil;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * sku实现
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SkuServiceImpl extends ServiceImpl<SkuMapper, Sku> implements SkuService {

    /**
     * sku展示
     * @return
     */
    @Override
    public List<SkuDTO> findAllSku() throws BusinessException {
        List<SkuBo> skuEntityList = getBaseMapper().findAllSku();
        return DozerUtil.mapList(skuEntityList,SkuDTO.class);
    }

    /**
     * sku新增
     *
     * @param attrId
     * @throws BusinessException
     */
    @Override
    public void save(Integer[] attrId,String productType) throws BusinessException {
        List<Sku> skuList = new ArrayList<>();
        for (int i = 0; i < attrId.length; i++) {
            Sku sku = new Sku();
            sku.setAttrId(attrId[i]);
            sku.setProductType(productType);
            skuList.add(sku);
        }
        QueryWrapper<Sku> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_type",productType);
        remove(queryWrapper);
        saveBatch(skuList);
    }

    /**
     * 通过productType查询Sku
     *
     * @param productType
     * @return
     */
    @Override
    public List<SkuDTO> findSkuByProductType(String productType) throws BusinessException {
        QueryWrapper<Sku> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_type",productType);
        return DozerUtil.mapList(list(queryWrapper),SkuDTO.class);
    }
}
