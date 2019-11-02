package com.fengxv.platform.utils;

import com.alibaba.fastjson.JSONObject;

public class ActionHelper {
    public ActionHelper() {
    }
    public static JSONObject responseOk() {
        JSONObject result = new JSONObject();
        result.put("success", true);
        return result;
    }

    public static JSONObject responseOk(Object data) {
        JSONObject result = new JSONObject();
        result.put("success", true);
        result.put("data", data);
        return result;
    }

    public static JSONObject responseFailed(String message) {
        JSONObject result = new JSONObject();
        result.put("success", false);
        result.put("errorMessage", message);
        return result;
    }

    public static String getRealCause(Throwable e) {
        while(e.getCause() != null) {
            e = e.getCause();
        }

        return e.getMessage();
    }
}
