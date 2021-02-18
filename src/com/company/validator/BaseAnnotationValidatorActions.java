package com.company.validator;

import java.lang.reflect.Field;
import java.util.List;

public class BaseAnnotationValidatorActions {
    /**
     * @param field List, ...
     * @param validator Validator object to use in-case of error
     */
    public static void checkSize(Field field, Validator validator) {
        System.out.println("field.");
    }
}
