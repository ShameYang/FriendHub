package com.shameyang.friendhub.service;

import com.shameyang.friendhub.utils.AlgorithmUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

/**
 * @author ShameYang
 * @date 2024/6/12 18:52
 * @description 算法工具类测试
 */
@SpringBootTest
public class AlgorithmUtilsTest {

    @Test
    void test() {
        String str1 = "大香蕉";
        String str2 = "大苹果";
        String str3 = "小苹果";
        String str4 = "大香蕉小苹果";
        // 2
        int score1 = AlgorithmUtils.minDistance(str1, str2);
        // 3
        int score2 = AlgorithmUtils.minDistance(str1, str3);
        // 3
        int score3 = AlgorithmUtils.minDistance(str1, str4);
        System.out.println(score1);
        System.out.println(score2);
        System.out.println(score3);
    }

    @Test
    void testCompareTags() {
        List<String> tagList1 = Arrays.asList("Java", "大一", "男");
        List<String> tagList2 = Arrays.asList("Java", "大一", "女");
        List<String> tagList3 = Arrays.asList("Python", "大二", "女");
        // 1
        int score1 = AlgorithmUtils.minDistance(tagList1, tagList2);
        // 3
        int score2 = AlgorithmUtils.minDistance(tagList1, tagList3);
        System.out.println(score1);
        System.out.println(score2);
    }

}
