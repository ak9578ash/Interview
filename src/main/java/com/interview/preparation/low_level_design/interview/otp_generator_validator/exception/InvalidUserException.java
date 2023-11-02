package com.interview.preparation.low_level_design.interview.otp_generator_validator.exception;

import com.interview.preparation.low_level_design.interview.dining.exception.InvalidBookingException;

public class InvalidUserException extends Exception{
    public InvalidUserException(String msg){
        super(msg);
    }

    @Override
    public String getMessage(){
        return super.getMessage();
    }
}
