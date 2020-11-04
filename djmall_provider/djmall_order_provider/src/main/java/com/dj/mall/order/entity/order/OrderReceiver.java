package com.dj.mall.order.entity.order;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.dozer.Mapping;

@Data
@TableName("djmall_order_receiver")
public class OrderReceiver {

    /**
     * 收货地址id
     */
    @TableId(type = IdType.AUTO)
    @Mapping("receiverId")
    private Integer id;
    /**
     * 收货人名称
     */
    private String receiverName;

    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 收货省
     */
    private String receiverProvince;
    /**
     * 收货市
     */
    private String receiverCity;

    /**
     * 收获区县
     */
    private String receiverCounty;
    /**
     * 详细地址
     */
    private String receiverDetail;
    /**
     *  收获手机号
     */
    private String receiverPhone;

}
