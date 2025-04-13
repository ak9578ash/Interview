package com.interview.preparation.low_level_design.amazon_locker_management.service;

import com.interview.preparation.low_level_design.amazon_locker_management.model.Item;
import com.interview.preparation.low_level_design.amazon_locker_management.model.Locker;
import com.interview.preparation.low_level_design.amazon_locker_management.model.LockerAssignment;
import com.interview.preparation.low_level_design.amazon_locker_management.model.User;
import java.util.List;

public interface LockerAssignmentService {
  Locker findNearestLocker(User user, Item item);
  Boolean validateLockerAssignment(Locker locker, User user, String otpCode);

  List<LockerAssignment> getAllLockerAssignmentsByLockerIds(List<String> lockerIds);

  void deleteByUserIdAndLockerId(String userId, String lockerId);
}
