package com.interview.preparation.designpatterns.builder;

public class MealBuilder {
    public Meal prepareVegMeal(){
        Meal vegMeal = new Meal();
        vegMeal.addItem(new VegBurger());
        vegMeal.addItem(new Pepsi());
        return vegMeal;
    }

    public Meal prepareNonVegMeal(){
        Meal nonVegMeal = new Meal();
        nonVegMeal.addItem(new ChickenBurger());
        nonVegMeal.addItem(new CocaCola());
        return nonVegMeal;
    }

}
