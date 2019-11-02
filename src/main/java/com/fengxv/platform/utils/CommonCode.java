package com.fengxv.platform.utils;

import lombok.ToString;

@ToString
public enum CommonCode {

    SUCCESS(true, 200, "success"),
    FAIL(false, 404, "fail"),
    USER_UNREGISTER(false,410,"用户未注册！"),
    PASSWORD_ERROR(false,411,"密码错误"),



    UNAUTHENTICATED(false, 10001, "此操作需要登陆系统！"),
    UNAUTHORISE(false, 10002, "权限不足，无权操作！"),
    SERVER_ERROR(false, 99999, "抱歉，系统繁忙，请稍后重试！"),
    INVALID_PARAM(false,10003,"非法参数！");





    //操作是否成功
    boolean success;
    //操作代码
    int code;
    //提示信息
    String message;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private CommonCode(boolean success, int code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

}

