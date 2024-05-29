package com.shameyang.friendhub.model.dto;

import com.shameyang.friendhub.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author ShameYang
 * @date 2024/5/29 16:03
 * @description 用户查询封装类
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TeamQuery extends PageRequest {
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
     * 最大人数
     */
    private Integer maxNum;

    /**
     * 创建人id
     */
    private Long userId;

    /**
     * 0-公开，1-私有，2-加密
     */
    private Integer status;
}
