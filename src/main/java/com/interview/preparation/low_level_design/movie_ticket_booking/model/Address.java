package com.interview.preparation.low_level_design.movie_ticket_booking.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Address {
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String postalCode;

}
