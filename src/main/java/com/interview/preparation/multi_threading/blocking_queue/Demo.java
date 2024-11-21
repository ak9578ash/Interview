package com.interview.preparation.multi_threading.blocking_queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Demo {
  public static void main(String[] args) {
    BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(5);

    Thread producerThread = Thread.ofPlatform()
        .name("ProducerThread")
        .unstarted(
            new NumbersProducer(queue)
        );

    Thread consumerThread1 = Thread.ofPlatform()
        .name("ConsumerThread1")
        .unstarted(
            new NumbersConsumer(queue)
        );

    Thread consumerThread2 = Thread.ofPlatform()
        .name("ConsumerThread2")
        .unstarted(
            new NumbersConsumer(queue)
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
