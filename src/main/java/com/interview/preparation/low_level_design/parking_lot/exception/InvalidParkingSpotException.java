package com.interview.preparation.low_level_design.parking_lot.exception;

public class InvalidParkingSpotException extends Exception{
    public InvalidParkingSpotException(String msg){
        super(msg);
    }

    @Override
    public String getMessage(){
        return super.getMessage();
    }
}
