package com.interview.preparation.low_level_design.amazon_locker_management.model;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Getter;

@Getter
public class Store {
  private final String id;
  private final String name;
  private final LocalDateTime openingTime;
  private final LocalDateTime closingTime;

  public Store(String name, LocalDateTime openingTime, LocalDateTime closingTime) {
    this.id = UUID.randomUUID().toString();
    this.name = name;
    this.openingTime = openingTime;
    this.closingTime = closingTime;
  }
}
