package com.interview.preparation.designpatterns.bridge;

public class Square extends Shape {
    private int side;

    public Square(int side, DrawAPI drawAPI) {
        super(drawAPI);
        this.side = side;
    }

    @Override
    public void draw() {
      drawAPI.drawSquare(side);
    }
}
