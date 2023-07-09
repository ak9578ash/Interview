package com.interview.preparation.low_level_design.movie_ticket_booking.exception;

public class InvalidSeatException extends  Exception{
    public InvalidSeatException(String message){
        super(message);
    }

    @Override
    public String getMessage(){
        return super.getMessage();
    }
}
