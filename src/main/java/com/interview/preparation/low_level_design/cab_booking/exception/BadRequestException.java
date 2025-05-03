package com.interview.preparation.low_level_design.cab_booking.exception;

public class BadRequestException extends  Exception{
    public BadRequestException(){
        super();
    }

    public BadRequestException(String message){
        super(message);
    }
}
