package com.dj.mall.dict.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.mall.dict.entity.attr.AttrValue;
import com.dj.mall.dict.mapper.attr.AttrValueMapper;
import com.dj.mall.dict.service.AttrValueService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 商品属性值实现
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AttrValueServiceImpl extends ServiceImpl<AttrValueMapper, AttrValue> implements AttrValueService {

}
