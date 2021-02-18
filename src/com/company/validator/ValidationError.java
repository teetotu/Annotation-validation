package com.company.validator;

public interface ValidationError {
    String getMessage();

    String getPath();

    Object getFailedValue();
}

