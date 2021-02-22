package com.company;

import com.company.validator.AnnotationValidator;

import java.lang.reflect.AnnotatedParameterizedType;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        AnnotationValidator mv = new AnnotationValidator();
        Unrelated1 object = new Unrelated1();
        Arrays.stream(object.getClass().getDeclaredFields()).forEach(field -> {
            var annotatedType = ((AnnotatedParameterizedType) field.getAnnotatedType()).getAnnotatedActualTypeArguments()[0];
            System.out.println(annotatedType.getType());
            System.out.println(Arrays.toString(annotatedType.getDeclaredAnnotations()));
            StringBuilder offset = new StringBuilder("\t");

            while (true){
                offset.append("\t");
                try {
                    annotatedType = ((AnnotatedParameterizedType) annotatedType).getAnnotatedActualTypeArguments()[0];
                } catch (Exception e) {
                    break;
                }
                System.out.println(offset.toString() + (annotatedType.getType()));
                System.out.println(offset.toString() + Arrays.toString(annotatedType.getDeclaredAnnotations()));
            }
        });
    }


}