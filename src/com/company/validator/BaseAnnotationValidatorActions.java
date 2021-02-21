package com.company.validator;

import com.company.annotations.*;
import com.company.utils.Utils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BaseAnnotationValidatorActions {
    /**
     * @param args AnnotationActionConsumerArguments
     */
    public static void checkSize(AnnotationContainer.AnnotationActionConsumerArguments args) {
        if (args == null) return;

        if (args.value instanceof List) {
            if (((List<?>) args.value).size() < ((Size) args.annotation).min() ||
                    ((List<?>) args.value).size() > ((Size) args.annotation).max()) {
                args.errorTable.add(new AnnotationValidationError("Size out of range", args.path, args.value));
            }
        } else if (args.value instanceof Set) {
            if (((Set<?>) args.value).size() < ((Size) args.annotation).min() ||
                    ((Set<?>) args.value).size() > ((Size) args.annotation).max()) {
                args.errorTable.add(new AnnotationValidationError("Size out of range", args.path, args.value));
            }
        } else if (args.value instanceof Map) {
            if (((Map<?, ?>) args.value).size() < ((Size) args.annotation).min() ||
                    ((Map<?, ?>) args.value).size() > ((Size) args.annotation).max()) {
                args.errorTable.add(new AnnotationValidationError("Size out of range", args.path, args.value));
            }
        } else if (args.value instanceof String) {
            if (((String) args.value).length() < ((Size) args.annotation).min() ||
                    ((String) args.value).length() > ((Size) args.annotation).max()) {
                args.errorTable.add(new AnnotationValidationError("Size out of range", args.path, args.value));
            }
        }
    }

    public static void checkNull(AnnotationContainer.AnnotationActionConsumerArguments args) {
        if (args.value == null) {
            args.errorTable.add(new AnnotationValidationError("Value can not be null",
                    args.path, null));
        }
    }

    public static void checkSign(AnnotationContainer.AnnotationActionConsumerArguments args) {
        if (args.value == null) return;
        if (Utils.getWrapperIntTypeMap().containsKey(args.value.getClass())) {
            if (Utils.getWrapperIntTypeMap().get(args.value.getClass()).cast(args.value) instanceof Number) {
                Number nm = (Number) Utils.getWrapperIntTypeMap().get(args.value.getClass()).cast(args.value);
                if (nm.longValue() < 0 &&
                        args.annotation.annotationType().getName().equals(Positive.class.getName())) {
                    args.errorTable.add(
                            new AnnotationValidationError("Value should be positive", args.path, args.value));
                } else if (nm.longValue() > 0 &&
                        args.annotation.annotationType().getName().equals(Negative.class.getName())) {
                    args.errorTable.add(
                            new AnnotationValidationError("Value should be negative", args.path, args.value));
                }
            }
        }
    }

    public static void checkRange(AnnotationContainer.AnnotationActionConsumerArguments args) {
        if (args.value == null) return;
        if (Utils.getWrapperIntTypeMap().containsKey(args.value.getClass())) {
            Long lng = ((Number) Utils.getWrapperIntTypeMap().get(args.value.getClass()).cast(args.value)).longValue();
            if (lng < ((InRange) args.annotation).min() || lng > ((InRange) args.annotation).max()) {
                args.errorTable.add(new AnnotationValidationError("Value is out of range", args.path, lng));
            }
        }
    }

    public static void checkIfBlank(AnnotationContainer.AnnotationActionConsumerArguments args) {
        if (args.value == null) return;
        if (args.value instanceof String) {
            if (((String) args.value).isBlank()) {
                args.errorTable.add(
                        new AnnotationValidationError("String can not be blank", args.path, args.value));
            }
        }
    }

    public static void checkIfEmpty(AnnotationContainer.AnnotationActionConsumerArguments args) {
        if (args.value == null) return;
        if (args.value instanceof String) {
            if (((String) args.value).isEmpty()) {
                args.errorTable.add(
                        new AnnotationValidationError("String can not be empty", args.path, args.value));
            }
        } else if (args.value instanceof List) {
            if (((List<?>) args.value).isEmpty()) {
                args.errorTable.add(
                        new AnnotationValidationError("List can not be empty", args.path, args.value));
            }
        } else if (args.value instanceof Map) {
            if (((Map<?, ?>) args.value).isEmpty()) {
                args.errorTable.add(
                        new AnnotationValidationError("Map can not be empty", args.path, args.value));
            }
        } else if (args.value instanceof Set) {
            if (((Set<?>) args.value).isEmpty()) {
                args.errorTable.add(
                        new AnnotationValidationError("Set can not be empty", args.path, args.value));
            }
        }
    }

    public static void checkValues(AnnotationContainer.AnnotationActionConsumerArguments args) {
        if (args.value instanceof String) {
            if (!Arrays.stream(((AnyOf) args.annotation).value()).anyMatch(it -> it.equals(args.value))) {
                args.errorTable.add(
                        new AnnotationValidationError("Invalid argument", args.path, args.value));
            }
        }
    }
}
