package com.interview.preparation.low_level_design.amazon_locker_management.service.impl;

import com.interview.preparation.low_level_design.amazon_locker_management.service.OtpService;
import com.interview.preparation.low_level_design.amazon_locker_management.service.strategy.OptGenerationStrategy;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OtpServiceImpl implements OtpService {
  private final OptGenerationStrategy optGenerationStrategy;
  @Override
  public String generateOtp(String userId, String lockerId) {
    return optGenerationStrategy.createOtp(userId, lockerId);
  }
}
