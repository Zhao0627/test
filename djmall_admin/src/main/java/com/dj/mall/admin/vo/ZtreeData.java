package com.dj.mall.admin.vo;

import com.dj.mall.auth.dto.role.RoleResourceDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ZtreeData implements Serializable {

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

    /**
     * 默认不打开
     */
    private List<RoleResourceDTO> roleResourceDTOList;

}
