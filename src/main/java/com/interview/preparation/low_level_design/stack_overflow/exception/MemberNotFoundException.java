package com.interview.preparation.low_level_design.stack_overflow.exception;

import javax.swing.text.html.ObjectView;

public class MemberNotFoundException extends Exception{
    public MemberNotFoundException(String msg){
        super(msg);
    }

    @Override
    public String getMessage(){
        return super.getMessage();
    }
}
