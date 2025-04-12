package com.interview.preparation.designpatterns.builder;

public class ChickenBurger extends Burger{
    @Override
    public String name() {
        return "Chicken Burger";
    }

    @Override
    public Float price() {
        return 25.4f;
    }
}
