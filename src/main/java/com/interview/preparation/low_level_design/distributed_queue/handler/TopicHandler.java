package com.interview.preparation.low_level_design.distributed_queue.handler;

import com.interview.preparation.low_level_design.distributed_queue.model.Topic;

public class TopicHandler {
    private final Topic topic;

    public TopicHandler(Topic topic) {
        this.topic = topic;
    }

    public void publish() {
        // to publish to all the subscriber of the topic
    }
}
