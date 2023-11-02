package com.interview.preparation.low_level_design.interview.otp_generator_validator.repository;

import com.interview.preparation.low_level_design.interview.otp_generator_validator.exception.InvalidUserException;
import com.interview.preparation.low_level_design.interview.otp_generator_validator.model.OtpData;

public interface OtpRepository {
    void saveOTP(String userId , OtpData otpData);
    OtpData getOTP(String userId) throws InvalidUserException;
}
