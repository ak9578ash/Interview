package com.interview.preparation.multi_threading.non_blocking_counter;

public class Demo {
  public static void main(String[] args) {

    AtomicCounter atomicCounter = new AtomicCounter();
    Thread th1 = Thread.ofPlatform()
        .name("Thread1")
        .start(
            atomicCounter::inc
        );

    Thread th2 = Thread.ofPlatform()
        .name("Thread2")
        .start(
            atomicCounter::inc
        );

    try {
      th1.join();
      th2.join();
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
    System.out.println("Final Count: " + atomicCounter.count());
  }
}
