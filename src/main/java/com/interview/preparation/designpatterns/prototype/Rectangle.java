package com.interview.preparation.designpatterns.prototype;

public class Rectangle extends Shape {
    public Rectangle() {
        this.type = "Rectangle";
    }

    @Override
    void draw() {
        System.out.println("Rectangle Is Drawn");
    }
}
