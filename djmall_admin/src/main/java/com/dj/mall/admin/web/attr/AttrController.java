package com.dj.mall.admin.web.attr;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.admin.vo.AttrVoReq;
import com.dj.mall.admin.vo.AttrVoResp;
import com.dj.mall.admin.vo.SkuVoResp;
import com.dj.mall.dict.api.attr.AttrService;
import com.dj.mall.dict.api.sku.SkuService;
import com.dj.mall.dict.dto.attr.AttrDTO;
import com.dj.mall.dict.dto.attr.AttrValueDTO;
import com.dj.mall.dict.dto.sku.SkuDTO;
import com.dj.mall.model.base.BusinessException;
import com.dj.mall.model.base.ResultModel;
import com.dj.mall.model.util.DozerUtil;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("dict/attr")
public class AttrController {

    @Reference
    private AttrService attrService;

    @Reference
    private SkuService skuService;

    /**
     * 商品属性展示
     */
    @PostMapping("/list")
    @RequiresPermissions("ATTR_MANAGER")
    public ResultModel findAttrAll(SkuVoResp skuVoResp) throws BusinessException {
        System.out.println(skuVoResp.getProductType());
        List<AttrDTO> attrDtoList = attrService.findAllAttr(DozerUtil.map(skuVoResp,SkuDTO.class));
        for (AttrDTO attrDTO: attrDtoList) {
            System.out.println(attrDTO.getValueList());
        }
        return new ResultModel().success(attrDtoList);
    }

    /**
     * 商品属性展示
     */
    @RequestMapping("/findAttrAllByAttrId")
    @RequiresPermissions("ATTR_MANAGER")
    public ResultModel findAttrAllByAttrId(String productType) throws BusinessException {
        List<AttrDTO> attrDtoList = attrService.findAllAttr(new SkuDTO());
        List<SkuDTO> skuDTOList = skuService.findSkuByProductType(productType);
        List<Integer> ArrIds = new ArrayList<>();
        for (SkuDTO skus:skuDTOList) {
            ArrIds.add(skus.getAttrId());
        }
        for (AttrDTO attrBo:attrDtoList) {
            for (Integer arrIds:ArrIds) {
                System.out.println(arrIds);
                System.out.println(attrBo.getAttrId());
                if (arrIds==attrBo.getAttrId()){
                    attrBo.setChecked(1);
                }
            }
        }
        return new ResultModel().success(attrDtoList);
    }

    /**
     * 商品属性展示
     */
    @PostMapping("/valueList")
    @RequiresPermissions("ATTR_MANAGER")
    public ResultModel findValueAll(Integer attrId) throws BusinessException {
        List<AttrValueDTO> attrDtoList = attrService.findAllValue(attrId);
        return new ResultModel().success(attrDtoList);
    }
    /**
     * 新增属性值
     * @param
     * @return
     * @throws Exception
     */
    @PostMapping("/addValue")
    @RequiresPermissions("VALUE_ADD_BUTTON")
    public ResultModel add(AttrValueDTO attrValueDto) throws Exception{
        attrService.addValue(attrValueDto);
        return new ResultModel().success("新增成功");
    }
    /**
     * 移除属性值
     * @param
     * @return
     * @throws Exception
     */
    @PostMapping("/delValue")
    @RequiresPermissions("VALUE_DEL_BUTTON")
    public ResultModel delValue(Integer valueId) throws Exception{
        attrService.delValue(valueId);
        return new ResultModel().success("删除成功");
    }
    /**
     * 新增商品属性
     */
    @PostMapping("/add")
    @RequiresPermissions("ATTR_ADD_BUTTON")
    public ResultModel addAttr(AttrVoResp attrVoResp) throws Exception{
        AttrDTO attr = attrService.findAttrByAttrName(attrVoResp.getAttrName());
        if (attr!=null){
            return new ResultModel().error("新增重复");
        }
        attrService.addAttr(DozerUtil.map(attrVoResp,AttrDTO.class));
        return new ResultModel().success("新增成功");
    }


}
