package com.dj.mall.admin.web.attr;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.admin.vo.AttrVoResp;
import com.dj.mall.dict.api.attr.AttrService;
import com.dj.mall.dict.dto.attr.AttrDTO;
import com.dj.mall.dict.dto.attr.AttrValueDTO;
import com.dj.mall.model.base.BusinessException;
import com.dj.mall.model.base.ResultModel;
import com.dj.mall.model.util.DozerUtil;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("dict/attr")
public class AttrController {

    @Reference
    private AttrService attrService;

    /**
     * 商品属性展示
     */
    @PostMapping("/list")
    @RequiresPermissions("ATTR_MANAGER")
    public ResultModel findAttrAll(@RequestParam(value ="ArrIds")String[] ArrIds) throws BusinessException {
        Integer[] dosageArray = (Integer[]) ConvertUtils.convert(ArrIds, Integer.class);
        List<AttrDTO> attrDtoList = attrService.findAllAttr(dosageArray);
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
        attrService.addAttr(DozerUtil.map(attrVoResp,AttrDTO.class));
        return new ResultModel().success("新增成功");
    }


}
