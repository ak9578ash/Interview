package com.interview.preparation.low_level_design.vending_machine.exception;

public class BadRequestException extends  Exception{
    public BadRequestException(String msg){
        super(msg);
    }

    @Override
    public String getMessage(){
        return super.getMessage() ;
    }
}
