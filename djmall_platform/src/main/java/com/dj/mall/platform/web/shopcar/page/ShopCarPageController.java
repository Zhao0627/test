package com.dj.mall.platform.web.shopcar.page;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.auth.api.shopcar.ShopCarService;
import com.dj.mall.auth.dto.shopcar.ShopCarDTO;
import com.dj.mall.auth.dto.user.UserDTO;
import com.dj.mall.cmpt.api.RedisService.RedisService;
import com.dj.mall.model.util.SystemConstant;
import com.dj.mall.platform.vo.shop.ShopCarShow;
import com.dj.mall.product.api.spu.ProductSpuService;
import com.dj.mall.product.dto.spu.ProductSpuDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/shop/car/")
public class ShopCarPageController {

    @Reference
    private ShopCarService shopCarService;

    @Reference
    private ProductSpuService productSpuService;

    @Reference
    private RedisService redisService;

    @RequestMapping("toShopCar")
    public String toShopCar(Model model,String TOKEN){
        UserDTO userDTO = redisService.get(SystemConstant.TOKEN + TOKEN);
        List<ShopCarDTO> shopCarDTOList = shopCarService.findShopCarByUserId(userDTO.getUserId());
        if (shopCarDTOList.size()!=0){
            List<Integer> skuIds = new ArrayList<>();
            for (ShopCarDTO shopCarDTO:shopCarDTOList) {
                skuIds.add(shopCarDTO.getProductSkuId());
            }
            List<ProductSpuDTO> productSpuDTOList = productSpuService.findShopByProductSkuIds(skuIds,null);
            List<ShopCarShow> shopCarShowList = new ArrayList<>();
            for (ProductSpuDTO pro:productSpuDTOList) {
                //展示购物车
                ShopCarShow shopCarShow = new ShopCarShow();

                shopCarShow.setFreight(pro.getFreight());
                shopCarShow.setOldPrice(pro.getSkuPrice());
                shopCarShow.setProductName(pro.getProductName());
                shopCarShow.setSkuRate(pro.getSkuRate());
                //转换bigdecimal
                BigDecimal rate = BigDecimal.valueOf(pro.getSkuRate());
                //算打折的%比
                BigDecimal dazhe = BigDecimal.valueOf(0.01);
                shopCarShow.setValueAttrNames(pro.getSkuAttrValueNames());
                shopCarShow.setNewPrice(rate.multiply(dazhe).multiply(pro.getSkuPrice()).setScale(SystemConstant.DECIMAL_PLACES_2,BigDecimal.ROUND_HALF_EVEN));
                shopCarShow.setProductSkuId(pro.getProductSkuId());
                shopCarShow.setSkuCount(pro.getSkuCount());
                for (ShopCarDTO shop:shopCarDTOList) {
                    if (pro.getProductSkuId().equals(shop.getProductSkuId())){
                        shopCarShow.setShopCarId(shop.getId());
                        shopCarShow.setChecked(shop.isChecked());
                        shopCarShow.setShopCount(shop.getShopCount());
                    }
                }
                shopCarShowList.add(shopCarShow);
            }
            model.addAttribute("shopCarShowList",shopCarShowList);
        }
        return "shopcar/show2";
    }

    @RequestMapping("toShop")
    public String toShop(){
        return "shop/shop";
    }

}
