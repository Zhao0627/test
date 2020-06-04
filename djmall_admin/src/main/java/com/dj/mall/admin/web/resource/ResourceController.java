package com.dj.mall.admin.web.resource;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.admin.vo.ResourceVoResp;
import com.dj.mall.auth.api.resource.ResourceService;
import com.dj.mall.auth.dto.resource.ResourceDTO;
import com.dj.mall.model.base.ResultModel;
import com.dj.mall.model.util.DozerUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/auth/resource/")
@RestController
public class ResourceController {

    @Reference
    private ResourceService resourceService;

    /**
     * 资源展示
     * @return
     */
    @RequestMapping("show")
    public ResultModel<Object> show(){
        return new ResultModel<>().success(resourceService.findAll());
    }

    /**
     * 新增资源
     * @return
     */
    @RequestMapping("saveResource")
    public ResultModel<Object> saveResource(ResourceVoResp resourceVoResp){
        resourceService.save(DozerUtil.map(resourceVoResp, ResourceDTO.class));
        return new ResultModel<>().success("新增成功");
    }

    @RequestMapping("updateResource")
    public ResultModel<Object> updateResource(ResourceVoResp resourceVoResp){
        resourceService.update(DozerUtil.map(resourceVoResp, ResourceDTO.class));
        return new ResultModel<>().success("修改成功");
    }

    /**
     * 删除父级节点
     *
     * @param resourceVoResp
     */
    @RequestMapping("deleteResource")
    public ResultModel<Object> deleteResource(ResourceVoResp resourceVoResp) {
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
    public void getIds(Integer id, List<Integer> ids) {
        List<ResourceVoResp> resourceVoResp = DozerUtil.mapList(resourceService.findByParentId(id),ResourceVoResp.class);
        if (resourceVoResp != null && resourceVoResp.size() > 0) {
            for (ResourceVoResp resource : resourceVoResp) {
                ids.add(resource.getResourceId());
                getIds(resource.getResourceId(), ids);
            }
        }
    }

}
