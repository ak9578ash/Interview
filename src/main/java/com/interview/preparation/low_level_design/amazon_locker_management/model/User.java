package com.interview.preparation.low_level_design.amazon_locker_management.model;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class User {
  private final String userId;
  private final String name;
  private final String email;
  private final Location location;

    public User(String name, String email) {
        this.userId = UUID.randomUUID().toString();
        this.name = name;
        this.email = email;
        this.location = null;
    }
}
