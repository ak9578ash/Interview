package com.interview.preparation.designpatterns.observer;

public class ObserverMain {
    public static void main(String[] args) {
        CricketDataSubjectImpl cricketDataSubject = new CricketDataSubjectImpl(12,12);

        ObserverImpl1 observerImpl1 = new ObserverImpl1();
        ObserverImpl2 observerImpl2 = new ObserverImpl2();

        cricketDataSubject.addObserver(observerImpl1);
        cricketDataSubject.addObserver(observerImpl2);

        cricketDataSubject.changeData();

        cricketDataSubject.removeObserver(observerImpl1);

        cricketDataSubject.changeData();

    }
}
