package com.dj.mall.product.api.spu;

import com.dj.mall.model.base.BusinessException;
import com.dj.mall.product.dto.ProductSpuAndSkuDTO;
import com.dj.mall.product.dto.spu.ProductSpuDTO;
import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;
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

    /**
     * 通过id查询Spu表
     * @param productId
     * @return
     * @throws BusinessException
     */
    ProductSpuDTO findSpuById(Integer productId) throws BusinessException;

    /**
     * 查询信息数量
     * @return
     */
    Integer findSpuCount(ProductSpuDTO productSpuDTO) throws BusinessException;

    /**
     * 修改spu和sku表
     * @param productSpuDTO
     */
    void updateSpuAndSku(ProductSpuDTO productSpuDTO) throws BusinessException;

    /**
     * 展示商品
     * @param productSpuDTO
     * @return
     * @throws BusinessException
     */
    List<ProductSpuDTO> findShopAll(ProductSpuDTO productSpuDTO) throws BusinessException;

    /**
     * 通过skuId查询商品表
     * @param productSpuDTO
     * @return
     */
    ProductSpuDTO findShopByProductSkuId(ProductSpuDTO productSpuDTO) throws BusinessException;

    /**
     * 展示购物车
     * @param skuIds
     * @return
     * @throws BusinessException
     */
    List<ProductSpuDTO> findShopByProductSkuIds(List<Integer> skuIds,String IsDefault) throws BusinessException;

    /**
     * 商品展示
     * @param productSpuDTO
     * @return ProductSpuDTO
     * @throws BusinessException
     */
    List<ProductSpuAndSkuDTO> showProduct(ProductSpuDTO productSpuDTO) throws BusinessException, IOException, SolrServerException;

}
