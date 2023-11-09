package com.interview.preparation.design_patterns.observer;

import java.util.ArrayList;
import java.util.List;

public class CricketDataSubjectImpl implements Subject{
    private  int currentScore;
    private  int currentOver;
    private final List<Observer> observerList;

    public CricketDataSubjectImpl(int currentScore, int currentOver) {
        this.currentScore = currentScore;
        this.currentOver = currentOver;
        this.observerList = new ArrayList<>();
    }

    public void changeData(){
        this.currentScore = 20;
        this.currentOver = 23;
        notifyObserver();
    }

    @Override
    public void addObserver(Observer observer) {
        this.observerList.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        this.observerList.remove(observer);
    }

    @Override
    public void notifyObserver() {
        for(int i=0;i<observerList.size();i++){
            observerList.get(i).update(currentScore , currentOver);
        }
    }
}
