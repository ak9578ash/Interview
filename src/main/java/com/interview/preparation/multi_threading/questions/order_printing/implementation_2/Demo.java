package com.interview.preparation.multi_threading.questions.order_printing.implementation_2;

import java.util.concurrent.CountDownLatch;

public class Demo {
  public static void main(String[] args) {
    CountDownLatch latch1 = new CountDownLatch(1);
    CountDownLatch latch2 = new CountDownLatch(1);

    Counter2 counter2 = new Counter2(latch1, latch2);

    Thread t1 = Thread.ofPlatform().name("t1")
        .unstarted(
            () -> {
              counter2.printFirst();
            }
        );

    Thread t2 = Thread.ofPlatform().name("t2")
        .unstarted(
            () -> {
              try {
                counter2.printSecond();
              } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
              }
            }
        );

    Thread t3 = Thread.ofPlatform().name("t3")
        .unstarted(
            () -> {
              try {
                counter2.printThird();
              } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
              }
            }
        );

    t1.start();
    t2.start();
    t3.start();
  }
}