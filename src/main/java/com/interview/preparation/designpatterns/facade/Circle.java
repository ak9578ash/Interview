package com.interview.preparation.designpatterns.facade;

public class Circle implements Shape{
    @Override
    public void draw() {
        System.out.println("Circle is drawn");
    }
}
