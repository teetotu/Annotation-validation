package com.company.validator;

import com.company.annotations.*;

import java.lang.annotation.Annotation;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.function.Consumer;

public class AnnotationContainer {
    /**
     * Utility class to pass arguments to the annotation checker consumer
     */
    protected static class AnnotationActionConsumerArguments {
        public String path;
        public Object value;
        public HashSet<ValidationError> errorTable;
        public Annotation annotation;

        public AnnotationActionConsumerArguments(Object value, HashSet<ValidationError> errorTable,
                                                 Annotation annotation, String path) {
            this.path = path;
            this.value = value;
            this.errorTable = errorTable;
            this.annotation = annotation;
        }
    }

    /**
     * Mapping of default Annotations to related Checkers
     */
    private static final Map<Class<? extends Annotation>, Consumer<AnnotationActionConsumerArguments>>
            defaultAnnotationActionMap = new HashMap<>();

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

    public HashMap<Class<? extends Annotation>, Consumer<AnnotationActionConsumerArguments>> userAnnotationActionList =
            new HashMap<>();


    /**
     * @param annotation Annotation you want to check
     * @param action     Consumer<T> that performs some operation intended by the annotation
     */
    public void addAnnotationChecker(Class<? extends Annotation> annotation, Consumer<AnnotationActionConsumerArguments> action) {
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
    public static Map<Class<? extends Annotation>, Consumer<AnnotationActionConsumerArguments>> getDefaultAnnotationActionUnmodifiableMap() {
        return Collections.unmodifiableMap(defaultAnnotationActionMap);
    }
}

