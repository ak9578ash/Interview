package com.interview.preparation.multi_threading.custom_blocking_queues;

public class Consumer implements Runnable{
  private final CustomBlockingQueue<Integer> customBlockingQueue;
  private final int poisonPill;
  public Consumer(CustomBlockingQueue<Integer> customBlockingQueue, int poisonPill) {
    this.customBlockingQueue = customBlockingQueue;
    this.poisonPill = poisonPill;
  }
  @Override
  public void run() {
    while (true) {
      try {
        Integer number = customBlockingQueue.deQueue();
        if (number.equals(poisonPill)) {
          return;
        }
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
  }
}
