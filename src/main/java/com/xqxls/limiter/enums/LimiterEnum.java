package com.xqxls.limiter.enums;

/**
 * @author xqxls
 * @create 2023-06-02 11:20
 * @Description
 */
public enum LimiterEnum {

    /** 计数器限流 **/
    COUNT_LIMITER,

    /** 漏桶限流 **/
    LEAKY_BUCKET_LIMITER,

    /** 令牌桶限流 **/
    TOKEN_BUCKET_LIMITER,

    RATE_LIMITER
}
