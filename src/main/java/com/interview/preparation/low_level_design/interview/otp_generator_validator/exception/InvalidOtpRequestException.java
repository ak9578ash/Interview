package com.interview.preparation.low_level_design.interview.otp_generator_validator.exception;

public class InvalidOtpRequestException extends Exception{
    public InvalidOtpRequestException(String msg){
        super(msg);
    }

    @Override
    public String getMessage(){
        return super.getMessage();
    }
}
