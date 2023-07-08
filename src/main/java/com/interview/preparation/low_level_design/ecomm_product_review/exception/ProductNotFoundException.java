package com.interview.preparation.low_level_design.ecomm_product_review.exception;

public class ProductNotFoundException extends Exception{
    public ProductNotFoundException(String message){
        super(message);
    }

    @Override
    public String getMessage(){
        return super.getMessage();
    }
}
