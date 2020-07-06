package com.dj.mall.dict.entity.logistics;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.dozer.Mapping;

import java.io.Serializable;

@Data
@TableName("djmall_dict_product_logistics")
public class Logistics implements Serializable {

    /**
     * 运费id
     */
    @TableId( type = IdType.AUTO)
    @Mapping("freightId")
    private Integer id;

    /**
     * 运费
     */
    private String freight;

    /**
     * 物流公司
     */
    private String logisticsCompany;

}
