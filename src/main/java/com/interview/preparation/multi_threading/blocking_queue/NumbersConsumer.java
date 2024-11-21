package com.interview.preparation.multi_threading.blocking_queue;

import java.util.concurrent.BlockingQueue;


public class NumbersConsumer implements Runnable {
  private final BlockingQueue<Integer> numberQueue;

  public NumbersConsumer(BlockingQueue<Integer> numberQueue) {
    this.numberQueue = numberQueue;
  }

  public void run() {
    try {
      int num = 5;
      while (num > 0) {
        numberQueue.take();
        System.out.println(Thread.currentThread().getName() + " is consuming");
        num--;
      }
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }
}
