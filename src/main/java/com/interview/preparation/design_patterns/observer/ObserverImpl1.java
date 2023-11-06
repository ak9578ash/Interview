package com.interview.preparation.design_patterns.observer;

public class ObserverImpl1 implements Observer{
    @Override
    public void update(int currentScore, int currentOver) {
        System.out.println("observer1 is updated");
    }
}
