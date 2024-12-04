package com.interview.preparation.multi_threading.questions.order_printing.implementation_1;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Counter1 {
  private int count;

  public Counter1() {
    this.count = 1;
  }

  public synchronized void printFirst() {
    log.info("FIRST");
    count++;
    this.notifyAll();
  }

  public synchronized void printSecond() throws InterruptedException {
    while (count != 2) {
      this.wait();
    }
    log.info("SECOND");
    count++;
    this.notifyAll();
  }

  public synchronized void printThird() throws InterruptedException {
    while (count != 3) {
      this.wait();
    }
    log.info("THIRD");
  }
}
