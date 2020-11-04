package com.dj.mall.admin.web.basedata;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.admin.config.ResourceConstant;
import com.dj.mall.admin.vo.basedata.BaseDataVoReq;
import com.dj.mall.admin.vo.basedata.BaseDataVoResp;
import com.dj.mall.dict.api.basedata.BaseDataService;
import com.dj.mall.dict.dto.basedata.BaseDataDTO;
import com.dj.mall.model.base.ResultModel;
import com.dj.mall.model.util.DozerUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

/**
 * 基础数据库web
 */
@RequestMapping("/dict/")
@RestController
public class BaseDataController {

    /**
     * 注入基础数据接口
     */
    @Reference
    private BaseDataService baseDataService;

    /**
     * 基础数据展示
     * @return
     */
    @GetMapping("base")
    @RequiresPermissions(ResourceConstant.DICT_MANAGER)
    public ResultModel show(){
        return new ResultModel().success(DozerUtil.mapList(baseDataService.getBaseDataAll(), BaseDataVoResp.class));
    }

    /**
     * 新增基础数据
     * @param baseDataVoReq
     * @return
     */
    @PostMapping("base")
    @RequiresPermissions(ResourceConstant.DICT_SAVE_BTN)
    public ResultModel save(BaseDataVoReq baseDataVoReq){
        Assert.hasText(baseDataVoReq.getCode(),"请填写code");
        Assert.hasText(baseDataVoReq.getPCode(),"请填写Pcode");
        Assert.hasText(baseDataVoReq.getName(),"请填写name");
        baseDataService.insertBaseData(DozerUtil.map(baseDataVoReq, BaseDataDTO.class));
        return new ResultModel().success("新增成功");
    }

    /**
     * 修改基础数据
     * @param baseDataVoReq
     * @return
     */
    @PutMapping("base")
    @RequiresPermissions(ResourceConstant.DICT_UPDATE_BTN)
    public ResultModel update(BaseDataVoReq baseDataVoReq){
        baseDataService.updateBaseData(DozerUtil.map(baseDataVoReq,BaseDataDTO.class));
        return new ResultModel().success("修改成功");
    }


}
