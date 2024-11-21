package com.interview.preparation.multi_threading.blocking_queue_with_poison_pill;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadLocalRandom;

public class NumbersProducerWithPoisonPill implements Runnable {
  private final BlockingQueue<Integer> numbersQueue;
  private final int poisonPill;
  private final int poisonPillCount;

  public NumbersProducerWithPoisonPill(BlockingQueue<Integer> numbersQueue, int poisonPill, int poisonPillCount) {
    this.numbersQueue = numbersQueue;
    this.poisonPill = poisonPill;
    this.poisonPillCount = poisonPillCount;
  }

  public void run() {
    try {
      int num = 5; // Number of items to produce
      while (num > 0) {
        int number = ThreadLocalRandom.current().nextInt(100);
        numbersQueue.put(number);
        System.out.println(Thread.currentThread().getName() + " is producing: ");
        num--;
      }

      // Insert poison pills to signal consumers to stop
      for (int i = 0; i < poisonPillCount; i++) {
        numbersQueue.put(poisonPill);
      }
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }
}
