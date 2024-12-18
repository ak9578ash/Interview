package com.interview.preparation.low_level_design.distributed_queue.model;

import com.interview.preparation.low_level_design.distributed_queue.model.observer.TopicSubscriber;
import lombok.Getter;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Topic {
    private final String topicName;
    private final String topicId;
    private final List<Message> messages;
    private final List<TopicSubscriber> subscribers;

    public Topic(@NonNull final String topicName, @NonNull final String topicId) {
        this.topicName = topicName;
        this.topicId = topicId;
        this.messages = new ArrayList<>();
        this.subscribers = new ArrayList<>();
    }

    public synchronized void addMessage(@NonNull final Message message) {
        messages.add(message);
    }

    public void addSubscriber(@NonNull final TopicSubscriber subscriber) {
        subscribers.add(subscriber);
    }

    public void removeSubscriber(TopicSubscriber subscriber){
        subscribers.remove(subscriber);
    }
}
