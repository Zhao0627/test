package com.dj.mall.admin.web.index;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.auth.api.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.servlet.http.HttpSession;

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

	@RequestMapping("toIndex")
	public String toIndex() {
		return "index/index";
	}

	@RequestMapping("toTop")
	public String toTop(HttpSession session, Model model) throws Exception {
		model.addAttribute("user", session.getAttribute("user"));
		return "index/top";
	}

	@RequestMapping("toLeft")
	public String toLeft() {
		return "index/left";
	}

	@RequestMapping("toRight")
	public String toRight() {
		return "index/right";
	}
}
