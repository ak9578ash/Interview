package com.interview.preparation.design_patterns.decorator;

public class RedShapeDecorator extends ShapeDecorator{

    public RedShapeDecorator(Shape decoratedShape) {
        super(decoratedShape);
    }

    private void setRedBorder(Shape decoratedShape){ // new method
        System.out.println("Border Color: Red");
    }


    @Override
    public void draw() { // already existing method
        decoratedShape.draw();
        setRedBorder(decoratedShape);
    }

}
