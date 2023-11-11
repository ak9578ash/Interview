package com.interview.preparation.low_level_design.interview.doctor_appointment.exception;

public class InvalidSlotException extends Exception{
    public InvalidSlotException(String msg){
        super(msg);
    }

    @Override
    public String getMessage(){
        return super.getMessage();
    }
}
