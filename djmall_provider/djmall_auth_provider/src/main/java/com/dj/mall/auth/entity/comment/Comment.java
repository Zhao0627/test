package com.dj.mall.auth.entity.comment;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.dozer.Mapping;

import java.util.Date;

@Data
@TableName("djmall_comment")
public class Comment {
    /**
     * 评论id
     */
    @TableId(type = IdType.AUTO)
    @Mapping("commentId")
    private Integer id;

    /**
     * 评论父级id
     */
    private Integer parentId;

    /**
     * 商品id
     */
    private Integer productId;

    /**
     * 评论人id
     */
    private Integer commentPeopleId;

    /**
     * 评论人
     */
    private String commentPeople;

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


}

