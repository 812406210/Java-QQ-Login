package com.fengxv.platform.utils;

import java.util.UUID;

public class UUIDUtil {
    public static String get() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
