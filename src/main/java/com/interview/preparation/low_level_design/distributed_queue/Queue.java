package com.interview.preparation.low_level_design.distributed_queue;

import com.interview.preparation.low_level_design.distributed_queue.handler.TopicHandler;
import com.interview.preparation.low_level_design.distributed_queue.model.Message;
import com.interview.preparation.low_level_design.distributed_queue.model.Topic;
import com.interview.preparation.low_level_design.distributed_queue.model.observer.TopicSubscriber;
import lombok.NonNull;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Queue {
    private final Map<String, TopicHandler> topicHandlerMap;

    public Queue() {
        this.topicHandlerMap = new HashMap<>();
    }

    public Topic createTopic(@NonNull final String topicName) {
        final Topic topic = new Topic(topicName, UUID.randomUUID().toString());
        TopicHandler topicHandler = new TopicHandler(topic);
        topicHandlerMap.put(topic.getTopicId(), topicHandler);
        System.out.println("Created topic: " + topic.getTopicName());
        return topic;
    }

    public void subscribe(@NonNull final Topic topic , TopicSubscriber subscriber) { // add observer
        topic.addSubscriber(subscriber);
    }

    public void deSubscribe(Topic topic , TopicSubscriber subscriber){ // remove observer
        topic.removeSubscriber(subscriber);
    }
    public void publish(@NonNull final Topic topic, @NonNull final Message message) { // notify observer
        topic.addMessage(message);
        topicHandlerMap.get(topic.getTopicId()).publish(message);
    }
}
