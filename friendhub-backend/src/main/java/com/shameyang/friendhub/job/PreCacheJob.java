package com.shameyang.friendhub.job;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shameyang.friendhub.model.domain.User;
import com.shameyang.friendhub.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author ShameYang
 * @date 2024/6/27 19:43
 * @description 缓存预热
 */
@Component
@Slf4j
public class PreCacheJob {

    @Resource
    private UserService userService;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private RedissonClient redissonClient;

    // 重点用户
    private List<Long> mainUserList = List.of(1L);

    // 每天执行，预热推荐用户
    @Scheduled(cron = "0 05 14 * * *")
    public void cacheRecommendUser() {
        RLock lock = redissonClient.getLock("friendhub:precachejob:docache:lock");
        try {
            // 只有一个线程可以获取到锁
            if (lock.tryLock(0, -1, TimeUnit.MILLISECONDS)) {
                System.out.println("getLock: " + Thread.currentThread().getId());
                //查数据库
                QueryWrapper<User> queryWrapper = new QueryWrapper<>();
                Page<User> userPage = userService.page(new Page<>(1, 20), queryWrapper);
                String redisKey = String.format("friendhub:user:recommend:%s", mainUserList);
                ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
                //写缓存,30s过期
                try {
                    valueOperations.set(redisKey, userPage, 30000, TimeUnit.MILLISECONDS);
                } catch (Exception e) {
                    log.error("redis set key error", e);
                }
            }
        } catch (InterruptedException e) {
            log.error("doCacheRecommendUser error", e);
        } finally {
            // 只能释放自己的锁
            if (lock.isHeldByCurrentThread()) {
                System.out.println("unLock: " + Thread.currentThread().getId());
                lock.unlock();
            }
        }
    }
}
