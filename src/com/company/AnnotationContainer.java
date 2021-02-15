package com.company;

import com.company.annotations.Size;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;

public class AnnotationContainer {
    HashMap<Annotation, Method> annotationActionMap = new HashMap<>() {{
        put(Size.class, )
    }};

    public void Add(Annotation newAnnotation, Method action) {
        if (newAnnotation == null || action == null)
            throw new NullPointerException();
        annotationActionMap.put(newAnnotation, action);
    }
}

