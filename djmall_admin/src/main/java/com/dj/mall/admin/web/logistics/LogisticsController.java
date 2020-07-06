package com.dj.mall.admin.web.logistics;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.admin.vo.LogisticsVoResp;
import com.dj.mall.dict.api.logistics.LogisticsService;
import com.dj.mall.dict.dto.logistics.LogisticsDTO;
import com.dj.mall.model.base.ResultModel;
import com.dj.mall.model.util.DozerUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/dict/log/")
public class LogisticsController {

    @Reference
    private LogisticsService logisticsService;

    @RequestMapping("save")
    @RequiresPermissions("LOGISTICS_SAVE_BTN")
    public ResultModel save(LogisticsVoResp logisticsVoResp){
        logisticsService.addLog(DozerUtil.map(logisticsVoResp, LogisticsDTO.class));
        return new ResultModel().success("新增成功");
    }

    @RequestMapping("update")
    @RequiresPermissions("LOGISTICS_UPDATE_BTN")
    public ResultModel update(LogisticsVoResp logisticsVoResp){
        logisticsService.UpdateLog(DozerUtil.map(logisticsVoResp, LogisticsDTO.class));
        return new ResultModel().success("修改成功");
    }

}

