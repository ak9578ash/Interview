package com.interview.preparation.low_level_design.ecomm_product_review.model.account;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserProfile {
    private String emailId;
    private String firstName;
    private String lastName;
    private String middleName;
    private String userName;
    private String secondaryEmailId;
    private String password;
    private String phoneNumber;
    private Address userAddress;
    private LocalDateTime createdAt;
    private LocalDateTime lastLoggedAt;
    private VerificationStatus verificationStatus;
}
