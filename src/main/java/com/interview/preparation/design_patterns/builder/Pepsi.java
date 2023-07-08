package com.interview.preparation.design_patterns.builder;

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
