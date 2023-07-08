package com.interview.preparation.low_level_design.parking_lot.exception;

public class InvalidParkingFloorException extends Exception{
    public InvalidParkingFloorException(String message){
        super(message);
    }

    @Override
    public String getMessage(){
        return super.getMessage();
    }
}
