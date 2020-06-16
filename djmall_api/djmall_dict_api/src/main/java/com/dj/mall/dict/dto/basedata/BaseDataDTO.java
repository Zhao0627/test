package com.dj.mall.dict.dto.basedata;
import lombok.Data;

import java.io.Serializable;

/**
 * 基础数据表实体类
 */
@Data
public class BaseDataDTO implements Serializable {

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
