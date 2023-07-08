package com.interview.preparation.low_level_design.parking_lot.model.account;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public abstract class Account {
    private String id;
    private String email;
    private String userName;
    private String password;
    private LocalDateTime lastAccessed;
    private Contact contact;
}
