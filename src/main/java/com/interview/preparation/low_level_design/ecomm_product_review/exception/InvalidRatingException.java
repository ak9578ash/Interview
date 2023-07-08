package com.interview.preparation.low_level_design.ecomm_product_review.exception;

public class InvalidRatingException extends Exception{
    public InvalidRatingException(String message){
        super(message);
    }

    @Override
    public String getMessage(){
        return super.getMessage();
    }
}
