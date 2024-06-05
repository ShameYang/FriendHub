package com.shameyang.friendhub.model.request;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author ShameYang
 * @date 2024/6/5 16:32
 * @description 解散队伍请求体
 */
@Data
public class TeamDeleteRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 21696262861978219L;

    /**
     * 队伍 id
     */
    private Long id;
}
