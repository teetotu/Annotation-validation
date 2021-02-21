package com.company.validator;

import com.company.annotations.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Consumer;

public class AnnotationContainer {
    protected static class AnnotationActionConsumerArguments {
        public String path;
        public Object value;
        public HashSet<ValidationError> errorTable;
        public Field field;
        public Annotation annotation;

        public AnnotationActionConsumerArguments(Object value, HashSet<ValidationError> errorTable,
                                                 Field field, Annotation annotation) {
            this.path = field.getName();
            this.value = value;
            this.errorTable = errorTable;
            this.field = field;
            this.annotation = annotation;
        }
    }

    /**
     * Mapping of default Annotations to related Checkers
     */
    private static final Map<Class<?>, Consumer<AnnotationActionConsumerArguments>> defaultAnnotationActionMap =
            new HashMap<>();
    static {
        defaultAnnotationActionMap.put(Size.class, BaseAnnotationValidatorActions::checkSize);
        defaultAnnotationActionMap.put(NotNull.class, BaseAnnotationValidatorActions::checkNull);
        defaultAnnotationActionMap.put(NotEmpty.class, BaseAnnotationValidatorActions::checkIfEmpty);
        defaultAnnotationActionMap.put(NotBlank.class, BaseAnnotationValidatorActions::checkIfBlank);
        defaultAnnotationActionMap.put(Positive.class, BaseAnnotationValidatorActions::checkSign);
        defaultAnnotationActionMap.put(Negative.class, BaseAnnotationValidatorActions::checkSign);
        defaultAnnotationActionMap.put(InRange.class, BaseAnnotationValidatorActions::checkRange);
        defaultAnnotationActionMap.put(AnyOf.class, BaseAnnotationValidatorActions::checkValues);
    }

    public Map<Class<?>, Consumer<AnnotationActionConsumerArguments>> userAnnotationActionList = new HashMap<>();


    public void Add(Class<?> annotation, Consumer<AnnotationActionConsumerArguments> action) {
        if (annotation == null || action == null)
            throw new NullPointerException();
        userAnnotationActionList.put(annotation, action);
    }

    /**
     * Do not try to modify
     *
     * @return Unmodifiable version of default Annotation Trigger->Action Map
     * @throws UnsupportedOperationException in case of put or any other operation
     *                                       that is not applicable to Collections.unmodifiableMap objects
     */
    public static Map<Class<?>, Consumer<AnnotationActionConsumerArguments>> getDefaultAnnotationActionUnmodifiableMap() {
        return Collections.unmodifiableMap(defaultAnnotationActionMap);
    }
}

