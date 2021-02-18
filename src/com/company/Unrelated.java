package com.company;

import com.company.annotations.Constrained;
import com.company.annotations.InRange;
import com.company.annotations.NotNull;
import com.company.annotations.Size;

import java.util.List;

@Constrained
public class Unrelated {
    @NotNull
    String str;

    @Size(min = 5, max = 10)
    List<@InRange(min = 1, max = 4) Integer> integerList;

    @Constrained
    static class UnrelatedInner1 {
        @NotNull
        String strInner;
    }

    @Constrained
    static class UnrelatedInner2 {
        @NotNull
        String strInner;
    }
}
