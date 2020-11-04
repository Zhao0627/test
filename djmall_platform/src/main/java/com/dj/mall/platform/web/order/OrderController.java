package com.dj.mall.platform.web.order;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.auth.api.shopcar.ShopCarService;
import com.dj.mall.auth.dto.user.UserDTO;
import com.dj.mall.cmpt.api.RedisService.RedisService;
import com.dj.mall.model.base.ResultModel;
import com.dj.mall.model.util.DozerUtil;
import com.dj.mall.model.util.SystemConstant;
import com.dj.mall.order.api.order.OrderApi;
import com.dj.mall.order.dto.detail.OrderDetailDTO;
import com.dj.mall.order.dto.order.OrderDTO;
import com.dj.mall.platform.vo.order.OrderDetailVoResp;
import com.dj.mall.platform.vo.order.OrderVoResp;
import com.dj.mall.product.api.sku.ProductSkuService;
import com.dj.mall.product.dto.spu.ProductSpuDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 订单业务层
 */
@RestController
@RequestMapping("/order/")
public class OrderController {

    //订单的接口
    @Reference
    private OrderApi orderApi;

    //redis的接口
    @Reference
    private RedisService redisService;

    //购物车的接口
    @Reference
    private ShopCarService shopCarService;

    //sku表的接口
    @Reference
    private ProductSkuService productSkuService;

    /**
     * 新增订单
     * @param orderVoResp
     * @param TOKEN
     * @return
     */
    @RequestMapping("addOrder")
    public ResultModel addOrder(OrderVoResp orderVoResp,String TOKEN){
        UserDTO user = redisService.get(SystemConstant.TOKEN + TOKEN);
        orderVoResp.setBuyerId(user.getUserId());
        orderApi.saveOrder(DozerUtil.map(orderVoResp, OrderDTO.class));
        List<Integer> skuIds = new ArrayList<>();
        for (ProductSpuDTO pro:orderVoResp.getSpuList()){
            skuIds.add(pro.getProductSkuId());
        }
        /* 修改购物车的状态 */
        shopCarService.updateShopCarBatchById(SystemConstant.SHOP_CAR_IS_DEL_YES,skuIds,user.getUserId(),null);
        return new ResultModel().success("提交订单成功");
    }

    /**
     * 我的订单展示
     * @param orderStatus
     * @param TOKEN
     * @return
     */
    @RequestMapping("myOrderShow")
    public Map myOrderShow(String orderStatus, String TOKEN){
        UserDTO user = redisService.get(SystemConstant.TOKEN + TOKEN);
        if (null==orderStatus){
            orderStatus="待支付";
        }
        Map<String,Object> map = new HashMap<>();
        List<OrderDetailDTO> orderDetailDTOS = orderApi.showMyOrder(orderStatus, user.getUserId(), null, null);
        map.put("data",DozerUtil.mapList(orderDetailDTOS, OrderDetailVoResp.class));
        map.put("code",0);
        return map;
    }

    /**
     * 修改总订单的状态
     */
    @RequestMapping("updateStatusByParentOrderNo")
    public ResultModel updateStatusByParentOrderNo(Integer skuId,String orderStatus,String parentOrderNo,String TOKEN){
        UserDTO user = redisService.get(SystemConstant.TOKEN + TOKEN);
        orderApi.updateOrderStatus(orderStatus,parentOrderNo,user.getUserId());
        return new ResultModel().success(orderStatus);
    }

    /**
     * 修改详情订单的状态
     */
    @RequestMapping("updateStatusByChildOrderNo")
    public ResultModel updateStatusByChildOrderNo(Integer detailId,String orderStatus,String childOrderNo,String TOKEN){
        UserDTO user = redisService.get(SystemConstant.TOKEN + TOKEN);
        orderApi.updateStatusByChildOrderNo(detailId,orderStatus,childOrderNo,user.getUserId());
        if (orderStatus.equals("待收货")){
            orderStatus="已完成";
        }
        if (orderStatus.equals("待发货")){
            orderStatus="已发货";
        }
        return new ResultModel().success(orderStatus);
    }

    /**
     * 去提醒
     */
    @RequestMapping("remind")
    public ResultModel remind(Integer detailId,String orderStatus,String TOKEN){
        UserDTO user = redisService.get(SystemConstant.TOKEN + TOKEN);
        orderApi.toRemind(detailId,orderStatus,user.getUserId());
        return new ResultModel().success("已提醒");
    }

    /**
     * 重新加入购物车
     */
    @RequestMapping("updateShopCarIsDel")
    public ResultModel updateShopCarIsDel(Integer spuId,Integer skuId,String TOKEN){
        UserDTO user = redisService.get(SystemConstant.TOKEN + TOKEN);
        List<Integer> skuIds = new ArrayList<>();
        skuIds.add(skuId);
        /* 修改购物车的状态 */
        shopCarService.updateShopCarBatchById(SystemConstant.SHOP_CAR_IS_DEL_NO,skuIds,user.getUserId(),spuId);
        return new ResultModel().success("已重新加入购物车");
    }



}
