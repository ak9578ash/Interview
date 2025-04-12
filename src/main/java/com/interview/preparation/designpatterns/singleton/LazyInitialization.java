package com.interview.preparation.designpatterns.singleton;

public class LazyInitialization {
    public static LazyInitialization instance ;
    private LazyInitialization(){}

    public static synchronized LazyInitialization getInstance() {
        if (instance == null) {
            instance = new LazyInitialization();
        }
        return instance;
    }

    public void getInitializationType(){
        System.out.println("LazyInitialization");
    }
}