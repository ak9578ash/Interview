package com.interview.preparation.designpatterns.proxy;

public class ProxyPatternDemo {
    public static void main(String[] args) {
        ProxyImage image = new ProxyImage("test.png");
        image.Display();
        System.out.println();
        image.Display();
    }
}
