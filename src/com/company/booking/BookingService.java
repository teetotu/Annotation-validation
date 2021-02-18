package com.company.booking;

import java.util.List;

public class BookingService {
    public void bookRoom(BookingForm bookingForm) {
        if (bookingForm != null && bookingForm.getAmenities() != null
                && List.of("TV", "Kitchen").containsAll(bookingForm.getAmenities())
                && bookingForm.getPropertyType() != null
                && List.of("House", "Hostel").contains(bookingForm.getPropertyType())
                && bookingForm.getGuests() != null
                && bookingForm.getGuests()
                .stream()
                .allMatch(it -> it != null && it.getAge() > 0
                        && it.getFirstName() != null && !it.getFirstName().isBlank()
                        && it.getLastName() != null && !it.getLastName().isBlank())
        ) {
            doBookRoom(bookingForm);
            return;
        }
        throw new RuntimeException("Форма заполнена неверно");
    }

    private void doBookRoom(BookingForm bookingForm) {
        // TODO: nothing
    }
}
