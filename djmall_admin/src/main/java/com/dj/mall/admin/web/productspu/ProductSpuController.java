package com.dj.mall.admin.web.productspu;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.admin.config.ResourceConstant;
import com.dj.mall.admin.vo.ProductSpuVoReq;
import com.dj.mall.admin.vo.ProductSpuVoResp;
import com.dj.mall.auth.dto.user.UserDTO;
import com.dj.mall.model.base.ResultModel;
import com.dj.mall.model.util.DozerUtil;
import com.dj.mall.model.util.QiNiuUtils;
import com.dj.mall.model.util.SystemConstant;
import com.dj.mall.product.api.spu.ProductSpuService;
import com.dj.mall.product.dto.spu.ProductSpuDTO;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 关于商品表的控制层
 */
@RestController
@RequestMapping("/product/spu/")
public class ProductSpuController {

    /**
     * 商品接口
     */
    @Reference
    private ProductSpuService productSpuService;

    /**
     * 展示商品
     * @param productSpuVoReq
     * @return
     */
    @RequestMapping("Show")
    @RequiresPermissions(ResourceConstant.RESOURCE_MANAGER)
    public Map<String,Object> Show(ProductSpuVoReq productSpuVoReq){
        List<ProductSpuDTO> spuAll = productSpuService.findSpuAll(DozerUtil.map(productSpuVoReq, ProductSpuDTO.class));
        Map<String,Object> map = new HashMap<>();
        map.put("data",DozerUtil.mapList(spuAll, ProductSpuVoResp.class));
        map.put("code",0);
        return map;
    }

    /**
     * 新增商品
     * @param productSpuVoReq
     * @param session
     * @param file
     * @return
     */
    @RequestMapping("Add")
    @RequiresPermissions(ResourceConstant.PRODUCT_SAVE_BTN)
    public ResultModel Add(ProductSpuVoReq productSpuVoReq, HttpSession session, MultipartFile file){
        UserDTO userDTO = (UserDTO) session.getAttribute("user");
        productSpuVoReq.setUserId(userDTO.getUserId());
        if (file!=null){
            String upload = QiNiuUtils.upload(file);
            productSpuVoReq.setImg(upload);
        }
        productSpuService.insertSpuAndSku(DozerUtil.map(productSpuVoReq,ProductSpuDTO.class));
        return new ResultModel().success("新增成功");
    }

    @RequestMapping("productDown")
    @RequiresPermissions("PRODUCT_STAND_DOWN_BTN")
    public ResultModel productDown(ProductSpuVoReq productSpuVoReq){
        if (productSpuVoReq.getSta().equals(SystemConstant.PRODUCT_DOWN_0)){
            productSpuVoReq.setStatus(SystemConstant.PRODUCT_DOWN);
        }else{
            productSpuVoReq.setStatus(SystemConstant.PRODUCT_UP);
        }
        productSpuService.productDown(DozerUtil.map(productSpuVoReq,ProductSpuDTO.class));
        if (productSpuVoReq.getSta().equals(SystemConstant.PRODUCT_DOWN_0)){
            return new ResultModel().success("下架成功");
        }else{
            return new ResultModel().success("上架成功");
        }
    }

}
