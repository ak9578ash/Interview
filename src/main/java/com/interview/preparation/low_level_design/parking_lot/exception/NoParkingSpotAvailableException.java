package com.interview.preparation.low_level_design.parking_lot.exception;

public class NoParkingSpotAvailableException extends Exception{
    public NoParkingSpotAvailableException(String message){
        super(message);
    }

    @Override
    public String getMessage(){
        return super.getMessage();
    }
}
