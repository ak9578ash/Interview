package com.interview.preparation.designpatterns.singleton;

public class SingletonPatternDemo {
    public static void main(String[] args) {

        EarlyInitialization object1 = EarlyInitialization.getInstance();
        object1.getInitializationType();

        LazyInitialization object2 = LazyInitialization.getInstance();
        object2.getInitializationType();
    }
}
