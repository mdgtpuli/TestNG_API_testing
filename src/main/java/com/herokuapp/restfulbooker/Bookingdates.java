package com.herokuapp.restfulbooker;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Bookingdates {

    private String checkin;
    private String checkout;

    public Bookingdates() {
    }
}
