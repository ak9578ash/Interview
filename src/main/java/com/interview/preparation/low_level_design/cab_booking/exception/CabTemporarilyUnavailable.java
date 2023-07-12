package com.interview.preparation.low_level_design.cab_booking.exception;

public class CabTemporarilyUnavailable extends  Exception{
    public CabTemporarilyUnavailable(String msg){
        super(msg);
    }

    @Override
    public String getMessage(){
        return super.getMessage();
    }
}
