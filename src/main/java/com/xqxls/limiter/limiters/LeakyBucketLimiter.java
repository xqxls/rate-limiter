package com.xqxls.limiter.limiters;

/**
 * @author xqxls
 * @create 2023-06-02 11:20
 * @Description 漏桶限流算法
 */
public class LeakyBucketLimiter extends Limiter {

    /** 水桶容量, 一秒流光 **/
    private final long capacity;

    /** 目前水桶剩下的水量 **/
    private double remainWater;

    /** 时间戳 **/
    private long lastTime;

    LeakyBucketLimiter(int qps) {
        super(qps);
        capacity = qps;
        remainWater = capacity;
        lastTime = 0;
    }

    @Override
    public synchronized boolean tryAcquire() {
        long now = System.currentTimeMillis();
        // 计算这段时间匀速流出的水
        double outWater = ((now - lastTime)/1000.0)*capacity;
        lastTime = now;
        if (outWater > remainWater) {
            // 请求已全部处理完毕
            remainWater = 1;
            return true;
        } else {
            // 还有未处理的请求
            remainWater -= outWater;
            if (remainWater + 1 <= capacity) {
                remainWater += 1;
                return true;
            } else {
                return false;
            }
        }
    }
}
