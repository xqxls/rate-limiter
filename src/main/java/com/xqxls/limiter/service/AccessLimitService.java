package com.xqxls.limiter.service;

import com.xqxls.limiter.enums.LimiterEnum;
import com.xqxls.limiter.limiters.Limiter;
import com.xqxls.limiter.limiters.LimiterFactory;
import org.springframework.stereotype.Service;

/**
 * @author xqxls
 * @create 2023-06-02 11:20
 * @Description 提供限流服务（可选三种限流算法）默认qps一秒10个请求
 */
@Service
public class AccessLimitService {

    /** 计数器实例 **/
    private Limiter countLimiter = LimiterFactory.getCountLimiter(LimiterEnum.COUNT_LIMITER, 3);
    /** 漏桶实例 **/
    private Limiter leakyBucketLimiter = LimiterFactory.getCountLimiter(LimiterEnum.LEAKY_BUCKET_LIMITER, 3);
    /** 令牌桶实例 **/
    private Limiter rateLimiter = LimiterFactory.getCountLimiter(LimiterEnum.TOKEN_BUCKET_LIMITER, 3);

    /**
     * 谷歌开源包：令牌桶算法
     * @return
     */
    public boolean rateLimiterAcquire() {
        return rateLimiter.tryAcquire();
    }

    /**
     * 漏桶算法
     * @return
     */
    public boolean budgetLimiterAcquire() {
        return leakyBucketLimiter.tryAcquire();
    }

    /**
     * 计数器算法
     * @return
     */
    public boolean countAcquire() {
        return countLimiter.tryAcquire();
    }
}
