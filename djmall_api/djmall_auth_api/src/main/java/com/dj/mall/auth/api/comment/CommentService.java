package com.dj.mall.auth.api.comment;

import com.dj.mall.auth.dto.comment.CommentDTO;
import com.dj.mall.model.base.BusinessException;

import java.util.List;

public interface CommentService {

    /**
     * 去新增评论表
     * @param commentDTO
     * @throws BusinessException
     */
    void addComment(CommentDTO commentDTO) throws BusinessException;

    /**
     * 展示评论表
     * @param obj
     * @param productId
     * @return
     * @throws BusinessException
     */
    List<CommentDTO> getCommentByProductId(String obj, Integer productId) throws BusinessException;

}
