package com.interview.preparation.designpatterns.observer;

// https://www.digitalocean.com/community/tutorials/observer-design-pattern-in-java
public class ObserverMain {
    public static void main(String[] args) {
        Topic topic = new Topic("PUBSUB_TOPIC");

        Subscriber1 subscriber1 = new Subscriber1(topic);
        Subscriber2 subscriber2 = new Subscriber2(topic);

        topic.addObserver(subscriber1);
        topic.addObserver(subscriber2);

        topic.changeData();

        topic.removeObserver(subscriber1);
        topic.changeData();
    }
}
