package com.interview.preparation.multi_threading.questions.order_printing.implementation_1;

public class Demo {
  public static void main(String[] args) {
    Counter1 counter = new Counter1();

    Thread t1 = Thread.ofPlatform().name("T1")
        .unstarted(
            () -> {
              counter.printFirst();
            }
        );

    Thread t2 = Thread.ofPlatform().name("T2")
        .unstarted(
            () -> {
              try {
                counter.printSecond();
              } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
              }
            }
        );

    Thread t3 = Thread.ofPlatform().name("T3")
        .unstarted(
            () -> {
              try {
                counter.printThird();
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
