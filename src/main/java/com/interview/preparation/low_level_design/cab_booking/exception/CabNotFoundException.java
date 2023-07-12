package com.interview.preparation.low_level_design.cab_booking.exception;

public class CabNotFoundException extends  Exception{
    public CabNotFoundException(String msg){
        super(msg);
    }

    @Override
    public String getMessage(){
        return super.getMessage();
    }
}
