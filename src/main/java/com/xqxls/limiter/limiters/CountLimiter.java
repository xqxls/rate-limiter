package com.xqxls.limiter.limiters;

/**
 * @author xqxls
 * @create 2023-06-02 11:20
 * @Description 计数器限流算法
 */
public class CountLimiter extends Limiter {

    /** 时间窗口 **/
    private static final int TIME_WINDOW = 1000;

    /** 计数器 **/
    private int count;

    /** 时间戳 **/
    private long lastTime;

    public CountLimiter(int qps) {
        super(qps);
        count = 0;
        lastTime = 0;
    }

    @Override
    public synchronized boolean tryAcquire() {
        long now = System.currentTimeMillis();
        if (now - lastTime > TIME_WINDOW) {
            // 保证时间戳后三位都是0.（是否这么做不太影响最后实现，但这样更精确）
            lastTime = now>>3<<3;
            count = 1;
            return true;
        } else if (count < qps) {
            count++;
            return true;
        } else {
            return false;
        }
    }
}
