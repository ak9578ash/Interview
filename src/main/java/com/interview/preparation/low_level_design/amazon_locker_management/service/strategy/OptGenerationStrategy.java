package com.interview.preparation.low_level_design.amazon_locker_management.service.strategy;

public interface OptGenerationStrategy {
  String createOtp(String userId, String lockerId);
}
