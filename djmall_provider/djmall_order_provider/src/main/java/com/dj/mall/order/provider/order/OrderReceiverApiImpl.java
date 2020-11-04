package com.dj.mall.order.provider.order;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.mall.model.base.BusinessException;
import com.dj.mall.model.util.DozerUtil;
import com.dj.mall.order.dto.order.OrderReceiverDTO;
import com.dj.mall.order.entity.order.OrderReceiver;
import com.dj.mall.order.mapper.order.OrderReceiverMapper;
import com.dj.mall.order.api.order.OrderReceiverApi;

import java.util.List;

@Service
public class OrderReceiverApiImpl extends ServiceImpl<OrderReceiverMapper, OrderReceiver> implements OrderReceiverApi {

    /**
     * 查询登陆人的所有收货地址相关
     *
     * @param userId
     * @return
     */
    @Override
    public List<OrderReceiverDTO> findorderReceiverByUserId(Integer userId) {
        QueryWrapper<OrderReceiver> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        return DozerUtil.mapList(list(queryWrapper),OrderReceiverDTO.class);
    }

    /**
     * 新增数据到地址表
     *
     * @param orderReceiverDTO
     */
    @Override
    public void saveReceiver(OrderReceiverDTO orderReceiverDTO) {
        save(DozerUtil.map(orderReceiverDTO,OrderReceiver.class));
    }
}
