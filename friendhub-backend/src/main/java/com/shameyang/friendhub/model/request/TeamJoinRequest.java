package com.shameyang.friendhub.model.request;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author ShameYang
 * @date 2024/6/4 15:51
 * @description 加入队伍请求体
 */
@Data
public class TeamJoinRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = -747257118482139227L;

    /**
     * id
     */
    private Long id;

    /**
     * 密码
     */
    private String password;
}
