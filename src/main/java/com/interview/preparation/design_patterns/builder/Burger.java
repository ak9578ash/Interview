package com.interview.preparation.design_patterns.builder;

public abstract class Burger implements Item{
    @Override
    public Packing packing(){
        return new Wrapper();
    }
}
