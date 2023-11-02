package com.interview.preparation.low_level_design.interview.dining.exception;

public class InvalidBookingException extends Exception{
    public InvalidBookingException(String msg){
        super(msg);
    }

    @Override
    public String getMessage(){
        return super.getMessage();
    }
}
