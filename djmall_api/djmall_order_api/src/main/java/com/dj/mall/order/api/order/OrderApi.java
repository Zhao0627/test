package com.dj.mall.order.api.order;

import com.dj.mall.model.base.BusinessException;
import com.dj.mall.order.dto.detail.OrderDetailDTO;
import com.dj.mall.order.dto.order.OrderDTO;

import java.util.List;

public interface OrderApi {
    /**
     * 订单新增
     * @param orderDTO
     */
    void saveOrder(OrderDTO orderDTO) throws BusinessException;

    /**
     * 展示订单表
     * @param orderStatus
     * @param userId
     * @param parentOrderNo
     * @param businessId
     * @return
     */
    List<OrderDetailDTO> showMyOrder(String orderStatus,Integer userId,String parentOrderNo ,Integer businessId) throws BusinessException;

    /**
     * 修改订单状态
     * @param orderStatus
     * @param parentOrderNo
     * @param userId
     * @throws BusinessException
     */
    void updateOrderStatus(String orderStatus, String parentOrderNo,Integer userId) throws BusinessException;

    /**
     * 去提醒
     * @param detailId
     * @param orderStatus
     * @param userId
     * @throws BusinessException
     */
    void toRemind(Integer detailId, String orderStatus,Integer userId) throws BusinessException;

    /**
     * 修改detail表状态
     * @param detailId
     * @param orderStatus
     * @param childOrderNo
     * @param userId
     * @throws BusinessException
     */
    void updateStatusByChildOrderNo(Integer detailId, String orderStatus, String childOrderNo,Integer userId) throws BusinessException;
}
