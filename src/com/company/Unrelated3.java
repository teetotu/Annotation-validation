package com.company;

import com.company.annotations.*;

@Constrained
public class Unrelated3 {
    @Positive
    Integer shouldBePositive = -5;

    @Negative
    Integer shouldBeNegative = 5;

    @NotBlank
    String shouldNotBeBlank = "";
}
