package com.interview.preparation.design_patterns.decorator;

public class ShapeDecorator implements Shape{
    protected Shape decoratedShape;

    public ShapeDecorator(Shape decoratedShape) {
        this.decoratedShape = decoratedShape;
    }

    @Override
    public void draw() {
        decoratedShape.draw();
    }

    @Override
    public String area() {
        return decoratedShape.area();
    }
}
