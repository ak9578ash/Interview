package com.interview.preparation.low_level_design.messaging_app.exception;

public class UserNotFoundException extends Exception{
    public UserNotFoundException(String message){
        super(message);
    }

    @Override
    public String getMessage(){
        return this.getMessage();
    }
}
