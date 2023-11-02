package com.interview.preparation.low_level_design.interview.otp_generator_validator;

import com.interview.preparation.low_level_design.interview.otp_generator_validator.exception.InvalidUserException;
import com.interview.preparation.low_level_design.interview.otp_generator_validator.model.OtpData;
import com.interview.preparation.low_level_design.interview.otp_generator_validator.repository.OtpRepository;
import com.interview.preparation.low_level_design.interview.otp_generator_validator.repository.impl.OtpRepositoryImpl;
import com.interview.preparation.low_level_design.interview.otp_generator_validator.service.OtpService;
import com.interview.preparation.low_level_design.interview.otp_generator_validator.service.impl.OtpServiceImpl;
import com.interview.preparation.low_level_design.interview.otp_generator_validator.util.PostgreSQLClient;

public class Main {
    public static OtpRepository otpRepository;
    public static OtpService otpService;
    public static PostgreSQLClient postgreSQLClient;
    public static void main(String[] args) throws InvalidUserException {
        postgreSQLClient = new PostgreSQLClient();
        otpRepository = new OtpRepositoryImpl(postgreSQLClient);
        otpService = new OtpServiceImpl(otpRepository);

        // STEP 1: generate OTP
//        OtpData userOtp = otpService.requestOTP("akash_test");

        // Step 2: Validate OTP
        boolean isValid = otpService.validateOTP("user123", "673215");
        if (isValid) {
            System.out.println("OTP is valid.");
        } else {
            System.out.println("OTP is either invalid or expired.");
        }
    }
}
