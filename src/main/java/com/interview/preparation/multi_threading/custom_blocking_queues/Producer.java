package com.interview.preparation.multi_threading.custom_blocking_queues;

public class Producer implements Runnable{
  private final CustomBlockingQueue<Integer> customBlockingQueue;
  private final int poisonPill;
  private final int poisonPillPerProducer;
  public Producer(CustomBlockingQueue<Integer> customBlockingQueue, int poisonPill, int poisonPillPerProducer) {
    this.customBlockingQueue = customBlockingQueue;
    this.poisonPill = poisonPill;
    this.poisonPillPerProducer = poisonPillPerProducer;
  }

  @Override
  public void run() {
    try {
      generateNumbers();
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  private void generateNumbers() throws InterruptedException {
    for (int i = 0; i < 10; i++) {
      customBlockingQueue.enQueue(1);
    }

    for (int j = 0; j < poisonPillPerProducer; j++) {
      customBlockingQueue.enQueue(poisonPill);
    }
  }
}
