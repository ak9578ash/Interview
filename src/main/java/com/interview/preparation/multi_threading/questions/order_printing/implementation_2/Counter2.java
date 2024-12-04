package com.interview.preparation.multi_threading.questions.order_printing.implementation_2;

import java.util.concurrent.CountDownLatch;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Counter2 {
  private final CountDownLatch latch1;
  private final CountDownLatch latch2;

  public Counter2(CountDownLatch latch1, CountDownLatch latch2) {
    this.latch1 = latch1;
    this.latch2 = latch2;
  }


  public void printFirst() {
    log.info("FIRST");
    latch1.countDown();
  }

  public void printSecond() throws InterruptedException {
    latch1.await();
    log.info("SECOND");
    latch2.countDown();
  }

  public void printThird() throws InterruptedException {
    latch2.await();
    log.info("THIRD");
  }
}
