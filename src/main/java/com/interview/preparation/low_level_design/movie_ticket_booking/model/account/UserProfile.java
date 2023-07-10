package com.interview.preparation.low_level_design.movie_ticket_booking.model.account;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserProfile {
    private String FirstName;
    private String MiddleName;
    private String LastName;
    private String emailId;
    private String phoneNumber;
}
