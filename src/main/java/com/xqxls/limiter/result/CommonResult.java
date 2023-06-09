package com.xqxls.limiter.result;

/**
 * @author xqxls
 * @create 2023-06-02 11:20
 * @Description
 */
public class CommonResult<T> {
    private int code;
    private String msg;
    private T data;

    private CommonResult(T data) {
        this.code = 0;
        this.msg = "success";
        this.data = data;
    }

    public CommonResult(CodeMsg cm) {
        if (cm == null) {
            return;
        }
        this.code = cm.getCode();
        this.msg = cm.getMsg();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
