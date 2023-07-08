package com.interview.preparation.low_level_design.parking_lot.model.account;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class PersonalInfo {
    private String firstName;
    private String lastName;
    private String middleName;
    private String initials;
    private String dob;
}
