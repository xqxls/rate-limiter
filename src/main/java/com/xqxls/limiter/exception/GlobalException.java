package com.xqxls.limiter.exception;

import com.xqxls.limiter.result.CodeMsg;

/**
 * @author xqxls
 * @create 2023-06-02 14:07
 * @Description
 */
public class GlobalException extends RuntimeException {

    private CodeMsg codeMsg;

    public GlobalException(CodeMsg codeMsg) {
        super(codeMsg.getMsg());
        this.codeMsg = codeMsg;

    }

    public GlobalException(String message) {
        super(message);
    }

    public GlobalException(Throwable cause) {
        super(cause);
    }

    public GlobalException(String message, Throwable cause) {
        super(message, cause);
    }

    public CodeMsg getErrorCode() {
        return codeMsg;
    }
}
