package com.interview.preparation.design_patterns.observer;

public class ObserverPatternDemo {
    public static void main(String[] args) {
        Subject subject = new Subject();

       Observer binaryObserver =  new BinaryObserver(subject);
       subject.attach(binaryObserver);



        System.out.println("First state change: 15");
        subject.setState(15);

        System.out.println("Second state change: 10");
        subject.setState(10);
    }
}
