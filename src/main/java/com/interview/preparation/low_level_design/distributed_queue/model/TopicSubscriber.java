package com.interview.preparation.low_level_design.distributed_queue.model;

import lombok.Getter;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
public class TopicSubscriber {
    private final String id;
    private final AtomicInteger offset;

    public TopicSubscriber() {
        this.id = UUID.randomUUID().toString();
        this.offset = new AtomicInteger(0);
    }
}
