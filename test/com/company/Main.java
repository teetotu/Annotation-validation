package com.company;

import com.company.booking.BookingForm;
import com.company.booking.GuestForm;
import com.company.validator.AnnotationValidator;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ArrayList<GuestForm> guests = new ArrayList<>();
        guests.add(null);
        guests.add(new GuestForm("Second", "Person", 20));
        guests.add(new GuestForm("Third", "Person", 30));
        BookingForm bookingForm = new BookingForm(
                guests,
                List.of("TV", "Kitchen"),
                "House"
        );
        AnnotationValidator av = new AnnotationValidator();

        av.validate(bookingForm).forEach(validationError -> {
            System.out.println(validationError.getMessage() + " | " +
                    validationError.getPath() + " | " +
                    validationError.getFailedValue());
        });
    }
}
