package com.interview.preparation.low_level_design.messaging_app.exception;

public class GroupNotFoundException extends Exception{
    public GroupNotFoundException(String msg){
        super(msg);
    }

    @Override
    public String getMessage(){
        return super.getMessage();
    }
}
