package com.interview.preparation.designpatterns.decorator;

public class Rectangle implements Shape{
    @Override
    public void draw() {
        System.out.println("Shape: Rectangle");
    }

    @Override
    public String area() {
        return "rectangle area";
    }


}
