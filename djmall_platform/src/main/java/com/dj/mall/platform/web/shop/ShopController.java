package com.dj.mall.platform.web.shop;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.model.base.ResultModel;
import com.dj.mall.model.util.DozerUtil;
import com.dj.mall.platform.vo.product.ProductSpuVoReq;
import com.dj.mall.platform.vo.product.ProductSpuVoResp;
import com.dj.mall.product.api.spu.ProductSpuService;
import com.dj.mall.product.dto.spu.ProductSpuDTO;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/shop/")
@RestController
public class ShopController {

    @Reference
    private ProductSpuService productSpuService;

    @RequestMapping("show")
    public Map<String,Object> show(ProductSpuVoReq productSpuVoReq){
        Integer page = productSpuVoReq.getPage();
        productSpuVoReq.setPage((page-1)*productSpuVoReq.getPageSize());
        Map<String,Object> map = new HashMap<>();
        map.put("data",DozerUtil.mapList(productSpuService.findShopAll(DozerUtil.map(productSpuVoReq, ProductSpuDTO.class)),ProductSpuVoResp.class));
        map.put("code",0);
        return map;
    }

    @RequestMapping("show2")
    public ResultModel show2(ProductSpuVoReq productSpuVoReq) throws IOException, SolrServerException {
        Integer page = productSpuVoReq.getPage();
        if(null==page){
            page=1;
        }
        productSpuVoReq.setPage((page-1)*productSpuVoReq.getPageSize());
        return new ResultModel().success(productSpuService.showProduct(DozerUtil.map(productSpuVoReq, ProductSpuDTO.class)));
    }

    @RequestMapping("getCount")
    public ResultModel getCount(ProductSpuVoReq productSpuVoReq){
        ProductSpuDTO shopByProductSkuId = productSpuService.findShopByProductSkuId(DozerUtil.map(productSpuVoReq, ProductSpuDTO.class));
        return new ResultModel().success(shopByProductSkuId.getSkuCount());
    }

}
