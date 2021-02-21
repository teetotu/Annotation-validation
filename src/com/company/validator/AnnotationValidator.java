package com.company.validator;

import com.company.annotations.Constrained;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedParameterizedType;
import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Consumer;

public class AnnotationValidator implements Validator {
    @Override
    public Set<ValidationError> validate(Object object) {
        HashSet<ValidationError> validationErrors = new HashSet<>();
        if (!object.getClass().isAnnotationPresent(Constrained.class)) {
            validationErrors.add(new AnnotationValidationError(
                    "Can not validate an object of a class without the @Constrained annotation",
                    object.getClass().getName(),
                    null));
        }
        Consumer<AnnotationContainer.AnnotationActionConsumerArguments> defaultConsumer = (obj) -> {
        };

        Map<Class<?>, Consumer<AnnotationContainer.AnnotationActionConsumerArguments>> annotationActionMap =
                AnnotationContainer.getDefaultAnnotationActionUnmodifiableMap();

        Arrays.stream(object.getClass().getDeclaredFields()).forEach(it -> {
            Arrays.stream(it.getAnnotatedType().getDeclaredAnnotations()).
                    forEach(itt -> {
                        it.setAccessible(true);
                        try {
                            annotationActionMap.
                                    getOrDefault(itt.annotationType(), defaultConsumer)
                                    .accept(new AnnotationContainer.AnnotationActionConsumerArguments(
                                            it.get(object),
                                            validationErrors,
                                            it,
                                            itt
                                    ));
                        } catch (IllegalAccessException e) {
                            System.out.println("Illegal access");
                        }
                    });
        });

        return validationErrors;
    }

    private static void validateR(Field field, Object object, HashSet<ValidationError> validationErrors) {
        Consumer<AnnotationContainer.AnnotationActionConsumerArguments> defaultConsumer = (obj) -> {
        };

        Map<Class<?>, Consumer<AnnotationContainer.AnnotationActionConsumerArguments>> annotationActionMap =
                AnnotationContainer.getDefaultAnnotationActionUnmodifiableMap();
        if (List.class.isAssignableFrom(field.getType()) &&
                field.getAnnotatedType() instanceof AnnotatedParameterizedType) {
            try {
                ((List<?>) field.get(object)).forEach(annotationActionMap.getOrDefault(, defaultConsumer));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}


//if (List.class.isAssignableFrom(it.getType()) &&
//                    it.getAnnotatedType() instanceof AnnotatedParameterizedType) {
//                Arrays.stream(((AnnotatedParameterizedType) it.getAnnotatedType()).getAnnotatedActualTypeArguments())
//                        .forEach(itt -> Arrays.stream(itt.getDeclaredAnnotations())
//                                .forEach(ittt -> {
//                                    it.setAccessible(true);
//                                    try {
//                                        annotationActionMap.
//                                                getOrDefault(ittt.annotationType(), defaultConsumer)
//                                                .accept(new AnnotationContainer.AnnotationActionConsumerArguments(
//                                                        it.get(object),
//                                                        validationErrors,
//                                                        it,
//                                                        ittt
//                                                ));
//                                    } catch (IllegalAccessException e) {
//                                        System.out.println("Illegal access");
//                                    }
//                                }));
//            }