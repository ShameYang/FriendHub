package com.shameyang.friendhub.model.request;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @author ShameYang
 * @date 2024/6/3 17:12
 * @description 修改队伍请求体
 */
@Data
public class TeamUpdateRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 6469989677603030681L;

    /**
     * id
     */
    private Long id;

    /**
     * 队伍名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 过期时间
     */
    private Date expireTime;

    /**
     * 0 - 公开，1 - 私有，2 - 加密
     */
    private Integer status;

    /**
     * 密码
     */
    private String password;
}
