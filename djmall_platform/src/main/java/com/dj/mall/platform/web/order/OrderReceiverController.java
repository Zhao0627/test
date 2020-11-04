package com.dj.mall.platform.web.order;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.auth.dto.user.UserDTO;
import com.dj.mall.cmpt.api.RedisService.RedisService;
import com.dj.mall.model.base.ResultModel;
import com.dj.mall.model.util.DozerUtil;
import com.dj.mall.model.util.SystemConstant;
import com.dj.mall.order.api.order.OrderReceiverApi;
import com.dj.mall.order.dto.order.OrderReceiverDTO;
import com.dj.mall.platform.vo.order.OrderReceiverVoReq;
import com.dj.mall.platform.vo.order.OrderReceiverVoResp;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/order/receiver/")
public class OrderReceiverController {

    @Reference
    private OrderReceiverApi receiverApi;

    @Reference
    private RedisService redisService;

    @RequestMapping("siteShow")
    public Map siteShow(Integer userId){
        Map<String,Object> map = new HashMap<>();
        map.put("data",DozerUtil.mapList(receiverApi.findorderReceiverByUserId(userId), OrderReceiverVoResp.class));
        map.put("code",0);
        return map;
    }

    @RequestMapping("addSite")
    public ResultModel addSite(OrderReceiverVoReq orderReceiverVoReq,String TOKEN){
        UserDTO user = redisService.get(SystemConstant.TOKEN + TOKEN);
        orderReceiverVoReq.setUserId(user.getUserId());
        receiverApi.saveReceiver(DozerUtil.map(orderReceiverVoReq,OrderReceiverDTO.class));
        return new ResultModel().success("提交成功");
    }

}
