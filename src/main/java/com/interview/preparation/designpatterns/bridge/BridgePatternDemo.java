package com.interview.preparation.designpatterns.bridge;

public class BridgePatternDemo {
    public static void main(String[] args) {
        Shape redCircle = new Circle(100,100, 10, new DrawRed());
        Shape greenCircle = new Circle(100,100, 10, new DrawGreen());

        redCircle.draw();
        greenCircle.draw();

        Shape redSquare = new Square(10,new DrawRed());
        redSquare.draw();
    }
}
