package com.interview.preparation.practise.bill_sharing.model;

import lombok.Getter;

import java.util.UUID;

@Getter
public class User {
    private final String id;
    private final String firstName;
    private final String lastName;
    private final String emailId;

    public User(String firstName, String lastName, String emailId) {
        this.id = UUID.randomUUID().toString();
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailId = emailId;
    }
}
