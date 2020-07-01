package com.dj.mall.dict.entity.attr;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.dozer.Mapping;

/**
 * 属性表
 */
@Data
@TableName("djmall_dict_product_attr")
public class Attr {

    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    @Mapping("attrId")
    private Integer id;

    /**
     * 属性名称
     */
    private String attrName;


}
