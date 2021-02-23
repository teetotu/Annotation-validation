package com.company;

import com.company.annotations.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@Constrained
public class Unrelated1 {
    @Size(min = 1, max = 1000)
    String blankString = "";

    @NotEmpty
    @Size(min = 1, max = 1000)
    List<Integer> emptyList = new ArrayList<>();

    @NotEmpty
    HashSet<Integer> emptyHashSet = new HashSet<>();

    @NotEmpty
    HashMap<Integer, Integer> emptyHashMap = new HashMap<>();
}
