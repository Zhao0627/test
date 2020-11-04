package com.dj.mall.auth.provider.comment;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.mall.auth.api.comment.CommentService;
import com.dj.mall.auth.dto.comment.CommentDTO;
import com.dj.mall.auth.entity.comment.Comment;
import com.dj.mall.auth.mapper.comment.CommentMapper;
import com.dj.mall.model.base.BusinessException;
import com.dj.mall.model.util.DozerUtil;
import com.dj.mall.model.util.SystemConstant;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    /**
     * 去新增评论表
     *
     * @param commentDTO
     * @throws BusinessException
     */
    @Override
    public void addComment(CommentDTO commentDTO) throws BusinessException {
        commentDTO.setCommentDate(new Date());
        commentDTO.setParentId(SystemConstant.COMMENT_PARENT_ID);
        commentDTO.setIsDel(SystemConstant.COMMENT_IS_DEL_NO);
        save(DozerUtil.map(commentDTO,Comment.class));
    }

    /**
     * 展示评论表
     *
     * @param obj
     * @param productId
     * @return
     * @throws BusinessException
     */
    @Override
    public List<CommentDTO> getCommentByProductId(String obj, Integer productId) throws BusinessException {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_id",productId);
        if (obj!=null){
            if (obj.equals("gt")){
                //大于
                queryWrapper.gt("comment_score",3);
            }
            if (obj.equals("eq")){
                //等于
                queryWrapper.eq("comment_score",3);
            }
            if (obj.equals("lt")){
                //小于
                queryWrapper.lt("comment_score",3);
            }
        }
        List<Comment> commentList = list(queryWrapper);
        return DozerUtil.mapList(commentList,CommentDTO.class);
    }
}
