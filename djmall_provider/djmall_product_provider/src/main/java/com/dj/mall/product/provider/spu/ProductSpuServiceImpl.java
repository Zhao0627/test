package com.dj.mall.product.provider.spu;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.mall.model.base.BusinessException;
import com.dj.mall.model.util.DozerUtil;
import com.dj.mall.model.util.SystemConstant;
import com.dj.mall.product.api.sku.ProductSkuService;
import com.dj.mall.product.api.spu.ProductSpuService;
import com.dj.mall.product.dto.sku.ProductSkuDTO;
import com.dj.mall.product.dto.spu.ProductSpuDTO;
import com.dj.mall.product.entity.spu.ProductSpu;
import com.dj.mall.product.mapper.sku.ProductSkuMapper;
import com.dj.mall.product.mapper.spu.ProductSpuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class ProductSpuServiceImpl extends ServiceImpl<ProductSpuMapper, ProductSpu> implements ProductSpuService {

    @Autowired
    private ProductSpuMapper productSpuMapper;

    @Autowired
    private ProductSkuService productSkuService;

    /**
     * （查询）展示商品
     *
     * @param productSpuDTO
     * @return
     */
    @Override
    public List<ProductSpuDTO> findSpuAll(ProductSpuDTO productSpuDTO) {
        return DozerUtil.mapList(productSpuMapper.findSpuAll(productSpuDTO),ProductSpuDTO.class);
    }

    /**
     * 新增spu和sku
     *
     * @param productSpuDTO
     * @throws BusinessException
     */
    @Override
    public void insertSpuAndSku(ProductSpuDTO productSpuDTO) throws BusinessException {
        ProductSpu productSpu = DozerUtil.map(productSpuDTO, ProductSpu.class);
        save(productSpu);
        for (ProductSkuDTO productSku:productSpuDTO.getSkuList()) {
            productSku.setProductId(productSpu.getId());
        }
        productSkuService.saveBeach(productSpuDTO.getSkuList());



    }

    /**
     * 下架商品
     * @param productSpuDTO
     * @throws BusinessException
     */
    @Override
    public void productDown(ProductSpuDTO productSpuDTO) throws BusinessException {
        productSpuMapper.updateStatus(DozerUtil.map(productSpuDTO,ProductSpu.class));
    }
}
