package com.shameyang.friendhub.service;

import com.shameyang.friendhub.model.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.List;


/**
 * @author ShameYang
 * @date 2024/4/1 13:57
 * @description
 */
@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    void testUserRegister() {
        String account = "zhangsan";
        String password = "";
        String checkPwd = "123321";
        long result = userService.userRegister(account, password, checkPwd);
        Assertions.assertEquals(-1, result);
        account = "zs";
        password = "123321";
        result = userService.userRegister(account, password, checkPwd);
        Assertions.assertEquals(-1, result);
        account = "zhangsan";
        password = "1234";
        checkPwd = "1234";
        result = userService.userRegister(account, password, checkPwd);
        Assertions.assertEquals(-1, result);
        account = "zhang@san";
        password = "123456";
        checkPwd = "123456";
        result = userService.userRegister(account, password, checkPwd);
        Assertions.assertEquals(-1, result);
        account = "zhangsan";
        result = userService.userRegister(account, password, checkPwd);
        Assertions.assertTrue(result > 0);
    }

    @Test
    void testSearchUsersByTags() {
        List<String> tagNameList = Arrays.asList("java", "php");
        List<User> userList = userService.searchUsersByTags(tagNameList);
        Assert.notNull(userList, "success");
    }
}