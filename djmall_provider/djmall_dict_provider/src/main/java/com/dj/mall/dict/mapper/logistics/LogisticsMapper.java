package com.dj.mall.dict.mapper.logistics;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dj.mall.dict.bo.LogisticsBo;
import com.dj.mall.dict.entity.logistics.Logistics;

import java.util.List;

/**
 * 基础数据mapper
 */
public interface LogisticsMapper extends BaseMapper<Logistics> {

    /**
     * 查询所有
     * @return
     */
    List<LogisticsBo> findLogAll();
}
