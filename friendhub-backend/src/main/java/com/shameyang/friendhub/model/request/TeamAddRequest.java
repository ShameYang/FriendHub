package com.shameyang.friendhub.model.request;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @author ShameYang
 * @date 2024/5/30 16:37
 * @description 添加队伍请求体
 */
@Data
public class TeamAddRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = -6233064786109617446L;

    /**
     * 队伍名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 最大人数
     */
    private Integer maxNum;

    /**
     * 过期时间
     */
    private Date expireTime;

    /**
     * 创建人 id
     */
    private Long userId;

    /**
     * 0 - 公开，1 - 私有，2 - 加密
     */
    private Integer status;

    /**
     * 密码
     */
    private String password;
}
