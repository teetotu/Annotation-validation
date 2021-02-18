package com.company.validator;

import com.company.annotations.Constrained;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class AnnotationValidator implements Validator {
    @Override
    public Set<ValidationError> validate(Object object) {
        //if (object instanceof Class<?>) {
        //            if (!((Class<?>) object).isAnnotationPresent(Constrained.class))
        //                throw new IllegalArgumentException(
        //                        "Can't call validate on an object without the @Constrained annotation"
        //                );
        //        } else
        if (!object.getClass().isAnnotationPresent(Constrained.class)) {
            throw new IllegalArgumentException("Can't call validate on an object without the @Constrained annotation");
        }
        Set<ValidationError> validationErrors = new HashSet<>();

//        Arrays.stream(object.getClass().getDeclaredFields())
//                .forEach(it -> {
//                    System.out.println(it);
//                    if (it.getAnnotatedType() instanceof AnnotatedParameterizedType) {
//                        Arrays.stream(
//                                ((AnnotatedParameterizedType) it.getAnnotatedType()).getAnnotatedActualTypeArguments()).
//                                forEach(itt1 -> Arrays.stream(itt1.getDeclaredAnnotations()).
//                                        forEach(itt1it -> System.out.println("<> " + itt1it))
//                                );
//                    }
//
//                    Arrays.stream(it.getAnnotatedType().getDeclaredAnnotations())
//                            .forEach(itt2 -> System.out.println("\t" + itt2));
//                });
        Arrays.stream(object.getClass().getDeclaredFields()).forEach(it -> {
            Arrays.stream(it.getAnnotatedType().getDeclaredAnnotations()).
                    forEach(itt -> {
                        if (AnnotationContainer.getDefaultAnnotationActionUnmodifiableList().containsKey(itt.annotationType().getName())) {
                            AnnotationContainer.getDefaultAnnotationActionUnmodifiableList().get(itt.annotationType().getName()).accept(it, this);
                            System.out.println("!");
                        }
                    });
        });

        return validationErrors;
    }
}