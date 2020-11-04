package com.dj.mall.order.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.mall.model.base.BusinessException;
import com.dj.mall.model.util.DozerUtil;
import com.dj.mall.model.util.SystemConstant;
import com.dj.mall.order.dto.detail.OrderDetailDTO;
import com.dj.mall.order.dto.info.OrderInfoDTO;
import com.dj.mall.order.dto.order.OrderDTO;
import com.dj.mall.order.entity.detail.OrderDetail;
import com.dj.mall.order.mapper.detail.OrderDetailMapper;
import com.dj.mall.order.service.OrderDetailService;
import com.dj.mall.product.api.sku.ProductSkuService;
import com.dj.mall.product.dto.sku.ProductSkuDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Reference
    private ProductSkuService productSkuService;

    @Autowired
    private RabbitTemplate rabbitTemplate;


    /**
     * 详情表的批量新增
     *
     * @param orderDTO
     * @throws BusinessException
     */
    @Override
    public void saveBatchDetail(OrderDTO orderDTO) throws BusinessException {
        List<OrderDetail> detailList = new ArrayList<>();
        List<ProductSkuDTO> productSkuDTOList = new ArrayList<>();
        for (int i=0;i<orderDTO.getSpuList().size();i++) {
            String freighta=orderDTO.getSpuList().get(i).getFreight();
            BigDecimal freight;
            if (freighta.equals(SystemConstant.PINKAGE)){
                freighta="0.00";
                freight = BigDecimal.valueOf(Double.valueOf(freighta));
            }else {
                String substring = freighta.substring(0, freighta.length() - 1);
                freight = BigDecimal.valueOf(Double.valueOf(substring));
            }
            BigDecimal skuCount = new BigDecimal(orderDTO.getSpuList().get(i).getSkuCount());
            OrderDetail orderDetail = OrderDetail.builder().parentOrderNo(orderDTO.getOrderNo()).buyerId(orderDTO.getBuyerId()).productId(orderDTO.getSpuList().get(i).getProductId())
                    .businessId(orderDTO.getSpuList().get(i).getUserId()).skuId(orderDTO.getSpuList().get(i).getProductSkuId()).skuInfo(orderDTO.getSpuList().get(i).getSkuAttrValueNames()).skuPrice(orderDTO.getSpuList().get(i).getOldPrice()).skuRate(orderDTO.getSpuList().get(i).getSkuRate())
                    .buyCount(orderDTO.getSpuList().get(i).getSkuCount()).payMoney(orderDTO.getSpuList().get(i).getNewPrice().multiply(skuCount).setScale(SystemConstant.DECIMAL_PLACES_2, BigDecimal.ROUND_HALF_EVEN).add(freight)).freightPrice(freight).createTime(new Date()).orderStatus(orderDTO.getOrderStatus()).buyerMessage("无消息处理").build();
            for (OrderInfoDTO infoDTO:orderDTO.getInfoList()) {
                if (orderDTO.getSpuList().get(i).getProductId()==infoDTO.getProductId()){
                    orderDetail.setChildOrderNo(infoDTO.getOrderNo());
                    System.out.println(infoDTO.getProductId());
                }
            }
            detailList.add(orderDetail);
            ProductSkuDTO productSkuDTO = new ProductSkuDTO();
            productSkuDTO.setId(orderDTO.getSpuList().get(i).getProductSkuId());
            productSkuDTO.setSkuCount(orderDTO.getSpuList().get(i).getSkuCount());
            productSkuDTOList.add(productSkuDTO);
        }
        saveBatch(detailList);
        productSkuService.updateSkuCount(productSkuDTOList);
        rabbitTemplate.convertAndSend("dlx-ex","dlx",orderDTO,message -> {
            message.getMessageProperties().setDelay(1800000);
            return message;
        });
    }

}
