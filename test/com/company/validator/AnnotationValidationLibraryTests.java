package com.company.validator;

import com.company.Unrelated1;
import com.company.Unrelated2;
import com.company.Unrelated3;
import com.company.booking.BookingForm;
import com.company.booking.GuestForm;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class AnnotationValidationLibraryTests {
    static AnnotationValidator av = new AnnotationValidator();

    @Test
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

    @Test
    void validateShouldFindNullInFirstTest() {
        ArrayList<GuestForm> guests = new ArrayList<>();
        guests.add(null);
        guests.add(new GuestForm("Second", "Person", 20));
        guests.add(new GuestForm("Third", "Person", 30));
        BookingForm bookingForm = new BookingForm(
                guests,
                List.of("TV", "Kitchen"),
                "House"
        );

        var validationErrors = ((ValidationError) av.validate(bookingForm).toArray()[0]);
        String path = "guests[0]";
        Assertions.assertEquals(validationErrors.getPath(), path);
        Assertions.assertNull(validationErrors.getFailedValue());
    }

    @Test
    void validateShouldNotValidateNotConstrainedClass() {
        String errorMessage = "Can not validate an object of a class without the @Constrained annotation";
        var actualError = ((ValidationError) av.validate(5).toArray()[0]);
        Assertions.assertEquals(actualError.getMessage(), errorMessage);
    }

    @Test
    void checkSizeTest() {
        Unrelated1 object = new Unrelated1();
        Integer correctSize = 5;
        Integer actualSize = av.validate(object).toArray().length;
        Assertions.assertEquals(correctSize, actualSize);
    }

    @Test
    void inRangeAnnotationTest() {
        Unrelated2 object = new Unrelated2();
        var validationErrors = av.validate(object).toArray();
        Integer correctFailedValue = 11;
        Integer actualFailedValue =
                ((Number) ((ValidationError) validationErrors[0]).getFailedValue()).intValue();
        Assertions.assertEquals(correctFailedValue, actualFailedValue);
    }

    @Test
    void positiveNegativeAnnotationTest() {
        Unrelated3 object = new Unrelated3();
        Integer correctSize = 3;
        Integer actualSize = av.validate(object).toArray().length;
        Assertions.assertEquals(correctSize, actualSize);

    }
}