package com.interview.preparation.multi_threading.custom_blocking_queues;

public class Demo {
  public static void main(String[] args) {
    CustomBlockingQueue<Integer> customBlockingQueue = new CustomBlockingQueue<>(5);

    Thread th1 = Thread.ofPlatform()
        .name("Producer")
        .start(new Producer(customBlockingQueue, 50));

    Thread th2 = Thread.ofPlatform()
        .name("Consumer1")
        .start(new Consumer(customBlockingQueue, 25));

    Thread th3 = Thread.ofPlatform()
        .name("Consumer2")
        .start(new Consumer(customBlockingQueue, 25));

    try {
      th1.join();
      th2.join();
      th3.join();
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }
}