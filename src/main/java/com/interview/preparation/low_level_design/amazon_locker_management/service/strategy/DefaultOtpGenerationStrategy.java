package com.interview.preparation.low_level_design.amazon_locker_management.service.strategy;

public class DefaultOtpGenerationStrategy implements OptGenerationStrategy{
  @Override
  public String createOtp(String userId, String lockerId) {
    // some method to generate OTP
    return null;
  }
}
