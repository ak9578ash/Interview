package com.interview.preparation.multi_threading.custom_blocking_queues;

public class Producer implements Runnable {
  private final CustomBlockingQueue<Integer> customBlockingQueue;
  private final Integer limit;

  public Producer(CustomBlockingQueue<Integer> customBlockingQueue, Integer limit) {
    this.customBlockingQueue = customBlockingQueue;
    this.limit = limit;
  }

  @Override
  public void run() {
    try {
      produceNumbers();
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }

  private void produceNumbers() throws InterruptedException {
    for (int i = 0; i < limit; i++) {
      customBlockingQueue.enQueue(i);
    }
  }
}
