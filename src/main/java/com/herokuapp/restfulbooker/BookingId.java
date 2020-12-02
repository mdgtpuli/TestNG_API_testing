package com.herokuapp.restfulbooker;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class BookingId {
    private int bookingid;
    private Booking booking;

    public BookingId() {
    }
}
