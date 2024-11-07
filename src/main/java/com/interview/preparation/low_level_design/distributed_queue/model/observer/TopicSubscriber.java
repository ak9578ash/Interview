package com.interview.preparation.low_level_design.distributed_queue.model.observer;

import com.interview.preparation.low_level_design.distributed_queue.model.Message;
import lombok.Getter;
import java.util.UUID;

@Getter
public class TopicSubscriber {
    private final String id;
    private Message message;

    public TopicSubscriber() {
        this.id = UUID.randomUUID().toString();
    }

    public  void setMessage(Message message){
        this.message = message;
    }
}
