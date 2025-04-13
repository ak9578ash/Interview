package com.interview.preparation.designpatterns.abstractfactory;

public class Server implements Computer {
    @Override
    public void createComputer() {
        System.out.println("Server created");
    }
}
