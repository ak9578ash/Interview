package com.interview.preparation.low_level_design.amazon_locker_management.service;

import com.interview.preparation.low_level_design.amazon_locker_management.model.Locker;
import com.interview.preparation.low_level_design.amazon_locker_management.model.LockerAssignment;
import com.interview.preparation.low_level_design.amazon_locker_management.model.LockerStatus;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;

/*
   This service will run in every 5 min to check if the locker assignment is expired or not.
   if expired initiate the refund
*/
@AllArgsConstructor
public class RefundCronService {
  private final LockerService lockerService;
  private final LockerAssignmentService lockerAssignmentService;
  private final ItemService itemService;
  private final NotificationService notificationService;

  void checkExpiredLockersAssignment() {
    List<Locker> occupiedLocker = lockerService.getAllOccupiedLocker();

    List<String> occupiedLockerIds = occupiedLocker
        .stream()
        .map(Locker::getId)
        .toList();

    List<LockerAssignment> lockerAssignments =
        lockerAssignmentService.getAllLockerAssignmentsByLockerIds(occupiedLockerIds);

    lockerAssignments
        .forEach(
            lockerAssignment -> {
              if (LocalDateTime.now()
                  .isAfter(lockerAssignment.getAssignedAt().plusDays(lockerAssignment.getExpirationTimeInDay()))) {
                lockerAssignmentService.deleteByUserIdAndLockerId(lockerAssignment.getUserId(), lockerAssignment.getLockerId());
                lockerService.updateLockerStatusById(lockerAssignment.getLockerId(), LockerStatus.AVAILABLE);
                itemService.initiateItemRefund(lockerAssignment.getUserId(), lockerAssignment.getItemId());
                notificationService.sendItemRefundNotification(lockerAssignment.getUserId(), lockerAssignment.getItemId());
              }
            }
        );
  }
}
