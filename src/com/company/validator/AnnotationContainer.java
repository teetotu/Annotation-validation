package com.company.validator;

import com.company.annotations.Size;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiConsumer;

public class AnnotationContainer {

    public static class ValidatorActionPair<T> {
        public String trigger;
        public BiConsumer<T, Validator> action;

        public ValidatorActionPair(String trigger, BiConsumer<T, Validator> action) {
            this.trigger = trigger;
            this.action = action;
        }
    }

    private final List<ValidatorActionPair<Field>> defaultAnnotationActionMap = new ArrayList<>() {{
        add(0, new ValidatorActionPair<>(Size.class.getSimpleName(), BaseAnnotationValidatorActions::checkSize));
    }};

    List<ValidatorActionPair<Field>> userAnnotationActionMap = new ArrayList<>();


    public void Add(String annotationName, BiConsumer<Field, Validator> action) {
        if (annotationName == null || action == null)
            throw new NullPointerException();
        userAnnotationActionMap.add(new ValidatorActionPair<Field>(annotationName, action));
    }

    /** Do not try to modify
     * @return Unmodifiable version of default Annotation Trigger->Action List
     * @throws UnsupportedOperationException in case of add or any other operation
     * that is not applicable to Collections.unmodifiableList objects
     */
    public List<ValidatorActionPair<Field>> getDefaultAnnotationActionUnmodifiableList() {
        return Collections.unmodifiableList(defaultAnnotationActionMap);
    }
}

