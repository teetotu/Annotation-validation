package com.company;

import java.util.Set;

public interface Validator {
    Set<ValidationError> validate(Object object);
}
