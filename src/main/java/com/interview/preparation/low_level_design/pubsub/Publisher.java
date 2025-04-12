package com.interview.preparation.low_level_design.pubsub;

import com.interview.preparation.low_level_design.pubsub.model.Message;
import java.util.HashSet;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Publisher {
  private final Set<Topic> topics;

  public Publisher() {
    this.topics = new HashSet<>();
  }

  public void registerTopic(Topic topic) {
    topics.add(topic);
  }

  public void publish(Topic topic, Message message) {
    if (!topics.contains(topic)) {
      log.error("This publisher can't publish to topic: " + topic.getName());
      throw new IllegalArgumentException("This publisher can't publish to topic: " + topic.getName());
    }
    topic.publish(message);
  }
}