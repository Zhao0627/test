package com.dj.mall.product.bo.spu;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.dj.mall.product.dto.sku.ProductSkuDTO;
import lombok.Data;
import org.dozer.Mapping;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductSpuBo implements Serializable {

    private Integer productId;
    /**
     * 商品名称
     */
    private String productName;
    /**
     * 运费表ID
     */
    private Integer freightId;
    /**
     * 商品描述
     */
    private String productDescribe;
    /**
     * 商品照片
     */
    private String img;
    /**
     * 商品类型（字典code）
     */
    private String type;
    /**
     * 新增人ID（用于展示判断）
     */
    private Integer userId;
    /**
     * SKU状态[0下架,1上架]
     */
    private String status;
    /**
     * 订单量
     */
    private Integer orderNumber;
    /**
     * 点赞量
     */
    private Integer praise;
    /**
     * 类型名称
     */
    private String name;

    /**
     * 上架人的名字
     */
    private String userName;

    /**
     * sku的list集合
     */
    private List<ProductSkuDTO> SkuList;

    /**
     * freight展示
     */
    private String  freight;

    /**
     * sku表数据
     */
    private ProductSkuDTO productSkuDTO;

    /**
     * sku价格
     */
    private BigDecimal skuPrice;
    /**
     * sku库存
     */
    private Integer skuCount;

    /**
     * SKU折扣,0表示无折扣
     */
    private Integer skuRate;

    /**
     * sku表的id
     */
    private Integer productSkuId;

    /**
     * sku表的属性值
     */
    private String skuAttrValueNames;

}
