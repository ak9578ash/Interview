package com.interview.preparation.low_level_design.cab_booking.exception;

public class UserNotFoundException extends Exception{
    public UserNotFoundException(String message){
        super(message);
    }

    @Override
    public String getMessage(){
        return super.getMessage();
    }
}
