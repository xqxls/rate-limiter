package com.xqxls.limiter.controller;

import com.xqxls.limiter.enums.LimiterEnum;
import com.xqxls.limiter.limiters.AccessLimit;
import com.xqxls.limiter.result.CodeMsg;
import com.xqxls.limiter.result.CommonResult;
import com.xqxls.limiter.service.AccessLimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author xqxls
 * @create 2023-06-02 11:20
 * @Description
 */
@Controller
public class AccessLimitController {

    @Autowired
    private AccessLimitService accessLimitService;

    @RequestMapping("/counter")
    @ResponseBody
    @AccessLimit(qps = 10, limiterEnum = LimiterEnum.COUNT_LIMITER)
    public CommonResult<CodeMsg> counter() {
        if (accessLimitService.countAcquire()) {
            // （业务逻辑）
            return new CommonResult<>(CodeMsg.ACQUIRE_SUCCESS);
        }
        return new CommonResult<>(CodeMsg.ACQUIRE_LIMITED);
    }

    @RequestMapping("/bucket")
    @ResponseBody
    @AccessLimit(qps = 10, limiterEnum = LimiterEnum.LEAKY_BUCKET_LIMITER)
    public CommonResult<CodeMsg> bucket() {
        if (accessLimitService.budgetLimiterAcquire()) {
            // (业务逻辑)
            return new CommonResult<>(CodeMsg.ACQUIRE_SUCCESS);
        }
        return new CommonResult<>(CodeMsg.ACQUIRE_LIMITED);
    }

    @RequestMapping("/token")
    @ResponseBody
    @AccessLimit(qps = 10, limiterEnum = LimiterEnum.TOKEN_BUCKET_LIMITER)
    public CommonResult<CodeMsg> rateLimiter() {
        if (accessLimitService.rateLimiterAcquire()) {
            // （业务逻辑）
            return new CommonResult<>(CodeMsg.ACQUIRE_SUCCESS);
        }
        return new CommonResult<>(CodeMsg.ACQUIRE_LIMITED);
    }
}
