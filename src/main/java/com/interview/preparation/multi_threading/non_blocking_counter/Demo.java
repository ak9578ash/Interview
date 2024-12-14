package com.interview.preparation.multi_threading.non_blocking_counter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Demo {
  public static void main(String[] args) {

    AtomicCounter atomicCounter = new AtomicCounter();
    Thread th1 = Thread.ofVirtual()
        .name("Thread1")
        .start(
            () -> {
              atomicCounter.inc();
            }
        );

    Thread th2 = Thread.ofVirtual()
        .name("Thread2")
        .start(
            () -> {
              atomicCounter.inc();
            }
        );

    try {
      th1.join();
      th2.join();
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
    log.info("Final Count: " + atomicCounter.count());
  }
}
