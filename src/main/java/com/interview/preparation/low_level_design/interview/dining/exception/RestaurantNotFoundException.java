package com.interview.preparation.low_level_design.interview.dining.exception;

public class RestaurantNotFoundException extends Exception{
    public RestaurantNotFoundException(String msg){
        super(msg);
    }

    @Override
    public String getMessage(){
        return super.getMessage();
    }
}
