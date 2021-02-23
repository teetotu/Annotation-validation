package com.company.validator;

import com.company.booking.BookingForm;
import com.company.booking.GuestForm;
import org.junit.jupiter.api.Assertions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest {
    static AnnotationValidator av = new AnnotationValidator();

    @org.junit.jupiter.api.Test
    void validateCorrectBookingFormTrue() {
        BookingForm bookingForm = new BookingForm(
                List.of(
                        new GuestForm("First", "Person", 10),
                        new GuestForm("Second", "Person", 20),
                        new GuestForm("Third", "Person", 30)
                ),
                List.of("TV", "Kitchen"),
                "House"
        );

        Assertions.assertTrue(av.validate(bookingForm).isEmpty());
    }

    void validateShouldFindNullInFirstTest() {
        BookingForm bookingForm = new BookingForm(
                List.of(
                        null,
                        new GuestForm("Second", "Person", 20),
                        new GuestForm("Third", "Person", 30)
                ),
                List.of("TV", "Kitchen"),
                "House"
        );

        var validationErrors = av.validate(bookingForm).stream().toArray();
        String path = "guests[0]";
        String failedValue = "guests[0]";
        Assertions.assertEquals(((ValidationError) validationErrors[0]).getPath(), path);
        Assertions.assertEquals(failedValue, null);
    }
}