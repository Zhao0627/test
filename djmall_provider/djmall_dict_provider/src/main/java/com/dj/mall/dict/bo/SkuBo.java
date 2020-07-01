package com.dj.mall.dict.bo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.dozer.Mapping;

import java.io.Serializable;

@Data
public class SkuBo implements Serializable {

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

    /* *
     * 商品类型
     */
    private String productShow;

    /* *
     * 属性值
     */
    private String valueList;

}
