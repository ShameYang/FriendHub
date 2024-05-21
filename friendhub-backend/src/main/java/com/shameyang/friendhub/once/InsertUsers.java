package com.shameyang.friendhub.once;
import java.util.Date;

import com.shameyang.friendhub.mapper.UserMapper;
import com.shameyang.friendhub.model.domain.User;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import javax.annotation.Resource;

/**
 * @author ShameYang
 * @date 2024/5/21 15:55
 * @description 插入用户数据
 */
@Component
public class InsertUsers {

    @Resource
    UserMapper userMapper;

    /**
     * 循环插入用户
     */
    @Scheduled(initialDelay = 5000,fixedRate = Long.MAX_VALUE)
    public void doInsertUser() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        final int INSERT_NUM = 1000;
        for (int i = 0; i < INSERT_NUM; i++) {
            User user = new User();
            user.setUserName("mockUser");
            user.setUserAccount("shameyang");
            user.setAvatarUrl("https://avatars.githubusercontent.com/u/94451620?v=4");
            user.setGender(0);
            user.setPassword("123456");
            user.setPhone("123123");
            user.setEmail("123@xxx.com");
            user.setUserStatus(0);
            user.setUserRole(0);
            user.setTags("[]");
            userMapper.insert(user);
        }
        stopWatch.stop();
        System.out.println(stopWatch.getLastTaskTimeMillis());
    }
}
