package com.shameyang.friendhub.service;

import org.junit.jupiter.api.Test;
import org.redisson.api.RList;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ShameYang
 * @date 2024/6/27 18:57
 * @description Redisson 测试
 */
@SpringBootTest
public class RedissonTest {

    @Resource
    private RedissonClient redissonClient;

    @Test
    void test() {
        // list
        RList<String> rList = redissonClient.getList("test-list");
        rList.add("shameyang");
        System.out.println("rList: " + rList.get(0));
        rList.remove(0);

        // map
        RMap<String, Integer> rMap = redissonClient.getMap("test-map");
        rMap.put("yang", 10);
        System.out.println("rMap: " + rMap.get("yang"));
        rMap.remove("yang");

    }
}
