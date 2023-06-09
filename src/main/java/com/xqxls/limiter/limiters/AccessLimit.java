package com.xqxls.limiter.limiters;

import com.xqxls.limiter.enums.LimiterEnum;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author xqxls
 * @create 2023-06-02 11:20
 * @Description
 */
@Retention(RUNTIME)
@Target(METHOD)
public @interface AccessLimit {
    int qps();
    LimiterEnum limiterEnum();
}
