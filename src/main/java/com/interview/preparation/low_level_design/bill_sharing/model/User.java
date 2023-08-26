package com.interview.preparation.low_level_design.bill_sharing.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import java.util.UUID;

@Getter
@Setter
public class User {
    private String name;
    private String userId;
    private String emailId;
    private String phoneNo;

    public User(@NonNull String name, @NonNull String emailId, @NonNull String phoneNo) {
        this.name = name;
        this.userId = UUID.randomUUID().toString();
        this.emailId = emailId;
        this.phoneNo = phoneNo;
    }
}
