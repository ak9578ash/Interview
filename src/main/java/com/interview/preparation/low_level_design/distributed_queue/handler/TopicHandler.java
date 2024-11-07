package com.interview.preparation.low_level_design.distributed_queue.handler;

import com.interview.preparation.low_level_design.distributed_queue.model.Message;
import com.interview.preparation.low_level_design.distributed_queue.model.Topic;

public class TopicHandler {
    private final Topic topic;

    public TopicHandler(Topic topic) {
        this.topic = topic;
    }

    public void publish(Message message) {
        // to publish to all the subscriber of the topic
        for(int i=0;i<topic.getSubscribers().size();i++){
            topic.getSubscribers().get(i).setMessage(message);
        }
    }
}
