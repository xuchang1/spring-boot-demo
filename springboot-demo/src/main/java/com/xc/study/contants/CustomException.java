package com.xc.study.contants;

public class CustomException extends RuntimeException {

    private String code;

    public CustomException(String message) {
        super(message);
    }

    public CustomException(ResOrExceEnum resOrExceEnum) {
        super(resOrExceEnum.getMsg());
        this.code = resOrExceEnum.getCode();
    }

    public String getCode() {
        return code;
    }
}
