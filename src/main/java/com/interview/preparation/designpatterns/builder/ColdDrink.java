package com.interview.preparation.designpatterns.builder;

public abstract class ColdDrink implements Item{

    @Override
    public Packing packing(){
        return new Bottle();
    }
}
