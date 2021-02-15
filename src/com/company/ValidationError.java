package com.company;

public interface ValidationError {
    String getMessage();

    String getPath();

    Object getFailedValue();
}

