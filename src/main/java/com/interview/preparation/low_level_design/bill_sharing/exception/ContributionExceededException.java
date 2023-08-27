package com.interview.preparation.low_level_design.bill_sharing.exception;

public class ContributionExceededException extends Exception{
    public ContributionExceededException(String message){
        super(message);
    }

    @Override
    public String getMessage(){
        return super.getMessage();
    }

}
