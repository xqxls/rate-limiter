package com.xqxls.limiter.limiters;

/**
 * @author xqxls
 * @create 2023-06-02 11:20
 * @Description
 */
public abstract class Limiter {

    final int qps;

    Limiter(int qps) {
        this.qps = qps;
    }

    /**
     *  获取继续执行的资格（非阻塞）立刻返回成功失败
     * @return
     */
    public abstract boolean tryAcquire();

}
