package com.dj.mall.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ZtreeDataDTO implements Serializable {

    /**
     * 节点id
     */
    private Integer id;

    /**
     * 节点父级id
     */
    private Integer pId;

    /**
     * 节点名称
     */
    private String name;

    /**
     * 判断是否勾选（默认不勾选）
     */
    private boolean checked = false;

    /**
     * 默认不打开
     */
    private boolean open=false;

}
