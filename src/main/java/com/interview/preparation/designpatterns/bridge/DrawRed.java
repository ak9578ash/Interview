package com.interview.preparation.designpatterns.bridge;

public class DrawRed implements DrawAPI {
    @Override
    public void drawCircle(int radius, int x, int y) {
        System.out.println("Drawing Circle[ color: red, radius: " + radius + ", x: " + x + ", " + y + "]");
    }

    @Override
    public void drawSquare(int side) {
        System.out.println("Drawing Red Square of Side: "+ side);
    }
}
