package com.interview.preparation.designpatterns.observer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Subscriber2 implements Observer{
    private final Topic topic;

    public Subscriber2(Topic topic) {
        this.topic = topic;
    }

    @Override
    public void update(String message) {
        log.info("Subscriber2 Consuming message: {} from topic: {}" , message, topic.getName());
    }
}
