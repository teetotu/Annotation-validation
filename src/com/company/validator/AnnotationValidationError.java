package com.company.validator;

public class AnnotationValidationError implements ValidationError {
    private final String message;
    private final String path;
    private final Object failedValue;

    protected AnnotationValidationError(String message, String path, Object failedValue) {
        this.message = message;
        this.path = path;
        this.failedValue = failedValue;
    }

    /**
     * @return validation error message
     */
    @Override
    public String getMessage() {
        return message;
    }

    /**
     * @return failed value path
     */
    @Override
    public String getPath() {
        return path;
    }

    /**
     * @return failed value
     */
    @Override
    public Object getFailedValue() {
        return failedValue;
    }
}
