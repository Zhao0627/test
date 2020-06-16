package com.dj.mall.dict.entity.basedata;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 资源实体类
 */
@Data
@TableName("djmall_auth_base_data")
public class BaseData implements Serializable {

    /**
     * 基础数据表code
     */
    private String code;

    /**
     * 基础数据表名称
     */
    private String name;

    /**
     * 基础数据表父级code
     */
    private String pCode;


}
