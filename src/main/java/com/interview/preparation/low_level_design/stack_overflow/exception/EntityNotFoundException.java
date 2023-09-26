package com.interview.preparation.low_level_design.stack_overflow.exception;

public class EntityNotFoundException extends Exception{
    public EntityNotFoundException(String msg){
        super(msg);
    }

    @Override
    public String getMessage(){
        return super.getMessage();
    }
}
