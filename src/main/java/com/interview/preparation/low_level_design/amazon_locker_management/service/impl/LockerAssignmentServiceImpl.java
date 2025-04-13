package com.interview.preparation.low_level_design.amazon_locker_management.service.impl;

import com.interview.preparation.low_level_design.amazon_locker_management.model.Item;
import com.interview.preparation.low_level_design.amazon_locker_management.model.Locker;
import com.interview.preparation.low_level_design.amazon_locker_management.model.LockerAssignment;
import com.interview.preparation.low_level_design.amazon_locker_management.model.LockerStatus;
import com.interview.preparation.low_level_design.amazon_locker_management.model.User;
import com.interview.preparation.low_level_design.amazon_locker_management.respository.LockerAssignmentRepository;
import com.interview.preparation.low_level_design.amazon_locker_management.service.LockerAssignmentService;
import com.interview.preparation.low_level_design.amazon_locker_management.service.LockerService;
import com.interview.preparation.low_level_design.amazon_locker_management.service.NotificationService;
import com.interview.preparation.low_level_design.amazon_locker_management.service.OtpService;
import com.interview.preparation.low_level_design.amazon_locker_management.service.strategy.LockerAssignmentStrategy;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LockerAssignmentServiceImpl implements LockerAssignmentService {
  private final LockerAssignmentStrategy lockerAssignmentStrategy;
  private final OtpService otpService;
  private final LockerAssignmentRepository lockerAssignmentRepository;
  private final NotificationService notificationService;
  private final LockerService lockerService;

  @Override
  public Locker findNearestLocker(User user, Item item) {
    if (!item.getIsItemLockerEligible()) {
      throw new IllegalArgumentException("Item is not eligible for locker assignment");
    }

    Locker optimalLocker = lockerAssignmentStrategy.findOptimalLocker(user, item);
    if (optimalLocker == null) {
      throw new RuntimeException("No locker available");
    }

    optimalLocker.setLockerStatus(LockerStatus.OCCUPIED);
    lockerService.updateLockerStatusById(optimalLocker.getId(), optimalLocker.getLockerStatus());

    String otpCode = otpService.generateOtp(user.getUserId(), optimalLocker.getId());

    LockerAssignment lockerAssignment = LockerAssignment.builder()
        .lockerId(optimalLocker.getId())
        .userId(user.getUserId())
        .itemId(item.getId())
        .otp(otpCode)
        .assignedAt(LocalDateTime.now())
        .expirationTimeInDay(3)
        .build();

    lockerAssignmentRepository.save(lockerAssignment);
    notificationService.sendOtpNotification(user, otpCode);
    return optimalLocker;
  }

  @Override
  public Boolean validateLockerAssignment(Locker locker, User user, String otpCode) {
    LockerAssignment lockerAssignment = lockerAssignmentRepository.findByUserIdAndLockerId(user.getUserId(),
        locker.getId());

    if (LocalDateTime.now().isAfter(lockerAssignment.getAssignedAt().plusDays(lockerAssignment.getExpirationTimeInDay()))) {
      throw new RuntimeException("Locker assignment expired");
    }

    if (!Objects.equals(lockerAssignment.getOtp(), otpCode)) {
      throw new RuntimeException("Invalid OTP");
    }
    if (!Objects.equals(lockerAssignment.getLockerId(), locker.getId())) {
      throw new RuntimeException("Locker ID mismatch");
    }

    deleteByUserIdAndLockerId(user.getUserId(), locker.getId());
    lockerService.updateLockerStatusById(locker.getId(), LockerStatus.AVAILABLE);

    return true;
  }

  @Override
  public List<LockerAssignment> getAllLockerAssignmentsByLockerIds(List<String> lockerIds) {
    return null;
  }

  @Override
  public void deleteByUserIdAndLockerId(String userId, String lockerId) {
    lockerAssignmentRepository.deleteByUserIdAndLockerId(userId, lockerId);
  }
}
