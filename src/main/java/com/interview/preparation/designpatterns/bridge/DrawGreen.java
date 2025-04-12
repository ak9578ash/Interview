package com.interview.preparation.designpatterns.bridge;

public class DrawGreen implements DrawAPI {
    @Override
    public void drawCircle(int radius, int x, int y) {
        System.out.println("Drawing Circle[ color: green, radius: " + radius + ", x: " + x + ", " + y + "]");
    }

    @Override
    public void drawSquare(int side) {
        System.out.println("Drawing Green Square of side :" + side);
    }
}
