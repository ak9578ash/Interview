package com.interview.preparation.low_level_design.distributed_queue.model;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class Message {
    private final String id;
    private final String content;
    private final LocalDateTime timestamp;

    public Message(String content){
        this.id = UUID.randomUUID().toString();
        this.content = content;
        this.timestamp = LocalDateTime.now();
    }
}
