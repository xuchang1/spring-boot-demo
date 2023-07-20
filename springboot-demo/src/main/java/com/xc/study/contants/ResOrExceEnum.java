package com.xc.study.contants;

public enum ResOrExceEnum {
    SUCCESS("200", "请求成功"),
    ARGS_ERROR("400", "请求参数异常成功")
    ;
    private String code;

    private String msg;

    ResOrExceEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
