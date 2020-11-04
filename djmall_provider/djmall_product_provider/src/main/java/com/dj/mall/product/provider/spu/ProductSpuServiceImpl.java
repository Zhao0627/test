package com.dj.mall.product.provider.spu;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.mall.model.base.BusinessException;
import com.dj.mall.model.util.DozerUtil;
import com.dj.mall.model.util.SystemConstant;
import com.dj.mall.product.api.sku.ProductSkuService;
import com.dj.mall.product.api.spu.ProductSpuService;
import com.dj.mall.product.dto.ProductSpuAndSkuDTO;
import com.dj.mall.product.dto.sku.ProductSkuDTO;
import com.dj.mall.product.dto.spu.ProductSpuDTO;
import com.dj.mall.product.entity.spu.ProductSpu;
import com.dj.mall.product.mapper.spu.ProductSpuMapper;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class ProductSpuServiceImpl extends ServiceImpl<ProductSpuMapper, ProductSpu> implements ProductSpuService {

    @Autowired
    private ProductSpuMapper productSpuMapper;

    @Autowired
    private ProductSkuService productSkuService;

    @Autowired
    private SolrClient solrClient;

    /**
     * （查询）展示商品
     *
     * @param productSpuDTO
     * @return
     */
    @Override
    public List<ProductSpuDTO> findSpuAll(ProductSpuDTO productSpuDTO) {
        if (productSpuDTO.getType()!=null){
            String[] type = productSpuDTO.getType().split(",");
            List<String> types = new ArrayList<>();
            for (String ty:type) {
                types.add(ty);
            }
            productSpuDTO.setTypes(types);
        }
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

    /**
     * 通过id查询Spu表
     *
     * @param productId
     * @return
     * @throws BusinessException
     */
    @Override
    public ProductSpuDTO findSpuById(Integer productId) throws BusinessException {
        return DozerUtil.map(getById(productId),ProductSpuDTO.class);
    }

    /**
     * 查询信息数量
     *
     * @return
     */
    @Override
    public Integer findSpuCount(ProductSpuDTO productSpuDTO) throws BusinessException {
        Integer count;
        if (productSpuDTO.getUserId()!=null){
            QueryWrapper<ProductSpu> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_id",productSpuDTO.getUserId());
            count = list(queryWrapper).size();
        }
        else{
            count = list().size();
        }
        return count;
    }

    /**
     * 修改spu和sku表
     *
     * @param productSpuDTO
     */
    @Override
    public void updateSpuAndSku(ProductSpuDTO productSpuDTO) throws BusinessException {
        updateById(DozerUtil.map(productSpuDTO,ProductSpu.class));
        productSkuService.updateBatchById(productSpuDTO.getSkuList());
    }

    /**
     * 展示商品
     *
     * @param productSpuDTO
     * @return
     * @throws BusinessException
     */
    @Override
    public List<ProductSpuDTO> findShopAll(ProductSpuDTO productSpuDTO) throws BusinessException {
        productSpuDTO.setStatus(SystemConstant.PRODUCT_UP);
        return DozerUtil.mapList(productSpuMapper.findShopAll(productSpuDTO),ProductSpuDTO.class);
    }

    /**
     * 通过skuId查询商品表
     *
     * @param productSpuDTO
     * @return
     */
    @Override
    public ProductSpuDTO findShopByProductSkuId(ProductSpuDTO productSpuDTO) {
        productSpuDTO.setStatus(SystemConstant.PRODUCT_UP);
        return DozerUtil.map(productSpuMapper.findShopByProductSkuId(productSpuDTO),ProductSpuDTO.class);
    }

    /**
     * 展示购物车
     *
     * @param skuIds
     * @return
     * @throws BusinessException
     */
    @Override
    public List<ProductSpuDTO> findShopByProductSkuIds(List<Integer> skuIds,String IsDefault) throws BusinessException {
        return DozerUtil.mapList(productSpuMapper.findShopByProductSkuIds(skuIds,IsDefault),ProductSpuDTO.class);
    }

    /**
     * 商品展示
     *
     * @return
     * @throws BusinessException
     */
    @Override
    public List<ProductSpuAndSkuDTO> showProduct(ProductSpuDTO productSpuDTO) throws BusinessException{
        //查询器
        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setQuery("*:*");
        solrQuery.setHighlight(true); // 开启高亮
        if (!StringUtils.isEmpty(productSpuDTO.getProductName())){
            String nameQuery="productKeywords:"+productSpuDTO.getProductName();
            solrQuery.setQuery(nameQuery);
            solrQuery.addHighlightField("productName"); // 设置高亮字段
            solrQuery.setHighlightSimplePre("<font style='color:red'>"); // 前缀
            solrQuery.setHighlightSimplePost("</font>"); // 后缀
        }
        if (!StringUtils.isEmpty(productSpuDTO.getType())){
            String typeQuery="type:"+productSpuDTO.getType();
            solrQuery.addFilterQuery(typeQuery);
        }
        if (!StringUtils.isEmpty(productSpuDTO.getStartPrice())){
            String startPrice="skuPrice:["+String.valueOf(productSpuDTO.getStartPrice())+" TO *]";
            System.out.println(startPrice);
            solrQuery.addFilterQuery(startPrice);
        }
        if (!StringUtils.isEmpty(productSpuDTO.getEndPrice())){
            String endPrice="skuPrice:[* TO "+String.valueOf(productSpuDTO.getEndPrice())+"]";
            System.out.println(endPrice);
            solrQuery.addFilterQuery(endPrice);
        }
        // 分页
        solrQuery.setStart(productSpuDTO.getPage());// 开始位置
        solrQuery.setRows(productSpuDTO.getPageSize());// 每页条数
        try {
            QueryResponse query  = solrClient.query(solrQuery);
            /*高亮字段*/
            List<ProductSpuAndSkuDTO> spuAndSkuDTOS = query.getBeans(ProductSpuAndSkuDTO.class);
            /*Map<String, Map<String, List<String>>> highlightMap = query.getHighlighting();
            if (null!=highlightMap){
                for (ProductSpuAndSkuDTO product:spuAndSkuDTOS) {
                    // 根据ID 获取高亮信息
                    Map<String, List<String>> fieldMap = highlightMap.get(product.getId());
                    if (fieldMap.size()!=0) {
                        // 获取高亮field
                        String productName = fieldMap.get("productName").get(0);
                        product.setProductName(productName);
                    }
                }
            }*/
            return spuAndSkuDTOS;
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
