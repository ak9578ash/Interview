package com.interview.preparation.design_patterns.observer;

public class ObserverImpl2 implements Observer{
    @Override
    public void update(int currentScore, int currentOver) {
        System.out.println("observer2 is updated");
    }
}
