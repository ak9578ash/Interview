package com.interview.preparation.low_level_design.amazon_locker_management.service;

import com.interview.preparation.low_level_design.amazon_locker_management.model.Locker;
import com.interview.preparation.low_level_design.amazon_locker_management.model.LockerStatus;
import java.util.List;

public interface LockerService {
  List<Locker> getAllAvailableLocker();
  List<Locker> getAllOccupiedLocker();
  void updateLockerStatusById(String lockerId, LockerStatus lockerStatus);

}
