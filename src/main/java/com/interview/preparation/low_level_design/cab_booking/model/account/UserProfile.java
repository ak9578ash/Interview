package com.interview.preparation.low_level_design.cab_booking.model.account;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class UserProfile {
    private String firstName;
    private String middleName;
    private String lastName;
    private String emailId;
}
