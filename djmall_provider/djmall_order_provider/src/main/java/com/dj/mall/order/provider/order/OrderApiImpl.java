package com.dj.mall.order.provider.order;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.mall.model.base.BusinessException;
import com.dj.mall.model.util.DozerUtil;
import com.dj.mall.model.util.PasswordSecurityUtil;
import com.dj.mall.order.api.order.OrderApi;
import com.dj.mall.order.dto.detail.OrderDetailDTO;
import com.dj.mall.order.dto.order.OrderDTO;
import com.dj.mall.order.dto.order.OrderReceiverDTO;
import com.dj.mall.order.entity.detail.OrderDetail;
import com.dj.mall.order.entity.info.OrderInfo;
import com.dj.mall.order.entity.order.Order;
import com.dj.mall.order.entity.order.OrderReceiver;
import com.dj.mall.order.mapper.detail.OrderDetailMapper;
import com.dj.mall.order.mapper.order.OrderMapper;
import com.dj.mall.order.mapper.order.OrderReceiverMapper;
import com.dj.mall.order.service.OrderDetailService;
import com.dj.mall.order.service.OrderInfoService;
import com.dj.mall.product.api.sku.ProductSkuService;
import com.dj.mall.product.dto.sku.ProductSkuDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class OrderApiImpl extends ServiceImpl<OrderMapper, Order> implements OrderApi {

    @Autowired
    private OrderReceiverMapper orderReceiverMapper;

    @Autowired
    private OrderInfoService orderInfoService;

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private OrderMapper orderMapper;

    /**
     * 订单新增
     *
     * @param orderDTO
     */
    @Override
    public void saveOrder(OrderDTO orderDTO) throws BusinessException {
        OrderReceiver orderReceiver = orderReceiverMapper.selectById(orderDTO.getReceiverId());
        String time = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        String orderNo="DJ"+time+ PasswordSecurityUtil.generateRandom(3);
        Order order = Order.builder().orderNo(orderNo).buyerId(orderDTO.getBuyerId()).totalMoney(orderDTO.getTotalMoney()).totalPayMoney(orderDTO.getTotalPayMoney())
                .totalFreight(orderDTO.getTotalFreight()).totalBuyCount(orderDTO.getTotalBuyCount()).payType(orderDTO.getPayType())
                .receiverProvince(orderReceiver.getReceiverProvince()).receiverCity(orderReceiver.getReceiverCity()).receiverCounty(orderReceiver.getReceiverCounty())
                .receiverName(orderReceiver.getReceiverName()).receiverPhone(orderReceiver.getReceiverPhone()).receiverDetail(orderReceiver.getReceiverDetail())
                .orderStatus(orderDTO.getOrderStatus()).createTime(new Date()).updateTime(new Date()).build();
        save(order);
        orderDTO.setOrderNo(orderNo);
        orderDTO.setOrderReceiverDTO(DozerUtil.map(orderReceiver, OrderReceiverDTO.class));
        orderInfoService.saveInfo(orderDTO);

    }

    /**
     * 展示订单表
     *
     * @param orderStatus
     * @param userId
     * @param parentOrderNo
     * @param businessId
     * @return
     */
    @Override
    public List<OrderDetailDTO> showMyOrder(String orderStatus,Integer userId,String parentOrderNo ,Integer businessId) {
        return DozerUtil.mapList(orderDetailMapper.findMyOrderAll(orderStatus,userId,parentOrderNo,businessId),OrderDetailDTO.class);
    }

    /**
     * 修改订单状态
     *
     * @param orderStatus
     * @param parentOrderNo
     * @param userId
     * @throws BusinessException
     */
    @Override
    public void updateOrderStatus(String orderStatus, String parentOrderNo,Integer userId) throws BusinessException {
        getBaseMapper().updateOrderStatus(orderStatus,parentOrderNo,userId);
    }

    /**
     * 去提醒
     *
     * @param detailId
     * @param orderStatus
     * @param userId
     * @throws BusinessException
     */
    @Override
    public void toRemind(Integer detailId, String orderStatus,Integer userId) throws BusinessException {
        OrderDetail build = OrderDetail.builder().id(detailId).orderStatus(orderStatus).buyerId(userId).buyerMessage("买家提醒赶紧发货了").build();
        orderDetailService.updateById(build);
    }

    /**
     * 修改detail表状态
     *
     * @param detailId
     * @param orderStatus
     * @param childOrderNo
     * @param userId
     * @throws BusinessException
     */
    @Override
    public void updateStatusByChildOrderNo(Integer detailId, String orderStatus, String childOrderNo,Integer userId) throws BusinessException {
        //在修改orderStatus之前查询detail表该orderStatus的数据
        QueryWrapper<OrderDetail> detailQueryWrapper = new QueryWrapper<>();
        detailQueryWrapper.eq("child_order_no",childOrderNo).eq("order_status",orderStatus).eq("buyer_id",userId);
        //在修改orderStatus之前查询info表该orderStatus的数据
        QueryWrapper<OrderInfo> infoQueryWrapper = new QueryWrapper<>();
        infoQueryWrapper.eq("order_no", childOrderNo).eq("buyer_id", userId);
        OrderInfo orderInfo = orderInfoService.getOne(infoQueryWrapper);
        QueryWrapper<OrderInfo> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("parent_order_no",orderInfo.getParentOrderNo()).eq("buyer_id",userId).eq("order_status",orderStatus);
        if (orderStatus.equals("待收货")){
            orderStatus="已完成";
        }
        if (orderStatus.equals("待发货")){
            orderStatus="待收货";
        }
        OrderDetail orderDetail = OrderDetail.builder().id(detailId).orderStatus(orderStatus).build();
        orderDetailService.updateById(orderDetail);
        if (orderDetailService.list(detailQueryWrapper).size()==0){
            orderInfo.setUpdateTime(new Date());
            orderInfo.setOrderStatus(orderStatus);
            orderInfoService.update(orderInfo,infoQueryWrapper);
            List<OrderInfo> list = orderInfoService.list(queryWrapper1);
            if (list.size()==0){
                QueryWrapper<Order> orderQueryWrapper = new QueryWrapper<>();
                orderQueryWrapper.eq("order_no",orderInfo.getParentOrderNo()).eq("buyer_id",userId);
                Order order = getOne(orderQueryWrapper);
                order.setOrderStatus(orderStatus);
                update(order,orderQueryWrapper);
            }
        }

    }
}
