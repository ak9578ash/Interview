package com.interview.preparation.low_level_design.parking_lot.exception;

public class InvalidParkingLotException extends Exception{
    public InvalidParkingLotException(String message){
        super(message);
    }

    @Override
    public String getMessage(){
        return super.getMessage();
    }
}
