package com.dj.mall.platform.web.comment;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.auth.api.comment.CommentService;
import com.dj.mall.auth.dto.comment.CommentDTO;
import com.dj.mall.auth.dto.user.UserDTO;
import com.dj.mall.cmpt.api.RedisService.RedisService;
import com.dj.mall.model.base.ResultModel;
import com.dj.mall.model.util.DozerUtil;
import com.dj.mall.model.util.SystemConstant;
import com.dj.mall.platform.vo.comment.CommentVoReq;
import com.dj.mall.platform.vo.comment.CommentVoResp;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/comment/")
public class CommentController {

    @Reference
    private CommentService commentService;

    @Reference
    private RedisService redisService;

    @RequestMapping("addComment")
    public ResultModel addComment(CommentVoReq commentVoReq,String TOKEN){
        UserDTO user = redisService.get(SystemConstant.TOKEN + TOKEN);
        commentVoReq.setCommentPeopleId(user.getUserId());
        commentVoReq.setCommentPeople(user.getNickName());
        commentService.addComment(DozerUtil.map(commentVoReq, CommentDTO.class));
        return new ResultModel().success("评论已提交");
    }

    @RequestMapping("getCommentByProductId")
    public ResultModel getCommentByProductId(String obj,Integer productId){
        List<CommentDTO> commentByProductId = commentService.getCommentByProductId(obj, productId);
        return new ResultModel().success(DozerUtil.mapList(commentByProductId, CommentVoResp.class));
    }

}
