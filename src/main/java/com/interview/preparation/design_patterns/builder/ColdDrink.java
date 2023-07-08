package com.interview.preparation.design_patterns.builder;

public abstract class ColdDrink implements Item{

    @Override
    public Packing packing(){
        return new Bottle();
    }
}
