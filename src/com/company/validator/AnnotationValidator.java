package com.company.validator;

import com.company.annotations.Constrained;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedParameterizedType;
import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Consumer;

public class AnnotationValidator implements Validator {
    static Consumer<AnnotationContainer.AnnotationActionConsumerArguments> defaultConsumer = (obj) -> {
    };

    static Map<Class<? extends Annotation>, Consumer<AnnotationContainer.AnnotationActionConsumerArguments>>
            annotationActionMap = AnnotationContainer.getDefaultAnnotationActionUnmodifiableMap();

    @Override
    public Set<ValidationError> validate(Object object) {
        HashSet<ValidationError> validationErrors = new LinkedHashSet<>();
        validate(object, validationErrors, "");

        return validationErrors;
    }

    private void validate(Object object, HashSet<ValidationError> validationErrors, String path) {
        if (!object.getClass().isAnnotationPresent(Constrained.class)) {
            validationErrors.add(new AnnotationValidationError(
                    "Can not validate an object of a class without the @Constrained annotation",
                    null,
                    null));
        }

        Arrays.stream(object.getClass().getDeclaredFields()).forEach(it ->
                {
                    Arrays.stream(it.getAnnotatedType().getDeclaredAnnotations()).
                            forEach(itt -> {
                                it.setAccessible(true);
                                try {
                                    annotationActionMap.
                                            getOrDefault(itt.annotationType(), defaultConsumer)
                                            .accept(new AnnotationContainer.AnnotationActionConsumerArguments(
                                                    it.get(object),
                                                    validationErrors,
                                                    itt, (path + it.getName())
                                            ));
                                } catch (IllegalAccessException e) {
                                    System.out.println("Illegal access");
                                }
                            });

                    if (it.getType().isAnnotationPresent(Constrained.class)) {
                        try {
                            it.setAccessible(true);
                            validate(it.get(object), validationErrors, path + it.getName() + ".");
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }

                }
        );
    }

    private static Set<ValidationError> validateList(Object object, Field field) {
        HashSet<ValidationError> validationErrors = new LinkedHashSet<>();
        if (field.getAnnotatedType() instanceof AnnotatedParameterizedType &&
                List.class.isAssignableFrom(field.getType())) {
            Arrays.stream(field.getAnnotatedType().getAnnotations()).forEach(System.out::println);
        }

        return validationErrors;
    }
}