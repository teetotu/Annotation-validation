package com.company;

import com.company.validator.AnnotationValidator;
import com.company.validator.ValidationError;

public class Main {

    public static void main(String[] args) {
        AnnotationValidator mv = new AnnotationValidator();
        Unrelated1 unrelated = new Unrelated1();
        for (ValidationError it : mv.validate(unrelated)) {
            System.out.println(it.getMessage() + " " + it.getPath() + " " + it.getFailedValue());
        }
    }
}