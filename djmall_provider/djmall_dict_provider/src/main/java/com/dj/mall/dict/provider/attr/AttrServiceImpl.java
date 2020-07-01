package com.dj.mall.dict.provider.attr;


import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.mall.auth.dto.ZtreeDataDTO;
import com.dj.mall.auth.dto.resource.ResourceDTO;
import com.dj.mall.dict.api.attr.AttrService;
import com.dj.mall.dict.bo.AttrBo;
import com.dj.mall.dict.dto.attr.AttrDTO;
import com.dj.mall.dict.dto.attr.AttrValueDTO;
import com.dj.mall.dict.entity.attr.Attr;
import com.dj.mall.dict.entity.attr.AttrValue;
import com.dj.mall.dict.mapper.attr.AttrMapper;
import com.dj.mall.dict.service.AttrValueService;
import com.dj.mall.model.base.BusinessException;
import com.dj.mall.model.util.DozerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
@Service
@Transactional(rollbackFor = Exception.class)
public class AttrServiceImpl extends ServiceImpl<AttrMapper, Attr> implements AttrService {

    @Autowired
    private AttrValueService attrValueService;

    /**
     * 商品属性展示
     *
     * @return
     * @throws BusinessException
     */
    @Override
    public List<AttrDTO> findAllAttr(Integer[] ArrIds) throws BusinessException {
        List<AttrBo> attrBoList =  getBaseMapper().findAllAttr();
        /*for (AttrBo attrBo:attrBoList) {
            for (Integer arrIds:ArrIds) {
                if (arrIds==attrBo.getAttrId()){
                    attrBo.setChecked(1);
                }
            }
        }*/
        return DozerUtil.mapList(attrBoList,AttrDTO.class);
    }

    /**
     * 根据id查找商品属性
     *
     * @param attrId
     * @return
     * @throws BusinessException
     */
    @Override
    public AttrDTO findById(Integer attrId) throws BusinessException {
        QueryWrapper<Attr> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",attrId);
        Attr attrEntity = super.getOne(queryWrapper);
        return DozerUtil.map(attrEntity,AttrDTO.class);
    }

    /**
     * 通过属性id查找属性值
     *
     * @param attrId
     * @return
     * @throws BusinessException
     */
    @Override
    public List<AttrValueDTO> findAllValue(Integer attrId) throws BusinessException {
        QueryWrapper<AttrValue> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("attr_id",attrId);
        return DozerUtil.mapList(attrValueService.list(queryWrapper),AttrValueDTO.class);
    }

    /**
     * 新增属性值
     *
     * @param attrValueDto
     * @throws BusinessException
     */
    @Override
    public void addValue(AttrValueDTO attrValueDto) throws BusinessException {
        attrValueService.save(DozerUtil.map(attrValueDto,AttrValue.class));
    }

    /**
     * 删除属性值
     *
     * @param valueId
     * @throws BusinessException
     */
    @Override
    public void delValue(Integer valueId) throws BusinessException {
        attrValueService.removeById(valueId);
    }

    /**
     * 新增角色属性
     *
     * @param attrDto
     */
    @Override
    public void addAttr(AttrDTO attrDto) throws BusinessException {
        save(DozerUtil.map(attrDto,Attr.class));
    }
}
