package com.interview.preparation.low_level_design.amazon_locker_management.model;

import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Locker {
  private final String id;
  private final Location location;
  private final LockerSize lockerSize;
  private final Store store;
  private LockerStatus lockerStatus;

  public Locker(Location location, LockerSize lockerSize, LockerStatus lockerStatus, Store store) {
    this.lockerStatus = lockerStatus;
    this.id = UUID.randomUUID().toString();
    this.location = location;
    this.lockerSize = lockerSize;
    this.store = store;
  }

  public void setLockerStatus(LockerStatus lockerStatus) {
    this.lockerStatus = lockerStatus;
  }
}
