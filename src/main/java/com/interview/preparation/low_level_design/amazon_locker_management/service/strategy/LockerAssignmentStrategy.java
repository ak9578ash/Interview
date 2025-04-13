package com.interview.preparation.low_level_design.amazon_locker_management.service.strategy;

import com.interview.preparation.low_level_design.amazon_locker_management.model.Item;
import com.interview.preparation.low_level_design.amazon_locker_management.model.Locker;
import com.interview.preparation.low_level_design.amazon_locker_management.model.User;

public interface LockerAssignmentStrategy {
  Locker findOptimalLocker(User user, Item item);
}
