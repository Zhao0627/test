package com.dj.mall.admin.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class LogisticsVoResp implements Serializable {

    /**
     * 运费表id
     */
    private Integer freightId;
    /**
     * 运费
     */
    private String freight;
    /**
     * 运营公司
     */
    private String logisticsCompany;

    /**
     * 运营公司展示
     */
    private String logisticsCompanyShow;

}
