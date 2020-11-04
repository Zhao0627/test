package com.dj.mall.platform.web.order.page;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.auth.api.shopcar.ShopCarService;
import com.dj.mall.auth.dto.shopcar.ShopCarDTO;
import com.dj.mall.auth.dto.user.UserDTO;
import com.dj.mall.cmpt.api.RedisService.RedisService;
import com.dj.mall.dict.api.basedata.BaseDataService;
import com.dj.mall.dict.dto.basedata.BaseDataDTO;
import com.dj.mall.model.util.DozerUtil;
import com.dj.mall.model.util.SystemConstant;
import com.dj.mall.order.api.order.OrderApi;
import com.dj.mall.order.api.order.OrderReceiverApi;
import com.dj.mall.order.dto.detail.OrderDetailDTO;
import com.dj.mall.order.dto.order.OrderReceiverDTO;
import com.dj.mall.platform.vo.order.OrderDetailVoResp;
import com.dj.mall.platform.vo.shop.ShopCarShow;
import com.dj.mall.product.api.spu.ProductSpuService;
import com.dj.mall.product.dto.spu.ProductSpuDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/order/")
public class OrderPageController {

    @Reference
    private OrderApi orderApi;
    @Reference
    private RedisService redisService;
    @Reference
    private ShopCarService shopCarService;
    @Reference
    private OrderReceiverApi orderReceiverApi;
    @Reference
    private ProductSpuService productSpuService;
    @Reference
    private BaseDataService baseDataService;

    /**
     * 去新增订单列表
     * @param productSkuIds
     * @param TOKEN
     * @param shopCount
     * @param model
     * @return
     */
    @RequestMapping("toSaveOrder")
    public String toSaveOrder(Integer[] productSkuIds, String TOKEN,Integer[] shopCount, Model model){
        UserDTO user = redisService.get(SystemConstant.TOKEN + TOKEN);
        List<OrderReceiverDTO> orderReceiverList = orderReceiverApi.findorderReceiverByUserId(user.getUserId());//查询收货地址
        List<ShopCarDTO> shopCarList = shopCarService.findShopCarByProductSkuIdsAndUserId(productSkuIds, user.getUserId(),SystemConstant.SHOP_CAR_IS_DEL_NO);//查询购物车信息
        List<Integer> skuIds = new ArrayList<>();
        for (Integer ids:productSkuIds) {
            skuIds.add(ids);
        }
        List<ProductSpuDTO> productSpuDTOList = productSpuService.findShopByProductSkuIds(skuIds,null);
        List<ShopCarShow> shopCarDTOList= new ArrayList<>();
        for (int i=0;i<productSpuDTOList.size();i++) {
            ShopCarShow shopCarShow = new ShopCarShow();
            shopCarShow.setFreight(productSpuDTOList.get(i).getFreight());
            shopCarShow.setOldPrice(productSpuDTOList.get(i).getSkuPrice());
            shopCarShow.setProductName(productSpuDTOList.get(i).getProductName());
            shopCarShow.setSkuRate(productSpuDTOList.get(i).getSkuRate());
            //转换bigdecimal
            BigDecimal rate = BigDecimal.valueOf(productSpuDTOList.get(i).getSkuRate());
            //算打折的%比
            BigDecimal dazhe = BigDecimal.valueOf(0.01);
            shopCarShow.setValueAttrNames(productSpuDTOList.get(i).getSkuAttrValueNames());
            shopCarShow.setNewPrice(rate.multiply(dazhe).multiply(productSpuDTOList.get(i).getSkuPrice()).setScale(SystemConstant.DECIMAL_PLACES_2,BigDecimal.ROUND_HALF_EVEN));
            shopCarShow.setProductSkuId(productSpuDTOList.get(i).getProductSkuId());
            shopCarShow.setUserId(productSpuDTOList.get(i).getUserId());
            shopCarShow.setProductSpuId(productSpuDTOList.get(i).getProductId());
            shopCarShow.setSkuCount(shopCount[i]);
            shopCarDTOList.add(shopCarShow);
        }
        List<BaseDataDTO> basedata = baseDataService.getBasedataByPCode(SystemConstant.PAY_TYPE);
        if (null!=orderReceiverList){
            model.addAttribute("orderReceiver",orderReceiverList);
        }
        model.addAttribute("shopCar",shopCarDTOList);
        model.addAttribute("basedata",basedata);
        return "order/save_order";
    }

    /**
     * 去展示订单列表
     * @return
     */
    @RequestMapping("toMyOrderShow")
    public String toOrderShow(String TOKEN){
        return "order/my_order_show_1";
    }

    /**
     * 去展示订单详情列表
     * @return
     */
    @RequestMapping("toLineItem")
    public String toLineItem(String TOKEN,String parentOrderNo,String orderStatus,
                             Model model,OrderDetailVoResp orderDetailVoResp){
        UserDTO user = redisService.get(SystemConstant.TOKEN + TOKEN);
        //分开的数据
        List<OrderDetailVoResp> orderDetailVoResps = DozerUtil.mapList(orderApi.showMyOrder(orderStatus, user.getUserId(), parentOrderNo,null), OrderDetailVoResp.class);
        //整条数据
        List<OrderDetailVoResp> orderDetailVoResps1 = DozerUtil.mapList(orderApi.showMyOrder(orderStatus, user.getUserId(), null, null), OrderDetailVoResp.class);

        for (OrderDetailVoResp detail:orderDetailVoResps1) {
            if (detail.getCreateTime()!=null ){
                String createTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(detail.getCreateTime());
                detail.setCreateTimeShow(createTime);

            }
            if (detail.getPayTime()!=null){
                String payTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(detail.getPayTime());
                detail.setPayTimeShow(payTime);
            }
        }
        model.addAttribute("OrderDetailList", orderDetailVoResps);
        model.addAttribute("OrderDetail", orderDetailVoResps1);
        return "order/line_item";
    }


}
