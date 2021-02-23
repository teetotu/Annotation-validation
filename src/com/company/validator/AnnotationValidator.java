package com.company.validator;

import com.company.annotations.Constrained;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedParameterizedType;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Consumer;

public class AnnotationValidator implements Validator {
    static Consumer<AnnotationContainer.AnnotationActionConsumerArguments> defaultConsumer = (obj) -> {
    };

    static Map<Class<? extends Annotation>, Consumer<AnnotationContainer.AnnotationActionConsumerArguments>>
            annotationActionMap = AnnotationContainer.getDefaultAnnotationActionUnmodifiableMap();

    /**
     * @param object Object to validate
     * @return HashSet of validation errors
     */
    @Override
    public Set<ValidationError> validate(Object object) {
        HashSet<ValidationError> validationErrors = new LinkedHashSet<>();
        validate(object, validationErrors, "");

        return validationErrors;
    }

    private static void validate(Object object, HashSet<ValidationError> validationErrors, String path) {
        if (object == null) return;
        if (!object.getClass().isAnnotationPresent(Constrained.class)) {
            validationErrors.add(new AnnotationValidationError(
                    "Can not validate an object of a class without the @Constrained annotation",
                    null,
                    null));
        }

        for (Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                Object value = field.get(object);
                for (Annotation annotation : field.getAnnotatedType().getDeclaredAnnotations()) {
                    annotationActionMap.
                            getOrDefault(annotation.annotationType(), defaultConsumer)
                            .accept(new AnnotationContainer.AnnotationActionConsumerArguments(
                                    value,
                                    validationErrors,
                                    annotation, (path + field.getName())
                            ));
                }

                if (field.getType().isAnnotationPresent(Constrained.class)) {
                    field.setAccessible(true);
                    validate(value, validationErrors, path + field.getName() + ".");
                }
                if (List.class.isAssignableFrom(field.getType()) &&
                        field.getAnnotatedType() instanceof AnnotatedParameterizedType) {
                    validationErrors.addAll(validateList
                            ((List<?>) value, path + field.getName(), field.getAnnotatedType()));
                }

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    private static HashSet<ValidationError> validateList(List<?> list, String path, AnnotatedType annotatedType) {
        HashSet<ValidationError> validationErrors = new LinkedHashSet<>();
        var innerType = ((AnnotatedParameterizedType) annotatedType).getAnnotatedActualTypeArguments()[0];
        int index = 0;
        for (Object el : list) {
            String postfix = String.format("[%d]", index);
            for (Annotation annotation : innerType.getDeclaredAnnotations()) {
                annotationActionMap.getOrDefault(annotation.annotationType(), defaultConsumer)
                        .accept(new AnnotationContainer.AnnotationActionConsumerArguments(
                                el, validationErrors, annotation, path + postfix));
            }

            if (el != null && el.getClass().isAnnotationPresent(Constrained.class)) {
                validate(el, validationErrors, path + postfix + ".");
            }
            if (el instanceof List) {
                validationErrors.addAll(validateList((List<?>) el, path + postfix, innerType));
            }

            index++;
        }

        return validationErrors;
    }
}