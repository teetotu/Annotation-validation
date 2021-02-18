package com.company.validator;

import java.lang.reflect.Field;
import java.util.List;

public class BaseAnnotationValidatorActions {

    /**
     * @param object List<T>, Set<T>, Map<K, V>, String
     */
    public static void checkSize(Field field, Validator validator) {
        System.out.println(field.getAnnotatedType());
    }
}
