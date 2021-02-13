package com.company;

import java.util.Collections;
import java.util.List;

@Constrained
public class BookingForm {
    @NotNull
    @Size(min = 1, max = 5)
    private List<@NotNull GuestForm> guests;

    @NotNull
    private List<@AnyOf({"TV", "Kitchen"}) String> amenities;

    @NotNull
    @AnyOf({"House", "Hostel"})
    private String propertyType;

    public BookingForm(List<GuestForm> guests, List<String> amenities, String propertyType) {
        this.guests = guests;
        this.amenities = amenities;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public List<String> getAmenities() {
        return Collections.unmodifiableList(this.amenities);
    }

    public void setAmenities(List<String> amenities) {
        this.amenities = amenities;
    }

    public List<GuestForm> getGuests() {
        return Collections.unmodifiableList(this.guests);
    }

    public void setGuests(List<GuestForm> guests) {
        this.guests = guests;
    }
}

