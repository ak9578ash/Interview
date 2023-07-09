package com.interview.preparation.low_level_design.movie_ticket_booking.exception;

public class SeatTemporarilyUnavailableException extends Exception{
    public SeatTemporarilyUnavailableException(String message){
        super(message);
    }

    @Override
    public String getMessage(){
        return super.getMessage();
    }
}
