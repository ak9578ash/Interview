package com.interview.preparation.design_patterns.singleton;

public class EarlyInitialization {
    //create an object of SingleObject
    private static final EarlyInitialization instance = new EarlyInitialization();

    //make the constructor private so that this class cannot be
    //instantiated
    private EarlyInitialization(){}

    //Get the only object available
    public static EarlyInitialization getInstance(){
        return instance;
    }

    public void getInitializationType(){
        System.out.println("EarlyInitialization");
    }
}