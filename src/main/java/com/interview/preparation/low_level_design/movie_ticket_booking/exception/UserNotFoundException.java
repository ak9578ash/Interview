package com.interview.preparation.low_level_design.movie_ticket_booking.exception;

import com.interview.preparation.low_level_design.movie_ticket_booking.model.account.User;

public class UserNotFoundException extends  Exception{
    public UserNotFoundException(String message){
        super(message);
    }

    @Override
    public String getMessage(){
        return super.getMessage();
    }
}
