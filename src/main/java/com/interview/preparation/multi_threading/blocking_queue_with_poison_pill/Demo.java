package com.interview.preparation.multi_threading.blocking_queue_with_poison_pill;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Demo {
  public static void main(String[] args) {
    BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(5);
    int poisonPill = -1; // A special value to signal termination
    int consumerCount = 2;

    Thread producerThread = Thread.ofPlatform()
        .name("ProducerThread")
        .unstarted(
            new NumbersProducerWithPoisonPill(queue, poisonPill, consumerCount)
        );

    Thread consumerThread1 = Thread.ofPlatform()
        .name("ConsumerThread1")
        .unstarted(
            new NumbersConsumerWithPoisonPill(queue, poisonPill)
        );

    Thread consumerThread2 = Thread.ofPlatform()
        .name("ConsumerThread2")
        .unstarted(
            new NumbersConsumerWithPoisonPill(queue, poisonPill)
        );

    producerThread.start();
    consumerThread1.start();
    consumerThread2.start();

    try {
      producerThread.join();
      consumerThread1.join();
      consumerThread2.join();
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }
}