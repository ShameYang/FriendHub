package com.shameyang.friendhub.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shameyang.friendhub.model.domain.User;
import javax.servlet.http.HttpServletRequest;

import java.util.List;

/**
* @author shameyang
* @description 针对表【user(用户)】的数据库操作Service
* @createDate 2024-04-01 10:39:53
*/
public interface UserService extends IService<User> {
    /**
     * 用户注册
     * @param userAccount 账号
     * @param password 密码
     * @param checkPwd 确认密码
     * @return 新用户 id
     */
    long userRegister(String userAccount, String password, String checkPwd);

    /**
     * 用户登录
     * @param userAccount 账号
     * @param password 密码
     * @return 脱敏后的用户信息
     */
    User userLogin(String userAccount, String password, HttpServletRequest request);

    /**
     * 用户信息脱敏
     * @param user 原用户
     * @return 脱敏后的用户
     */
    User getHandlerUser(User user);

    /**
     * 用户注销
     * @param request 用户注销请求
     * @return 是否成功 1-成功
     */
    int userLogout(HttpServletRequest request);

    /**
     * 根据标签搜索用户（内存过滤）
     * @param tagNameList 标签列表
     * @return
     */
    List<User> searchUsersByTags(List<String> tagNameList);
}
