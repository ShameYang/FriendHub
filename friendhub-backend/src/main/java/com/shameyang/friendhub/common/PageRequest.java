package com.shameyang.friendhub.common;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author ShameYang
 * @date 2024/5/29 16:24
 * @description 通用分页请求参数
 */
@Data
public class PageRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = -2159891265973958011L;

    /**
     * 页面大小
     */
    protected int pageSize = 10;

    /**
     * 当前是第几页
     */
    protected int pageNum = 1;
}
