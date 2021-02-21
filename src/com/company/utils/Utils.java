package com.company.utils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Utils {
    private static final Map<Class<?>, Class<?>> WRAPPER_INT_TYPE_MAP = new HashMap<>(16);

    static {
        WRAPPER_INT_TYPE_MAP.put(int.class, Integer.class);
        WRAPPER_INT_TYPE_MAP.put(byte.class, Byte.class);
        WRAPPER_INT_TYPE_MAP.put(long.class, Long.class);
        WRAPPER_INT_TYPE_MAP.put(short.class, Short.class);
        WRAPPER_INT_TYPE_MAP.put(Integer.class, Integer.class);
        WRAPPER_INT_TYPE_MAP.put(Byte.class, Byte.class);
        WRAPPER_INT_TYPE_MAP.put(Long.class, Long.class);
        WRAPPER_INT_TYPE_MAP.put(Short.class, Short.class);
    }

    public static Map<Class<?>, Class<?>> getWrapperIntTypeMap() {
        return Collections.unmodifiableMap(WRAPPER_INT_TYPE_MAP);
    }
}
