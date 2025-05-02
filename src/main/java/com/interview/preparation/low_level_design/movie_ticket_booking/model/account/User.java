package com.interview.preparation.low_level_design.movie_ticket_booking.model.account;

import com.interview.preparation.low_level_design.movie_ticket_booking.model.Address;
import lombok.Getter;

import java.util.UUID;

@Getter
public class User {
  private final String id;
  private final Address address;
  private final UserProfile userProfile;

  public User(Address address, UserProfile userProfile) {
    this.id = UUID.randomUUID().toString();
    this.address = address;
    this.userProfile = userProfile;
  }
}
