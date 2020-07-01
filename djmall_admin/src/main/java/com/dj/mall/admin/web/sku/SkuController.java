package com.dj.mall.admin.web.sku;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.admin.vo.SkuVoResp;
import com.dj.mall.dict.api.sku.SkuService;
import com.dj.mall.dict.dto.sku.SkuDTO;
import com.dj.mall.model.base.BusinessException;
import com.dj.mall.model.base.ResultModel;
import com.dj.mall.model.util.DozerUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dict/sku")
public class SkuController {

    @Reference
    private SkuService skuService;

    /**
     * 商品属性展示
     */
    @PostMapping("/list")
    @RequiresPermissions("SKU_MANAGER")
    public ResultModel findAttrAll() throws BusinessException{
        List<SkuDTO> skuDtoList = skuService.findAllSku();
        return new ResultModel().success(DozerUtil.mapList(skuDtoList, SkuVoResp.class));
    }

    /**
     * 商品属性新增
     */
    @PostMapping("/saveSku")
    @RequiresPermissions("SKU_ATTR_SAVE_BTN")
    public ResultModel saveSku(Integer[] attrId,String productType) throws BusinessException{
        skuService.save(attrId,productType);
        return new ResultModel().success("保存成功");
    }
}
