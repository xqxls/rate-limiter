package com.xqxls.limiter.result;

import lombok.Data;

/**
 * @author xqxls
 * @create 2023-06-02 11:20
 * @Description
 */
@Data
public class CodeMsg {
    private int code;
    private String msg;

    public static CodeMsg SUCCESS = new CodeMsg(0, "success");
    public static CodeMsg ACQUIRE_SUCCESS = new CodeMsg(200001, "acquire success!");
    public static CodeMsg UNKNOWN_LIMITER = new CodeMsg(500001, "unknown limiter!");
    public static CodeMsg ACQUIRE_LIMITED = new CodeMsg(500002, "Reject！ Be limited!");

    private CodeMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
