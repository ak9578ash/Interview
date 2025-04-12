package com.interview.preparation.designpatterns.builder;

public class BuilderPatternDemo {
    public static void main(String[] args) {
        MealBuilder mealBuilder = new MealBuilder();
        Meal vegMeal = mealBuilder.prepareVegMeal();


        vegMeal.showItem();
        System.out.println(vegMeal.getCost());
    }
}
