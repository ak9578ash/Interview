package com.interview.preparation.designpatterns.builder;

public class CocaCola extends ColdDrink{
    @Override
    public String name() {
        return "Coca Cola";
    }

    @Override
    public Float price() {
        return 123.9f;
    }
}
