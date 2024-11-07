package com.interview.preparation.low_level_design.messaging_app.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserProfile {
    private String firstName;
    private String lastName;
    private String emailId;
    private String phoneNumber;
}
