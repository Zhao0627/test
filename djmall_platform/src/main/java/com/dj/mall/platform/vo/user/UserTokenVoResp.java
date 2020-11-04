package com.dj.mall.platform.vo.user;

import lombok.Data;
import java.io.Serializable;

@Data
public class UserTokenVoResp implements Serializable {

    /**
     * user表用户名
     */
    private String userName;

    /**
     * user表昵称
     */
    private String nickName;

    /**
     * token
     */
    private String token;

    /**
     * 用户id
     */
    private Integer userId;

}
