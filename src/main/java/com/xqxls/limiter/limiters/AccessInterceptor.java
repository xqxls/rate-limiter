package com.xqxls.limiter.limiters;

import com.xqxls.limiter.enums.LimiterEnum;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author xqxls
 * @create 2023-06-02 11:20
 * @Description
 */
@Service
public class AccessInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod hm = (HandlerMethod)handler;
            AccessLimit accessLimit = hm.getMethodAnnotation(AccessLimit.class);
            if (accessLimit == null) {
                return true;
            }

            int qps = accessLimit.qps();
            LimiterEnum limiterEnum = accessLimit.limiterEnum();

            Limiter limiter = LimiterFactory.getCountLimiter(limiterEnum, qps);
            return limiter.tryAcquire();
        }
        return true;
    }
}
