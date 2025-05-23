package com.interview.preparation.low_level_design.pubsub;

import com.interview.preparation.low_level_design.pubsub.model.Message;
import com.interview.preparation.low_level_design.pubsub.subscriber.PrintSubscriber;
import com.interview.preparation.low_level_design.pubsub.subscriber.Subscriber;

public class Demo {
  public static void main(String[] args) {
    // Create topics
    Topic topic1 = new Topic("Topic1");
    Topic topic2 = new Topic("Topic2");

    // Create publishers
    Publisher publisher1 = new Publisher();
    Publisher publisher2 = new Publisher();

    // Create subscribers
    Subscriber subscriber1 = new PrintSubscriber("Subscriber1");
    Subscriber subscriber2 = new PrintSubscriber("Subscriber2");
    Subscriber subscriber3 = new PrintSubscriber("Subscriber3");

    publisher1.registerTopic(topic1);
    publisher2.registerTopic(topic2);

    // Subscribe to topics
    topic1.addSubscriber(subscriber1);
    topic1.addSubscriber(subscriber2);
    topic2.addSubscriber(subscriber2);
    topic2.addSubscriber(subscriber3);

    // Publish messages
    Thread publisher1Thread1 = Thread.ofVirtual()
        .name("publisher_1_thread_1")
        .unstarted(
            () -> {
              publisher1.publish(topic1, new Message("Message1 for Topic1"));
            }
        );

    Thread publisher1Thread2 = Thread.ofVirtual()
        .name("publisher_1_thread_2")
        .unstarted(
            () -> {
              publisher1.publish(topic1, new Message("Message2 for Topic1"));
            }
        );

    Thread publisher2Thread1 = Thread.ofVirtual()
        .name("publisher_2_thread_1")
        .unstarted(
            () -> {
              try {
                publisher2.publish(topic2, new Message("Message1 for Topic2"));
              }catch (IllegalStateException e) {
                System.out.println("Publisher2 can't publish to Topic1");
              }
            }
        );

    publisher1Thread1.start();
    publisher1Thread2.start();
    publisher2Thread1.start();

    // Wait for threads to finish
    try {
      publisher1Thread1.join();
      publisher1Thread2.join();
      publisher2Thread1.join();
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }

    // Unsubscribe from a topic
    topic1.removeSubscriber(subscriber2);

    // Publish more messages
    publisher1.publish(topic1, new Message("Message3 for Topic1"));
    publisher2.publish(topic2, new Message("Message2 for Topic2"));
  }
}
