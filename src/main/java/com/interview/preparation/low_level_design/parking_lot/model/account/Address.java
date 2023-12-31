package com.interview.preparation.low_level_design.parking_lot.model.account;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Address {
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String street;
    private String city;
    private String state;
    private String pincode;
}
