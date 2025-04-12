package com.interview.preparation.designpatterns.builder;

public abstract class Burger implements Item{
    @Override
    public Packing packing(){
        return new Wrapper();
    }
}
