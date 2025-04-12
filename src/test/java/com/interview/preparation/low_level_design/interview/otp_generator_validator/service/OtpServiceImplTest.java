package com.interview.preparation.low_level_design.interview.otp_generator_validator.service;

import com.interview.preparation.low_level_design.interview.otp_generator_validator.exception.InvalidOtpRequestException;
import com.interview.preparation.low_level_design.interview.otp_generator_validator.exception.InvalidUserException;
import com.interview.preparation.low_level_design.interview.otp_generator_validator.model.OtpData;
import com.interview.preparation.low_level_design.interview.otp_generator_validator.repository.OtpRepository;
import com.interview.preparation.low_level_design.interview.otp_generator_validator.service.impl.OtpServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;


public class OtpServiceImplTest {
    @Mock
    private OtpRepository otpRepository;

    private OtpServiceImpl otpService;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        otpService = new OtpServiceImpl(otpRepository);
    }

    @Test(expected = InvalidOtpRequestException.class)
    public void testRequestOTP() throws InvalidUserException, InvalidOtpRequestException {
        // Mock the behavior of the repository to save an OTP
//        otpService = new OtpServiceImpl(otpRepository);
        long currTime = System.currentTimeMillis();
        OtpData otpData = new OtpData("123456", currTime - 62*100);

        Mockito.when(otpRepository.getOTP("testUser")).thenReturn(otpData);

        // Call the method under test
        otpService.requestOTP("testUser");


    }

    @Test
    public void testValidateOTP() throws InvalidUserException {
        // Mock the behavior of the repository to retrieve an OTP
        OtpData otpData = new OtpData("123456", System.currentTimeMillis());
        Mockito.when(otpRepository.getOTP("testUser")).thenReturn(otpData);

        // Call the method under test with a valid OTP
        boolean isValid = otpService.validateOTP("testUser", "123456");

        // Verify that the OTP is considered valid
        assertTrue(isValid);

        // Call the method under test with an invalid OTP
        isValid = otpService.validateOTP("testUser", "654321");

        // Verify that the OTP is considered invalid
        assertFalse(isValid);

        // Call the method under test with an expired OTP
        otpData = new OtpData("789012", System.currentTimeMillis() - (2 * 60 * 1000 + 1));
        Mockito.when(otpRepository.getOTP("testUser")).thenReturn(otpData);

        isValid = otpService.validateOTP("testUser", "789012");

        // Verify that the OTP is considered expired and invalid
        assertFalse(isValid);
    }
}
