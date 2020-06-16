package com.dj.mall.admin.web.resource;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.admin.config.ResourceConstant;
import com.dj.mall.admin.vo.ResourceVoResp;
import com.dj.mall.auth.api.resource.ResourceService;
import com.dj.mall.auth.dto.resource.ResourceDTO;
import com.dj.mall.model.base.ResultModel;
import com.dj.mall.model.util.DozerUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 资源处理类
 */
@RequestMapping("/auth/resource/")
@RestController
public class ResourceController {

    /**
     * 资源接口
     */
    @Reference
    private ResourceService resourceService;

    /**
     * 资源展示
     * @return
     */
    @RequestMapping("show")
    @RequiresPermissions(ResourceConstant.RESOURCE_MANAGER)
    public ResultModel<Object> show() throws Exception{
        return new ResultModel<>().success(resourceService.findAll());
    }

    /**
     * 新增资源
     * @return
     */
    @RequestMapping("saveResource")
    @RequiresPermissions(ResourceConstant.RESOURCE_SAVE_BTN)
    public ResultModel<Object> saveResource(ResourceVoResp resourceVoResp) throws Exception{
        Assert.hasText(resourceVoResp.getResourceName(),"资源名不能为空");
        Assert.hasText(resourceVoResp.getUrl(), "资源路径不能为空");
        Assert.notNull(resourceVoResp.getPId(), "上级节点不能为空");
        Assert.notNull(resourceVoResp.getResourceCode(), "资源编码不能为空");
        Assert.notNull(resourceVoResp.getResourceType(), "资源类型不能为空");
        resourceService.save(DozerUtil.map(resourceVoResp, ResourceDTO.class));
        return new ResultModel<>().success("新增成功");
    }

    /**
     * 修改资源
     * @param resourceVoResp
     * @return
     */
    @RequestMapping("updateResource")
    @RequiresPermissions(ResourceConstant.RESOURCE_UPDATE_BTN)
    public ResultModel<Object> updateResource(ResourceVoResp resourceVoResp) throws Exception{
        resourceService.update(DozerUtil.map(resourceVoResp, ResourceDTO.class));
        return new ResultModel<>().success("修改成功");
    }

    /**
     * 删除父级节点
     *
     * @param resourceVoResp
     */
    @RequestMapping("deleteResource")
    @RequiresPermissions(ResourceConstant.RESOURCE_DELETE_BTN)
    public ResultModel<Object> deleteResource(ResourceVoResp resourceVoResp) throws Exception{
        List<Integer> ids = new ArrayList<Integer>();
        ids.add(resourceVoResp.getResourceId());
        getIds(resourceVoResp.getResourceId(), ids);
        resourceService.delete(ids);
        return new ResultModel<>().success("删除成功");

    }

    /**
     * 查询父级节点
     *
     * @param id
     * @param ids
     */
    public void getIds(Integer id, List<Integer> ids) throws Exception{
        List<ResourceVoResp> resourceVoResp = DozerUtil.mapList(resourceService.findByParentId(id),ResourceVoResp.class);
        if (resourceVoResp != null && resourceVoResp.size() > 0) {
            for (ResourceVoResp resource : resourceVoResp) {
                ids.add(resource.getResourceId());
                getIds(resource.getResourceId(), ids);
            }
        }
    }

}
