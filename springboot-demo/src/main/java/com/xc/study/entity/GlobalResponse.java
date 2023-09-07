package com.xc.study.entity;

import com.xc.study.contants.ResOrExceEnum;

public class GlobalResponse<T> {

    private String code;

    private String msg;

    private boolean success = true;

    private T data;

    public GlobalResponse(String code, String msg) {
        this.code = code;
        this.msg = msg;
        success = ResOrExceEnum.SUCCESS.getCode().equals(this.code);
    }

    public GlobalResponse(ResOrExceEnum resOrExceEnum) {
        this.code = resOrExceEnum.getCode();
        this.msg = resOrExceEnum.getMsg();
        success = ResOrExceEnum.SUCCESS.getCode().equals(this.code);
    }

    public GlobalResponse (T data) {
        this.code = ResOrExceEnum.SUCCESS.getCode();
        this.msg = ResOrExceEnum.SUCCESS.getMsg();
        this.data = data;
    }

    public static <T> GlobalResponse<T> success(T data) {
        return new GlobalResponse<>(data);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
