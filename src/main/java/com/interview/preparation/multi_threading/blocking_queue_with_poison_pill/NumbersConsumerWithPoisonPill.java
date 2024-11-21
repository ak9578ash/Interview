package com.interview.preparation.multi_threading.blocking_queue_with_poison_pill;

import java.util.concurrent.BlockingQueue;


public class NumbersConsumerWithPoisonPill implements Runnable {
  private final BlockingQueue<Integer> numberQueue;
  private final int poisonPill;

  public NumbersConsumerWithPoisonPill(BlockingQueue<Integer> numberQueue, int poisonPill) {
    this.numberQueue = numberQueue;
    this.poisonPill = poisonPill;
  }

  public void run() {
    try {
      int num = 5;
      while (num > 0) {
        Integer number = numberQueue.take();
        if (number.equals(poisonPill)) {
          System.out.println(Thread.currentThread().getName() + " received poison pill. Exiting.");
          break;
        }
        System.out.println(Thread.currentThread().getName() + " is consuming: ");
        num--;
      }
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }
}
