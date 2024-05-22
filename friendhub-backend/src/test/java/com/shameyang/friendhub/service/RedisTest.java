package com.shameyang.friendhub.service;

import com.shameyang.friendhub.model.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.Resource;

/**
 * @author ShameYang
 * @date 2024/5/22 14:38
 * @description Redis 测试
 */
@SpringBootTest
public class RedisTest {

    @Resource
    private RedisTemplate redisTemplate;

    @Test
    void test() {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        // 增
        valueOperations.set("shameyangString", "shameyang");
        valueOperations.set("shameyangInt", 1);
        valueOperations.set("shameyangDouble", 2.0);
        User user = new User();
        user.setId(1L);
        user.setUserName("shameyang");
        valueOperations.set("shameyangUser", user);

        // 查
        Object shameyang = valueOperations.get("shameyangString");
        Assertions.assertTrue("shameyang".equals((String) shameyang));
        shameyang = valueOperations.get("shameyangInt");
        Assertions.assertTrue(1 == (Integer) shameyang);
        shameyang = valueOperations.get("shameyangDouble");
        Assertions.assertTrue(2.0 == (Double) shameyang);
        System.out.println(valueOperations.get("shameyangUser"));

        // 改
        valueOperations.set("shameyangString", "shameyang123");

        // 删
        redisTemplate.delete("shameyangString");
    }
}
