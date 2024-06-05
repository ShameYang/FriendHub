package com.shameyang.friendhub.model.request;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author ShameYang
 * @date 2024/6/4 19:39
 * @description 退出队伍请求体
 */
@Data
public class TeamQuitRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = -4304158840972842954L;

    /**
     * 队伍 id
     */
    private Long id;
}
