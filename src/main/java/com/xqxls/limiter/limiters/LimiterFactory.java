package com.xqxls.limiter.limiters;

import com.xqxls.limiter.enums.LimiterEnum;
import com.xqxls.limiter.exception.GlobalException;
import com.xqxls.limiter.result.CodeMsg;

/**
 * @author xqxls
 * @create 2023-06-02 11:20
 * @Description
 */
public class LimiterFactory {

    public static Limiter getCountLimiter(LimiterEnum limiterEnum, int qps) {
        switch (limiterEnum) {
            case COUNT_LIMITER:
                return new CountLimiter(qps);
            case LEAKY_BUCKET_LIMITER:
                return new LeakyBucketLimiter(qps);
            case TOKEN_BUCKET_LIMITER:
                return new TokenBucketLimiter(qps);
            default:
                throw new GlobalException(CodeMsg.UNKNOWN_LIMITER);
        }
    }

}
