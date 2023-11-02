package com.interview.preparation.low_level_design.interview.e_commerce.exception;

public class CategoryNotFoundException extends Exception{
    public CategoryNotFoundException(String msg){
        super(msg);
    }

    @Override
    public String getMessage(){
        return super.getMessage();
    }
}
