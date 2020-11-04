package com.dj.mall.admin.web.index;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.auth.api.user.UserService;
import com.dj.mall.auth.dto.resource.ResourceDTO;
import com.dj.mall.auth.dto.user.UserDTO;
import com.dj.mall.cmpt.api.RedisService.RedisService;
import com.dj.mall.model.base.ResultModel;
import com.dj.mall.model.util.SystemConstant;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * 三分天下类
 */
@Controller
@RequestMapping("/index/")
public class IndexPageController {

    /**
     * 用户接口
     */
    @Reference
    private UserService userService;

    /**
     * Redis接口
     */
    @Reference
    private RedisService redisService;

	@RequestMapping("toIndex")
	public String toIndex() {
		return "index/index";
	}

	@RequestMapping("toTop")
	public String toTop(HttpSession session, Model model) throws Exception {
        UserDTO user = (UserDTO) session.getAttribute("user");
        model.addAttribute("user",user);
		return "index/top";
	}

	@RequestMapping("toLeft")
	public String toLeft(HttpSession session) {
		return "index/left";
	}

	@RequestMapping("toRight")
	public String toRight() {
		return "index/right";
	}

	@RequestMapping("to403")
	public String to403() {
		return "index/403";
	}

    /**
     * 获取Mean
     * @return
     */
	@RequestMapping("getMean")
    @ResponseBody
    public ResultModel getMean(HttpSession session) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        List<ResourceDTO> resourceList = new ArrayList<>();
        List<ResourceDTO> allRole = redisService.getHash("ALL_ROLE", "role" + user.getUserLevel());
        for (ResourceDTO resource:allRole) {
            if (resource.getResourceType().equals(SystemConstant.MENU_1)){
                resourceList.add(resource);
            };
        }
        return new ResultModel().success(resourceList);
    }
}
