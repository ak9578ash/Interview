package com.interview.preparation.low_level_design.messaging_app.models;

import java.util.UUID;

public class Message {
    private String id;
    private String content;

    public Message(String content){
        this.id = UUID.randomUUID().toString();
        this.content = content;
    }
}
