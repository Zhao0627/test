package com.dj.mall.dict.entity.attr;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.dozer.Mapping;

@Data
@TableName("djmall_dict_product_attr_value")
public class AttrValue {

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @Mapping("valueId")
    private Integer id;
    /**
     * arrt的Id
     */
    private Integer attrId;

    private String attrValue;


}
