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
     * @param loginUser 当前用户
     * @return
     */
    List<User> searchUsersByTags(List<String> tagNameList, User loginUser);

    /**
     * 更新用户信息
     * @param user
     * @param loginUser
     * @return
     */
    Integer updateUser(User user, User loginUser);

    /**
     * 获取当前登录用户信息
     * @return
     */
    User getLoginUser(HttpServletRequest request);

    /**
     * 是否为管理员（根据 request）
     * @param request 请求
     * @return 不是管理员
     */
    boolean isNotAdmin(HttpServletRequest request);

    /**
     * 是否为管理员（根据用户）
     * @param loginUser 当前登录用户
     * @return 不是管理员
     */
    boolean isNotAdmin(User loginUser);

    /**
     * 匹配用户
     * @param num 匹配数
     * @param loginUser 当前登录用户
     * @return 用户列表
     */
    List<User> matchUsers(long num, User loginUser);
}
