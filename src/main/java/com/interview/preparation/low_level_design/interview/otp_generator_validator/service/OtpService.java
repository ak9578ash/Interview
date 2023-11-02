package com.interview.preparation.low_level_design.interview.otp_generator_validator.service;

import com.interview.preparation.low_level_design.interview.otp_generator_validator.exception.InvalidOtpRequestException;
import com.interview.preparation.low_level_design.interview.otp_generator_validator.exception.InvalidUserException;
import com.interview.preparation.low_level_design.interview.otp_generator_validator.model.OtpData;

public interface OtpService {
    OtpData requestOTP(String userId) throws InvalidUserException, InvalidOtpRequestException;
    Boolean validateOTP(String userId, String otpCode) throws InvalidUserException;

}
