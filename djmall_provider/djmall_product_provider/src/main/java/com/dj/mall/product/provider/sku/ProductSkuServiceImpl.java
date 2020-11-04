package com.dj.mall.product.provider.sku;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.mall.model.base.BusinessException;
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

    /**
     * 通过productId查询sku
     *
     * @param productId
     * @return
     * @throws BusinessException
     */
    @Override
    public List<ProductSkuDTO> findSkuByProductId(Integer productId) throws BusinessException {
        QueryWrapper<ProductSku> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_id",productId);
        return DozerUtil.mapList(list(queryWrapper),ProductSkuDTO.class);
    }

    /**
     * 通过id查询Sku表
     *
     * @param id
     * @return
     * @throws BusinessException
     */
    @Override
    public ProductSkuDTO findSkuById(Integer id) throws BusinessException {
        return DozerUtil.map(getById(id),ProductSkuDTO.class);
    }

    /**
     * 修改Sku表
     *
     * @param productSkuDTO
     * @throws BusinessException
     */
    @Override
    public void updateSku(ProductSkuDTO productSkuDTO) throws BusinessException {
        updateById(DozerUtil.map(productSkuDTO,ProductSku.class));
    }

    /**
     * 批量修改sku
     *
     * @param skuList
     */
    @Override
    public void updateBatchById(List<ProductSkuDTO> skuList) {
        updateBatchById(DozerUtil.mapList(skuList,ProductSku.class));
    }

    /**
     * 提交订单修改库存
     *
     * @param productSkuDTOList
     */
    @Override
    public void updateSkuCount(List<ProductSkuDTO> productSkuDTOList) {
        getBaseMapper().updateSkuCount(DozerUtil.mapList(productSkuDTOList,ProductSku.class));
    }

    /**
     * 取消订单减库存
     *
     * @param productSkuDTOList
     */
    @Override
    public void updateSkuCountAdd(List<ProductSkuDTO> productSkuDTOList) {
        getBaseMapper().updateSkuCountAdd(productSkuDTOList);
    }
}
