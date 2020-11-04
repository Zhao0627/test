package com.dj.mall.admin.vo.comment;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class CommentVoReq implements Serializable {
    /**
     * 评论id
     */
    private Integer commentId;

    /**
     * 评论父级id
     */
    private Integer parentId;

    /**
     * 商品id
     */
    private Integer productId;

    /**
     * 评论人
     */
    private Integer commentPeopleId;

    /**
     * 评论分 星数
     */
    private Integer commentScore;

    /**
     * 评论内容
     */
    private String commentContent;

    /**
     * 评论时间
     */
    private Date commentDate;

    /**
     * 是否删除 0 正常 1 删除
     */
    private Integer isDel;

    /**
     * 评论人名称
     */
    private String commentPeople;

}

