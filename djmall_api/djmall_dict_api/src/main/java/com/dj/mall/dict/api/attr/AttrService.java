package com.dj.mall.dict.api.attr;

import com.dj.mall.dict.dto.attr.AttrDTO;
import com.dj.mall.dict.dto.attr.AttrValueDTO;
import com.dj.mall.dict.dto.sku.SkuDTO;
import com.dj.mall.model.base.BusinessException;

import java.util.List;

/**
 * attr 接口
 */
public interface AttrService {

    /**
     * 商品属性展示
     * @return
     * @throws BusinessException
     */
    List<AttrDTO> findAllAttr(SkuDTO skuDTO) throws BusinessException;

/*    *//**
     *
     * @param ArrIds
     * @return
     *//*
    List<AttrDTO> findAttrAllByAttrId(Integer[] ArrIds) throws BusinessException;*/

    /**
     * 根据id查找商品属性
     * @param attrId
     * @return
     * @throws BusinessException
     */
    AttrDTO findById(Integer attrId) throws BusinessException;

    /**
     * 通过属性id查找属性值
     * @param attrId
     * @return
     * @throws BusinessException
     */
    List<AttrValueDTO> findAllValue(Integer attrId)throws BusinessException;

    /**
     * 新增属性值
     * @param attrValueDto
     * @throws BusinessException
     */
    void addValue(AttrValueDTO attrValueDto)throws BusinessException;

    /**
     * 删除属性值
     * @param valueId
     * @throws BusinessException
     */
    void delValue(Integer valueId)throws BusinessException;

    /**
     * 新增角色属性
     * @param attrDto
     */
    void addAttr(AttrDTO attrDto)throws BusinessException;

    /**
     * 通过attrName条件查询信息
     * @param attrName
     * @return
     * @throws BusinessException
     */
    AttrDTO findAttrByAttrName(String attrName) throws BusinessException;
}
