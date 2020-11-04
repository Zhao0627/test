package com.dj.mall.order.mapper.order;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dj.mall.order.entity.order.Order;
import org.springframework.dao.DataAccessException;

public interface OrderMapper extends BaseMapper<Order> {

    /**
     * 修改订单状态
     * @param orderStatus
     * @param parentOrderNo
     * @throws DataAccessException
     */
    void updateOrderStatus(String orderStatus, String parentOrderNo,Integer userId) throws DataAccessException;

}
