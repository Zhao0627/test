package com.dj.mall.admin.web.order;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.admin.config.ResourceConstant;
import com.dj.mall.admin.vo.order.OrderDetailVoResp;
import com.dj.mall.auth.dto.user.UserDTO;
import com.dj.mall.model.base.ResultModel;
import com.dj.mall.model.util.DozerUtil;
import com.dj.mall.model.util.SystemConstant;
import com.dj.mall.order.api.order.OrderApi;
import com.dj.mall.order.dto.detail.OrderDetailDTO;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/order/")
@RestController
public class OrderController {

    @Reference
    private OrderApi orderApi;

    @RequestMapping("orderShow")
    @RequiresPermissions(ResourceConstant.ORDER_MANAGER)
    public Map orderShow(HttpSession session){
        UserDTO user = (UserDTO) session.getAttribute("user");
        Map<String,Object> map = new HashMap<>();
        //如果登陆人是管理员
        List<OrderDetailVoResp> orderDetailVoResps=null;
        if (user.getUserLevel()== SystemConstant.ADMIN_ID){
            List<OrderDetailDTO> orderDetailDTOS = orderApi.showMyOrder(null, null, null, null);
            orderDetailVoResps = DozerUtil.mapList(orderDetailDTOS, OrderDetailVoResp.class);
        }
        if (user.getUserLevel().equals(SystemConstant.COMMERCIAL_ID)){
            List<OrderDetailDTO> orderDetailDTOS = orderApi.showMyOrder(null, null, null, user.getUserId());
            orderDetailVoResps = DozerUtil.mapList(orderDetailDTOS, OrderDetailVoResp.class);
        }
        for (OrderDetailVoResp detail:orderDetailVoResps) {
            if (detail.getCreateTime()!=null ){
                String createTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(detail.getCreateTime());
                detail.setCreateTimeShow(createTime);

            }
            if (detail.getPayTime()!=null){
                String payTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(detail.getPayTime());
                detail.setPayTimeShow(payTime);
            }
        }
        map.put("data",orderDetailVoResps);
        map.put("user",user);
        return map;
    }

    @RequestMapping("updateStatusByChildOrderNo")
    @RequiresPermissions(ResourceConstant.ORDER_MANAGER)
    public ResultModel updateStatusByChildOrderNo(Integer detailId,Integer buyerId,String orderStatus,String childOrderNo){
        orderApi.updateStatusByChildOrderNo(detailId,orderStatus,childOrderNo,buyerId);
        if (orderStatus.equals("待收货")){
            orderStatus="已完成";
        }
        if (orderStatus.equals("待发货")){
            orderStatus="已发货";
        }
        return new ResultModel().success(orderStatus);
    }

}
