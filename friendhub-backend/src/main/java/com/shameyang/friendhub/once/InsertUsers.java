package com.shameyang.friendhub.once;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

import com.shameyang.friendhub.mapper.UserMapper;
import com.shameyang.friendhub.model.domain.User;
import com.shameyang.friendhub.service.UserService;
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
//    @Scheduled(initialDelay = 5000,fixedRate = Long.MAX_VALUE)
    public void doInsertUser() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        final int INSERT_NUM = 1000;
        for (int i = 0; i < INSERT_NUM; i++) {
            User user = newMockUser();
            userMapper.insert(user);
        }
        stopWatch.stop();
        System.out.println(stopWatch.getLastTaskTimeMillis());
    }

    @Resource
    private UserService userService;
    //线程设置
    private ExecutorService executorService = new ThreadPoolExecutor(15, 1000, 10000, TimeUnit.MINUTES, new ArrayBlockingQueue<>(10000));

    /**
     * 并发批量插入用户  100000
     */
//    @Scheduled(initialDelay = 5000,fixedRate = Long.MAX_VALUE)
    public void doConcurrencyInsertUser() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        final int INSERT_NUM = 100000;
        int j = 0;
        //批量插入数据的大小
        int batchSize = 5000;
        List<CompletableFuture<Void>> futureList = new ArrayList<>();
        for (int i = 0; i < INSERT_NUM / batchSize; i++) {
            List<User> userList = new ArrayList<>();
            while (true) {
                j++;
                User user = newMockUser();
                userList.add(user);
                if (j % batchSize == 0) {
                    break;
                }
            }
            //异步执行
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                System.out.println("ThreadName：" + Thread.currentThread().getName());
                userService.saveBatch(userList, batchSize);
            }, executorService);
            futureList.add(future);
        }
        CompletableFuture.allOf(futureList.toArray(new CompletableFuture[]{})).join();

        stopWatch.stop();
        System.out.println(stopWatch.getLastTaskTimeMillis());
    }

    /**
     * 创建假用户对象
     * @return MockUser
     */
    private User newMockUser() {
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
        return user;
    }
}
