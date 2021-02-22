package com.company;

import com.company.annotations.Constrained;
import com.company.annotations.Negative;
import com.company.annotations.NotNull;
import com.company.annotations.Positive;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

@Constrained
class Unrelated1 {
    static List<@NotNull @Negative List<@Positive @Negative List<@NotNull @Negative String>>> nullList = new LinkedList<>();
    static HashSet<@NotNull List<@NotNull List<@NotNull Integer>>> nullSet = new HashSet<>();

    Unrelated1() {
        for (int i = 0; i < 3; i++) {
            nullList.add(new LinkedList<>());
            for (int j = 0; j < 3; j++) {
                nullList.get(i).add(null);
            }
        }
    }

}

@Constrained
class Unrelated2 {
    @NotNull
    Unrelated3 otherClassObject2 = new Unrelated3();

    @NotNull
    String nullString2 = null;
}

@Constrained
class Unrelated3 {
    @NotNull
    String nullString31 = null;
    @NotNull
    String nullString32 = null;
    @NotNull
    String nullString33 = null;
    @NotNull
    String nullString34 = null;
}


