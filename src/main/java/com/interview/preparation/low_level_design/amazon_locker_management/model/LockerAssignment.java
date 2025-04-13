package com.interview.preparation.low_level_design.amazon_locker_management.model;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LockerAssignment {
  private final String lockerId;
  private final String userId;
  private final String itemId;
  private final String otp;
  private final LocalDateTime assignedAt;
  private final Integer expirationTimeInDay;

  public LockerAssignment(String lockerId, String userId, String itemId, String otp) {
    this.lockerId = lockerId;
    this.userId = userId;
    this.itemId = itemId;
    this.otp = otp;
    this.assignedAt = LocalDateTime.now();
    this.expirationTimeInDay = 3;
  }
}
