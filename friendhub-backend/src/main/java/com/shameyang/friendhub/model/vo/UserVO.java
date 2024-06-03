package com.shameyang.friendhub.model.vo;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @author ShameYang
 * @date 2024/5/31 16:12
 * @description 用户包装类（脱敏）
 */
@Data
public class UserVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 5967940128739661598L;
    /**
     * id
     */
    private Long id;

    /**
     * 昵称
     */
    private String userName;

    /**
     * 账号
     */
    private String userAccount;

    /**
     * 头像
     */
    private String avatarUrl;

    /**
     * 性别 0-男 1-女
     */
    private Integer gender;

    /**
     * 电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 用户状态 0-正常
     */
    private Integer userStatus;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 角色权限 0-普通用户 1-管理员
     */
    private Integer userRole;
}
