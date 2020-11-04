package com.dj.mall.order.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.mall.model.base.BusinessException;
import com.dj.mall.model.util.DozerUtil;
import com.dj.mall.model.util.PasswordSecurityUtil;
import com.dj.mall.model.util.SystemConstant;
import com.dj.mall.order.dto.info.OrderInfoDTO;
import com.dj.mall.order.dto.order.OrderDTO;
import com.dj.mall.order.entity.info.OrderInfo;
import com.dj.mall.order.mapper.info.OrderInfoMapper;
import com.dj.mall.order.service.OrderDetailService;
import com.dj.mall.order.service.OrderInfoService;
import com.dj.mall.product.dto.spu.ProductSpuDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo> implements OrderInfoService {

    @Autowired
    private OrderDetailService orderDetailApi;

    /**
     * 新增info表数据
     *
     * @param orderDTO
     */
    @Override
    public void saveInfo(OrderDTO orderDTO) throws BusinessException {
        List<OrderInfo> infoList = new ArrayList<>();
        List<Integer> newList = new ArrayList<>();
        for (ProductSpuDTO pro:orderDTO.getSpuList()) {
            newList.add(pro.getProductId());
        }
        Set<Integer> set1 = new HashSet(newList);
        Set<Integer> sameNum = new FindSameNumber().findSameNum(newList);
        for (Integer same:sameNum) {
            double sameSumOldParce=0.00;
            double sameSumNewParce=0.00;
            double sameFreightParce=0.00;
            Integer sameSumCount=0;
            Integer businessId=0;
            for (int i = 0; i < orderDTO.getSpuList().size(); i++) {
                if (orderDTO.getSpuList().get(i).getProductId()==same){
                    String freighta=orderDTO.getSpuList().get(i).getFreight();
                    BigDecimal freight;
                    if (freighta.equals("包邮")){
                        freighta="0.00";
                        freight = BigDecimal.valueOf(Double.valueOf(freighta));
                    }else {
                        String substring = freighta.substring(0, freighta.length() - 1);
                        freight = BigDecimal.valueOf(Double.valueOf(substring));
                    }
                    sameSumOldParce+=Double.valueOf(String.valueOf(orderDTO.getSpuList().get(i).getOldPrice().multiply(new BigDecimal(orderDTO.getSpuList().get(i).getSkuCount()))
                            .add(freight).setScale(SystemConstant.DECIMAL_PLACES_2,BigDecimal.ROUND_HALF_EVEN)));
                    sameSumNewParce+=Double.valueOf(String.valueOf(orderDTO.getSpuList().get(i).getNewPrice().multiply(new BigDecimal(orderDTO.getSpuList().get(i).getSkuCount()))
                            .add(freight).setScale(SystemConstant.DECIMAL_PLACES_2,BigDecimal.ROUND_HALF_EVEN)));
                    sameFreightParce+=Double.valueOf(String.valueOf(freight));
                    sameSumCount+=orderDTO.getSpuList().get(i).getSkuCount();
                    businessId=orderDTO.getSpuList().get(i).getUserId();
                }
            }
            String orderNo="DJ"+new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date())+PasswordSecurityUtil.generateRandom(3);
            OrderInfo orderInfo = OrderInfo.builder().orderNo(orderNo).parentOrderNo(orderDTO.getOrderNo()).buyerId(orderDTO.getBuyerId()).businessId(businessId).productId(same)
                    .totalMoney(new BigDecimal(sameSumOldParce)).totalPayMoney(new BigDecimal(sameSumNewParce)).totalFreight(new BigDecimal(sameFreightParce))
                    .totalBuyCount(sameSumCount).payType(orderDTO.getPayType())
                    .receiverProvince(orderDTO.getOrderReceiverDTO().getReceiverProvince()).receiverCity(orderDTO.getOrderReceiverDTO().getReceiverCity()).receiverCounty(orderDTO.getOrderReceiverDTO().getReceiverCounty())
                    .receiverName(orderDTO.getOrderReceiverDTO().getReceiverName()).receiverPhone(orderDTO.getOrderReceiverDTO().getReceiverPhone()).receiverDetail(orderDTO.getOrderReceiverDTO().getReceiverDetail())
                    .orderStatus(orderDTO.getOrderStatus()).createTime(new Date()).updateTime(new Date()).build();
            infoList.add(orderInfo);
        }
        set1.removeAll(sameNum);
        for (Integer notSame:set1) {
            double notSameSumOldParce=0.00;
            double notSameSumNewParce=0.00;
            double notSameFreightParce=0.00;
            Integer notSameSumCount=0;
            Integer businessId=0;
            for (int i = 0; i < orderDTO.getSpuList().size(); i++) {
                if (orderDTO.getSpuList().get(i).getProductId()==notSame){
                    String freighta=orderDTO.getSpuList().get(i).getFreight();
                    BigDecimal freight;
                    if (freighta.equals("包邮")){
                        freighta="0.00";
                        freight = BigDecimal.valueOf(Double.valueOf(freighta));
                    }else {
                        String substring = freighta.substring(0, freighta.length() - 1);
                        freight = BigDecimal.valueOf(Double.valueOf(substring));
                    }
                    notSameSumOldParce+=Double.valueOf(String.valueOf(orderDTO.getSpuList().get(i).getOldPrice().multiply(new BigDecimal(orderDTO.getSpuList().get(i).getSkuCount()))
                            .add(freight).setScale(SystemConstant.DECIMAL_PLACES_2,BigDecimal.ROUND_HALF_EVEN)));
                    notSameSumNewParce+=Double.valueOf(String.valueOf(orderDTO.getSpuList().get(i).getNewPrice().multiply(new BigDecimal(orderDTO.getSpuList().get(i).getSkuCount()))
                            .add(freight).setScale(SystemConstant.DECIMAL_PLACES_2,BigDecimal.ROUND_HALF_EVEN)));
                    notSameFreightParce+=Double.valueOf(String.valueOf(freight));
                    notSameSumCount+=orderDTO.getSpuList().get(i).getSkuCount();
                    businessId=orderDTO.getSpuList().get(i).getUserId();
                }
            }
            String orderNo="DJ"+new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date())+PasswordSecurityUtil.generateRandom(3);
            OrderInfo orderInfo = OrderInfo.builder().orderNo(orderNo).parentOrderNo(orderDTO.getOrderNo()).buyerId(orderDTO.getBuyerId()).businessId(businessId).productId(notSame)
                    .totalMoney(new BigDecimal(notSameSumOldParce)).totalPayMoney(new BigDecimal(notSameSumNewParce)).totalFreight(new BigDecimal(notSameFreightParce)).totalBuyCount(notSameSumCount).payType(orderDTO.getPayType())
                    .receiverProvince(orderDTO.getOrderReceiverDTO().getReceiverProvince()).receiverCity(orderDTO.getOrderReceiverDTO().getReceiverCity()).receiverCounty(orderDTO.getOrderReceiverDTO().getReceiverCounty())
                    .receiverName(orderDTO.getOrderReceiverDTO().getReceiverName()).receiverPhone(orderDTO.getOrderReceiverDTO().getReceiverPhone()).receiverDetail(orderDTO.getOrderReceiverDTO().getReceiverDetail())
                    .orderStatus(orderDTO.getOrderStatus()).createTime(new Date()).updateTime(new Date()).build();
            infoList.add(orderInfo);
        }
        orderDTO.setInfoList(DozerUtil.mapList(infoList, OrderInfoDTO.class));
        saveBatch(infoList);
        orderDetailApi.saveBatchDetail(orderDTO);

    }
}
