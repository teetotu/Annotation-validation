package com.company;

import com.company.booking.BookingForm;
import com.company.booking.GuestForm;
import com.company.validator.MyValidator;

import java.lang.reflect.AnnotatedParameterizedType;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        MyValidator mv = new MyValidator();
        BookingForm bookingForm = new BookingForm(
                List.of(
                        new GuestForm("Shair_0", "Dilavar", 19),
                        new GuestForm("Shair_1", "Dilavar", 19),
                        new GuestForm("Shair_2", "Dilavar", 19)),
                List.of("TV", "Kitchen", "Kitchen", "TV"),
                "Hostel");
        mv.validate(bookingForm);
    }
}


