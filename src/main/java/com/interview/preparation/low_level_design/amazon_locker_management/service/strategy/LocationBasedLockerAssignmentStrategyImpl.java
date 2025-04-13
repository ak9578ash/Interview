package com.interview.preparation.low_level_design.amazon_locker_management.service.strategy;

import com.interview.preparation.low_level_design.amazon_locker_management.model.Item;
import com.interview.preparation.low_level_design.amazon_locker_management.model.Location;
import com.interview.preparation.low_level_design.amazon_locker_management.model.Locker;
import com.interview.preparation.low_level_design.amazon_locker_management.model.User;
import com.interview.preparation.low_level_design.amazon_locker_management.service.LockerService;
import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LocationBasedLockerAssignmentStrategyImpl implements LockerAssignmentStrategy{
  private final LockerService lockerService;

  @Override
  public Locker findOptimalLocker(User user, Item item) {
    // Some code will be here to find the optimal locker based on user location and item
    List<Locker> allLocker = lockerService.getAllAvailableLocker();
    return Locker
        .builder()
        .id("Locker-1")
        .location(new Location(10.0, 12.5))
        .build();
  }
}

