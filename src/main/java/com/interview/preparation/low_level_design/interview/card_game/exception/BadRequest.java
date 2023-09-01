package com.interview.preparation.low_level_design.interview.card_game.exception;

public class BadRequest extends Exception{
    public BadRequest(String msg){
        super(msg);
    }

    @Override
    public String getMessage(){
        return this.getMessage();
    }
}
