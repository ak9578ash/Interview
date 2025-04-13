package com.interview.preparation.designpatterns.abstractfactory;

public class PC implements Computer {
    @Override
    public void createComputer() {
        System.out.println("PC created");
    }
}
