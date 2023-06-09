package com.xqxls.limiter.limiters;

/**
 * @author xqxls
 * @create 2023-06-02 11:20
 * @Description 令牌桶限流算法
 */
public class TokenBucketLimiter extends Limiter {

    /** 桶内能装多少令牌 **/
    final int capacity;

    /** 现在桶内令牌数量 **/
    int curTokenNum;

    /** 时间戳 **/
    long lastTime;

    TokenBucketLimiter(int qps) {
        super(qps);
        capacity = qps;
        curTokenNum = 0;
        lastTime = 0;
    }

    @Override
    public synchronized boolean tryAcquire() {
        long now = System.currentTimeMillis();
        int intoToken = (int)((now - lastTime)/1000.0 * capacity);
        lastTime = now;
        if (intoToken + curTokenNum > capacity) {
            // 令牌已放满
            curTokenNum = capacity - 1;
            return true;
        } else if (intoToken + curTokenNum >= 1) {
            // 还有令牌
            curTokenNum = intoToken + curTokenNum - 1;
            return true;
        } else {
            return false;
        }
    }
}
