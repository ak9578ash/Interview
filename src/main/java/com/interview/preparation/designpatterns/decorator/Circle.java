package com.interview.preparation.designpatterns.decorator;

public class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Shape: Circle");
    }

    @Override
    public String area() {
        return "circle area";
    }
}
