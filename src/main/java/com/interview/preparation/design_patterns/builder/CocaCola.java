package com.interview.preparation.design_patterns.builder;

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
