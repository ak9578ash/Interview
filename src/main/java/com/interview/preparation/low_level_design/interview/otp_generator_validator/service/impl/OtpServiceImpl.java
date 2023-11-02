package com.interview.preparation.low_level_design.interview.otp_generator_validator.service.impl;

import com.interview.preparation.low_level_design.interview.otp_generator_validator.exception.InvalidOtpRequestException;
import com.interview.preparation.low_level_design.interview.otp_generator_validator.exception.InvalidUserException;
import com.interview.preparation.low_level_design.interview.otp_generator_validator.model.OtpData;
import com.interview.preparation.low_level_design.interview.otp_generator_validator.repository.OtpRepository;
import com.interview.preparation.low_level_design.interview.otp_generator_validator.service.OtpService;

import java.time.LocalDateTime;

public class OtpServiceImpl implements OtpService {
    private static final int OTP_EXPIRATION_TIME = 2 * 60 * 1000;
    private static final int RESEND_EXPIRATION_TIME = 60 * 1000;
    private final OtpRepository otpRepository;

    public OtpServiceImpl(OtpRepository otpRepository) {
        this.otpRepository = otpRepository;
    }

    @Override
    public OtpData requestOTP(String userId) throws InvalidUserException, InvalidOtpRequestException {
        OtpData otpData = otpRepository.getOTP(userId);
        if(otpData != null && checkResendCondition(otpData)){
            throw new InvalidOtpRequestException(String.format("please wait for %d min before requesting any new OTP",RESEND_EXPIRATION_TIME/1000*60));
        }

        // Generate a random OTP
        String otpCode = generateOTP();

        // Save OTP data to the database
        otpData = new OtpData(otpCode, System.currentTimeMillis());
        // TODO : to add the otp in the cache
        otpRepository.saveOTP(userId, otpData);

        // Send OTP to the user (you can implement this part)
        sendOTPToUser(userId, otpCode);
        return otpData;
    }
    private Boolean checkResendCondition(OtpData otpData){
        long currSystemTime = System.currentTimeMillis();
        return currSystemTime - otpData.getCreationTime() < RESEND_EXPIRATION_TIME;
    }

    @Override
    public Boolean validateOTP(String userId, String otpCode) throws InvalidUserException {
        //TODO: add cache to fetch the data
        OtpData otpData = otpRepository.getOTP(userId);

        if (otpData == null || isOTPExpired(otpData)) {
            return false;
        }

        return otpData.getCode().equals(otpCode);
    }

    private boolean isOTPExpired(OtpData otpData) {
        long currentTime = System.currentTimeMillis();
        return (currentTime - otpData.getCreationTime()) > OTP_EXPIRATION_TIME;
    }

    // Generate a random 6-digit OTP
    private String generateOTP() {
        int min = 100000;
        int max = 999999;
        return String.valueOf(min + (int)(Math.random() * ((max - min) + 1)));
    }

    // Simulated method to send OTP to the user (replace with your implementation)
    private void sendOTPToUser(String userId, String otpCode) {
        System.out.println("Sending OTP " + otpCode + " to " + userId);
    }
}
