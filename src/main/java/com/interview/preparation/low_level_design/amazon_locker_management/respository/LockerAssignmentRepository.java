package com.interview.preparation.low_level_design.amazon_locker_management.respository;

import com.interview.preparation.low_level_design.amazon_locker_management.model.LockerAssignment;

public interface LockerAssignmentRepository {
  void save(LockerAssignment lockerAssignment);
  LockerAssignment findByUserIdAndLockerId(String userId, String lockerId);

  void deleteByUserIdAndLockerId(String userId, String lockerId);
}
