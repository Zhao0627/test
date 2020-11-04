package com.dj.mall.order.api.order;

import com.dj.mall.model.base.BusinessException;
import com.dj.mall.order.dto.order.OrderReceiverDTO;

import java.util.List;

public interface OrderReceiverApi {

    /**
     * 查询登陆人的所有收货地址相关
     * @param userId
     * @return
     */
    List<OrderReceiverDTO> findorderReceiverByUserId(Integer userId) throws BusinessException;

    /**
     * 新增数据到地址表
     * @param orderReceiverDTO
     */
    void saveReceiver(OrderReceiverDTO orderReceiverDTO) throws BusinessException;

}
