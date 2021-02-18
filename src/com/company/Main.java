package com.company;

import com.company.booking.BookingForm;
import com.company.booking.GuestForm;
import com.company.validator.AnnotationContainer;
import com.company.validator.AnnotationValidator;
import com.company.validator.BaseAnnotationValidatorActions;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        AnnotationValidator mv = new AnnotationValidator();
        BookingForm bookingForm = new BookingForm(
                List.of(
                        new GuestForm("Shair_0", "Dilavar", 19),
                        new GuestForm("Shair_1", "Dilavar", 19),
                        new GuestForm("Shair_2", "Dilavar", 19)),
                List.of("TV", "Kitchen", "Kitchen", "TV"),
                "Hostel");
//        mv.validate(bookingForm);

        var ac = new AnnotationContainer();

    }
}


