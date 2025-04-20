package com.interview.preparation.multi_threading.blocking_queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadLocalRandom;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NumbersProducer implements Runnable {
  private final BlockingQueue<Integer> numbersQueue;

  public NumbersProducer(BlockingQueue<Integer> numbersQueue) {
    this.numbersQueue = numbersQueue;
  }

  public void run() {
   try {
     int num = 10;
       while (num > 0) {
           int number = ThreadLocalRandom.current().nextInt();
           numbersQueue.put(number);
           log.info(Thread.currentThread().getName() + " is producing");
           num--;
       }
   }catch (InterruptedException e) {
       Thread.currentThread().interrupt();
   }
  }
}