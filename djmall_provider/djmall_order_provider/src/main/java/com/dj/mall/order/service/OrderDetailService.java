package com.dj.mall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dj.mall.model.base.BusinessException;
import com.dj.mall.order.dto.detail.OrderDetailDTO;
import com.dj.mall.order.dto.order.OrderDTO;
import com.dj.mall.order.entity.detail.OrderDetail;

import java.util.List;

public interface OrderDetailService extends IService<OrderDetail> {
    /**
     * 详情表的批量新增
     * @param orderDTO
     * @throws BusinessException
     */
    void saveBatchDetail(OrderDTO orderDTO) throws BusinessException;

}
