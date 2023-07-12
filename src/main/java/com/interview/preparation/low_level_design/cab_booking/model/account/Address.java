package com.interview.preparation.low_level_design.cab_booking.model.account;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Address {
    private String line1;
    private String line2;
    private String city;
    private String state;
    private String postalCode;
}
