package com.company;

import com.company.annotations.Constrained;
import com.company.annotations.NotNull;

@Constrained
class Unrelated1 {
    Unrelated2 otherClassObject1 = new Unrelated2();

    @NotNull
    String nullString1 = null;

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


