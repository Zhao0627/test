package com.dj.mall.task.dlx;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.model.util.SystemConstant;
import com.dj.mall.order.api.order.OrderApi;
import com.dj.mall.order.dto.detail.OrderDetailDTO;
import com.dj.mall.order.dto.order.OrderDTO;
import com.dj.mall.product.api.sku.ProductSkuService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 死信-消费者
 */
@Component
public class DLXConsumer {

    @Reference
    private ProductSkuService productSkuService;

    @Reference
    private OrderApi orderApi;

    /**
     * 消费者
     *
     * @param orderDTO 消息体
     * @throws Exception
     */
    @RabbitHandler
    @RabbitListener(queues = "dlx")
    public void process1(OrderDTO orderDTO) throws Exception {
        List<OrderDetailDTO> orderDetailDTOS = orderApi.showMyOrder(SystemConstant.ORDER_STATUS_PAY, null, orderDTO.getOrderNo(), null);
        if (orderDTO.getOrderStatus().equals(SystemConstant.ORDER_STATUS_PAY)){
            orderApi.updateOrderStatus(SystemConstant.ORDER_STATUS_CANCEL,orderDTO.getOrderNo(),orderDTO.getBuyerId());
        };
    }

}
