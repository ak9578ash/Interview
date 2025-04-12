package com.interview.preparation.designpatterns.builder;

public class Pepsi extends ColdDrink{
    @Override
    public String name() {
        return "Pepsi";
    }

    @Override
    public Float price() {
        return 12f;
    }
}
