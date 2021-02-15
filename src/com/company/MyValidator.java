package com.company;

import com.company.annotations.Constrained;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

public class MyValidator implements Validator {
    @Override
    public Set<ValidationError> validate(Object object) {
        if (!object.getClass().isAnnotationPresent(Constrained.class)) {
            throw new IllegalArgumentException("Can't call validate on an object without the Constrained annotation");
        }
        Set<ValidationError> validationErrors = new HashSet<>();

        for (Field field : object.getClass().getDeclaredFields()) {
            System.out.println("field.getName() = " + field.getName());
        }

        return validationErrors;
    }
}