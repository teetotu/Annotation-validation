package com.company.validator;

import com.company.annotations.Size;

import java.lang.reflect.Field;
import java.util.*;
import java.util.function.BiConsumer;

public class AnnotationContainer {
//    public static class ActionPair {
//        String trigger;
//        BiConsumer<Field, Validator> action;
//
//        ActionPair(String trigger, BiConsumer<Field, Validator> action) {
//            this.trigger = trigger;
//            this.action = action;
//        }
//    }

    /**
     * Mapping of default Annotations to related Checkers
     */
    private static final Map<String, BiConsumer<Field, Validator>> defaultAnnotationActionMap = new HashMap<>() {{
        put(Size.class.getName(), BaseAnnotationValidatorActions::checkSize);
    }};

    public Map<String, BiConsumer<Field, Validator>> userAnnotationActionList = new HashMap<>();


    public void Add(String annotationName, BiConsumer<Field, Validator> action) {
        if (annotationName == null || action == null)
            throw new NullPointerException();
        userAnnotationActionList.put(annotationName, action);
    }

    /**
     * Do not try to modify
     *
     * @return Unmodifiable version of default Annotation Trigger->Action Map
     * @throws UnsupportedOperationException in case of put or any other operation
     *                                       that is not applicable to Collections.unmodifiableMap objects
     */
    public static Map<String, BiConsumer<Field, Validator>> getDefaultAnnotationActionUnmodifiableList() {
        return Collections.unmodifiableMap(defaultAnnotationActionMap);
    }
}

