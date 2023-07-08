package com.interview.preparation.low_level_design.parking_lot.model.account;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Contact {
    private String phone;
    private Address address;
    private PersonalInfo personalInfo;
}
