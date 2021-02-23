package com.company;

import com.company.annotations.Constrained;
import com.company.annotations.InRange;

@Constrained
public class Unrelated2 {
    @InRange(min=5, max=10)
    Integer intt = 11;
}
