package com.interview.preparation.multi_threading.custom_blocking_queues;

public class Consumer implements Runnable {
  private final CustomBlockingQueue<Integer> customBlockingQueue;
  private final Integer limit;

  public Consumer(CustomBlockingQueue<Integer> customBlockingQueue, Integer limit) {
    this.customBlockingQueue = customBlockingQueue;
    this.limit = limit;
  }

  @Override
  public void run() {
    try {
      consumeNumbers();
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }

  private void consumeNumbers() throws InterruptedException {
    for (int i = 0; i < limit; i++) {
      customBlockingQueue.deQueue();
    }
  }
}
