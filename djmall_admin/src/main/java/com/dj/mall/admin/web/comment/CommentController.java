package com.dj.mall.admin.web.comment;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.admin.vo.comment.CommentVoResp;
import com.dj.mall.auth.api.comment.CommentService;
import com.dj.mall.auth.dto.comment.CommentDTO;
import com.dj.mall.model.base.ResultModel;
import com.dj.mall.model.util.DozerUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/comment/")
public class CommentController {

    @Reference
    private CommentService commentService;

    @RequestMapping("getCommentByProductId")
    public ResultModel getCommentByProductId(String obj,Integer productId){
        List<CommentDTO> commentByProductId = commentService.getCommentByProductId(obj, productId);
        return new ResultModel().success(DozerUtil.mapList(commentByProductId, CommentVoResp.class));
    }

}
