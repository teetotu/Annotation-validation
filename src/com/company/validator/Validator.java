package com.company.validator;

import java.util.Set;

public interface Validator {
    Set<ValidationError> validate(Object object);
}
