package com.dj.mall.admin.web.basedata.page;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.admin.config.ResourceConstant;
import com.dj.mall.admin.vo.BaseDataVoResp;
import com.dj.mall.dict.api.basedata.BaseDataService;
import com.dj.mall.dict.dto.basedata.BaseDataDTO;
import com.dj.mall.model.base.ResultModel;
import com.dj.mall.model.util.DozerUtil;
import com.dj.mall.model.util.SystemConstant;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/dict/base/")
@Controller
public class BaseDataPageController {

    @Reference
    private BaseDataService baseDataService;

    @RequestMapping("toShow")
    @RequiresPermissions(ResourceConstant.DICT_MANAGER)
    public String toShow(Model model){
        String pCode = SystemConstant.P_CODE;
        List<BaseDataDTO> basedata = baseDataService.getBasedataByPCode(pCode);
        model.addAttribute("basedata",basedata);
        return "base/show";
    }

}
