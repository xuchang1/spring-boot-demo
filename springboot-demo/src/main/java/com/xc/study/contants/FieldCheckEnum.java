package com.xc.study.contants;

public enum FieldCheckEnum {

    NAME("name", "姓名不能为空"),
    AGE("age", "年龄不能为空")
    ;

    private String type;

    private String message;

    FieldCheckEnum(String type, String message) {
        this.type = type;
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return message;
    }
}
