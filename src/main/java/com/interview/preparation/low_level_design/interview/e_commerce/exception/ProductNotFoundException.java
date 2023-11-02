package com.interview.preparation.low_level_design.interview.e_commerce.exception;

public class ProductNotFoundException extends Exception{
    public ProductNotFoundException(String msg){
        super(msg);
    }

    @Override
    public String getMessage(){
        return super.getMessage();
    }
}
