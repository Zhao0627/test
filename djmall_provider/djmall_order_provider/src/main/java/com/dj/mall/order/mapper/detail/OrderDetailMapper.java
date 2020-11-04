package com.dj.mall.order.mapper.detail;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dj.mall.order.bo.detail.OrderDetailBo;
import com.dj.mall.order.entity.detail.OrderDetail;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface OrderDetailMapper extends BaseMapper<OrderDetail> {

    List<OrderDetailBo> findMyOrderAll(String orderStatus,Integer userId,String parentOrderNo ,Integer businessId) throws DataAccessException;

}
