package com.dj.mall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dj.mall.model.base.BusinessException;
import com.dj.mall.order.dto.order.OrderDTO;
import com.dj.mall.order.entity.info.OrderInfo;

public interface OrderInfoService extends IService<OrderInfo> {
    /**
     * 新增info表数据
     * @param orderDTO
     */
    void saveInfo(OrderDTO orderDTO)throws BusinessException;
}
