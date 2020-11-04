package com.dj.mall.admin.web.logistics.page;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.admin.vo.logistics.LogisticsVoReq;
import com.dj.mall.dict.api.basedata.BaseDataService;
import com.dj.mall.dict.api.logistics.LogisticsService;
import com.dj.mall.dict.dto.basedata.BaseDataDTO;
import com.dj.mall.model.util.DozerUtil;
import com.dj.mall.model.util.SystemConstant;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/dict/log/")
public class LogisticsPageController {

    @Reference
    private LogisticsService logisticsService;

    @Reference
    private BaseDataService baseDataService;

    @RequestMapping("toShow")
    @RequiresPermissions("LOGISTICS_MANAGER")
    public String toShow(Model model){
        List<LogisticsVoReq> logisticsVoReqs = DozerUtil.mapList(logisticsService.findLogAll(), LogisticsVoReq.class);
        List<BaseDataDTO> logistics = baseDataService.getBasedataByPCode(SystemConstant.LOGISTICS_COMPANY);
        model.addAttribute("logs",logisticsVoReqs);
        model.addAttribute("logistics",logistics);
        return "logistics/show";
    }

    @RequestMapping("toUpdate")
    @RequiresPermissions("LOGISTICS_UPDATE_BTN")
    public String toUpdate(String freightId, Model model){
        LogisticsVoReq logisticsVoReq = DozerUtil.map(logisticsService.findLogByFreightId(freightId), LogisticsVoReq.class);
        model.addAttribute("logs",logisticsVoReq);
        return "logistics/update";
    }

}
