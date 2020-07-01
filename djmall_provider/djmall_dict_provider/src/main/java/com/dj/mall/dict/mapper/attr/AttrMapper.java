package com.dj.mall.dict.mapper.attr;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dj.mall.dict.bo.AttrBo;
import com.dj.mall.dict.entity.attr.Attr;

import java.util.List;

public interface AttrMapper  extends BaseMapper<Attr> {

    /**
     * 自定义sql
     * @return
     */
    List<AttrBo> findAllAttr();

}
