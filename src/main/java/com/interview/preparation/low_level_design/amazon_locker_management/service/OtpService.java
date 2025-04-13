package com.interview.preparation.low_level_design.amazon_locker_management.service;

public interface OtpService {
  String generateOtp(String userId, String lockerId);
}
