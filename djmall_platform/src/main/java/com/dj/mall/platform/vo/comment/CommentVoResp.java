package com.dj.mall.platform.vo.comment;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
public class CommentVoResp implements Serializable{
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
    //后台到前台转换（”yyyy-MM-dd hh:mm:ss“）格式
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", timezone = "GMT+8")
    //前台到后台转换Date格式
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
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

