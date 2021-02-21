package com.company;

import com.company.annotations.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Constrained
public class Unrelated {
    @NotNull
    Set<@Positive Integer> notNullSet = new HashSet<>();

    @NotNull
    static List<@NotNull String> integerList = new ArrayList<>();

    static {
        integerList.add(null);
        integerList.add("String1");
        integerList.add("String2");

    }
}
