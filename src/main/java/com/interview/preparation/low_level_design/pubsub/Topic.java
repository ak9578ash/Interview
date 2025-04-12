package com.interview.preparation.low_level_design.pubsub;

import com.interview.preparation.low_level_design.pubsub.model.Message;
import com.interview.preparation.low_level_design.pubsub.subscriber.Subscriber;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import lombok.Getter;

@Getter
public class Topic {
  private final String name;
  private final Set<Subscriber> subscribers;

  public Topic(String name) {
    this.name = name;
    this.subscribers = new CopyOnWriteArraySet<>();
  }


  public void addSubscriber(Subscriber subscriber) {
    subscribers.add(subscriber);
  }

  public void removeSubscriber(Subscriber subscriber) {
    subscribers.remove(subscriber);
  }

  public void publish(Message message) {
    for (Subscriber subscriber : subscribers) {
      subscriber.onMessage(message);
    }
  }
}
