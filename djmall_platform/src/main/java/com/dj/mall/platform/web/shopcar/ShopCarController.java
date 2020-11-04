package com.dj.mall.platform.web.shopcar;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.auth.api.shopcar.ShopCarService;
import com.dj.mall.auth.dto.shopcar.ShopCarDTO;
import com.dj.mall.auth.dto.user.UserDTO;
import com.dj.mall.cmpt.api.RedisService.RedisService;
import com.dj.mall.model.base.ResultModel;
import com.dj.mall.model.util.DozerUtil;
import com.dj.mall.model.util.SystemConstant;
import com.dj.mall.platform.vo.shop.ShopCarShow;
import com.dj.mall.platform.vo.shop.ShopCarVoReq;
import com.dj.mall.product.api.sku.ProductSkuService;
import com.dj.mall.product.api.spu.ProductSpuService;
import com.dj.mall.product.dto.spu.ProductSpuDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/shop/car/")
@RestController
public class ShopCarController {

    @Reference
    private ShopCarService shopCarService;

    @Reference
    private RedisService redisService;

    @Reference
    private ProductSkuService productSkuService;

    @Reference
    private ProductSpuService productSpuService;

    @RequestMapping("saveShopCar")
    public ResultModel saveShopCar(ShopCarVoReq shopCarVoReq,String token){
        UserDTO userDTO = redisService.get(SystemConstant.TOKEN + token);
        ShopCarDTO shopCarDTO = shopCarService.findShopCarByProductSkuIdAndUserId(shopCarVoReq.getProductSkuId(),userDTO.getUserId());
        if (null==shopCarDTO){
            shopCarVoReq.setUserId(userDTO.getUserId());
            shopCarService.saveShop(DozerUtil.map(shopCarVoReq, ShopCarDTO.class));
        }else {
            ShopCarDTO shopCarDTO1 = new ShopCarDTO();
            Integer shopCarCount = shopCarDTO.getShopCount()+shopCarVoReq.getShopCount();
            shopCarDTO1.setId(shopCarDTO.getId());
            shopCarDTO1.setShopCount(shopCarCount);
            shopCarService.updateShopCar(shopCarDTO1);
        }
        return new ResultModel().success("已加入购物车");
    }

    /**
     * 修改复选框的选择
     * @param checkedProductSkuIds
     * @param TOKEN
     * @return
     */
    @RequestMapping("checkedTrueAndFalse")
    public ResultModel checkedTrueAndFalse(@RequestParam("productSkuIds")Integer [] productSkuIds,@RequestParam("checkedProductSkuIds")Integer [] checkedProductSkuIds,String TOKEN){
        System.out.println(checkedProductSkuIds);
        UserDTO userDTO = redisService.get(SystemConstant.TOKEN + TOKEN);
        ShopCarDTO shopCarDTO = new ShopCarDTO();
        shopCarDTO.setCheckedProductSkuIds(checkedProductSkuIds);
        shopCarDTO.setProductSkuIds(productSkuIds);
        shopCarDTO.setUserId(userDTO.getUserId());
        shopCarService.updateShopCar(shopCarDTO);
        return new ResultModel().success("选中");
    }

    @RequestMapping("findShopCarByProductSkuIdsAndUserId")
    public ResultModel findShopCarByProductSkuIdsAndUserId(Integer [] productSkuIds,String TOKEN){
        UserDTO userDTO = redisService.get(SystemConstant.TOKEN + TOKEN);
        List<ShopCarDTO> shopCarDTOList = shopCarService.findShopCarByProductSkuIdsAndUserId(productSkuIds, userDTO.getUserId(),SystemConstant.SHOP_CAR_IS_DEL_NO);
        List<Integer> skuIds = new ArrayList<>();
        for (ShopCarDTO shopCarDTO:shopCarDTOList) {
            skuIds.add(shopCarDTO.getProductSkuId());
        }
        List<ProductSpuDTO> productSpuDTOList = productSpuService.findShopByProductSkuIds(skuIds,SystemConstant.NO_DEFAULT);

        List<ShopCarShow> showCarShowList = new ArrayList<>();
        for (ProductSpuDTO proSpu:productSpuDTOList) {
            ShopCarShow shopCarShow = new ShopCarShow();
            shopCarShow.setOldPrice(proSpu.getSkuPrice());
            //转换bigdecimal
            BigDecimal rate = BigDecimal.valueOf(proSpu.getSkuRate());
            //算打折的%比
            BigDecimal dazhe = BigDecimal.valueOf(0.01);
            shopCarShow.setNewPrice(rate.multiply(dazhe).multiply(proSpu.getSkuPrice()).setScale(SystemConstant.DECIMAL_PLACES_2,BigDecimal.ROUND_HALF_EVEN));
            shopCarShow.setFreight(proSpu.getFreight());
            for (ShopCarDTO shopcar:shopCarDTOList) {
                if (proSpu.getProductSkuId().equals(shopcar.getProductSkuId())){
                    shopCarShow.setShopCount(shopcar.getShopCount());
                }
            }
            showCarShowList.add(shopCarShow);
        }
        return new ResultModel().success(showCarShowList);
    }

    @RequestMapping("updateCount")
    public ResultModel updateCount(@RequestParam("shopCarIds") Integer []shopCarIds,@RequestParam("shopCounts")Integer []shopCounts){
        shopCarService.updateShopCarBySkuIds(shopCarIds,shopCounts);
        return new ResultModel().success("");
    }

    @RequestMapping("deleteShopCar")
    public ResultModel deleteShopCar(@RequestParam("productSkuIds") Integer []productSkuIds,String TOKEN){
        UserDTO userDTO = redisService.get(SystemConstant.TOKEN + TOKEN);
        shopCarService.deleteShopCar(productSkuIds,userDTO.getUserId());
        return new ResultModel().success("已取消");
    }

}
