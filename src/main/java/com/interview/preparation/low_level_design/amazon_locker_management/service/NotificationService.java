package com.interview.preparation.low_level_design.amazon_locker_management.service;

import com.interview.preparation.low_level_design.amazon_locker_management.model.User;

public interface NotificationService {
  void sendOtpNotification(User user, String otp);
  void sendItemRefundNotification(String userId, String itemId);
}
