package com.dj.mall.dict.entity.sku;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.dozer.Mapping;

import java.io.Serializable;

@Data
@TableName("djmall_dict_product_sku_gm")
public class Sku implements Serializable {
    /**
     * skuid
     */
    @TableId( type = IdType.AUTO)
    @Mapping("skuId")
    private Integer id;
    /**
     * 商品类型
     */
    private String productType;

    /**
     * 属性值id
     */
    private Integer attrId;

}

