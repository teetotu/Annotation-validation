package com.company;

import com.company.booking.BookingForm;
import com.company.booking.GuestForm;
import com.company.validator.AnnotationValidator;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        AnnotationValidator mv = new AnnotationValidator();
        Unrelated unrelated = new Unrelated();
        mv.validate(unrelated).forEach(it -> System.out.println(it.getMessage() + " " + it.getPath() + " " + it.getFailedValue()));
    }
}


//        BookingForm bookingForm = new BookingForm(
//                List.of(
//                        new GuestForm(null, "Dilavar", 19),
//                        new GuestForm("Shair", "Dilavar", 19),
//                        new GuestForm("Shair", "Dilavar", 19)
//                ),
//                List.of("TV", "Kitchen", "Kitchen", "TV"),
//                "Hostel");
