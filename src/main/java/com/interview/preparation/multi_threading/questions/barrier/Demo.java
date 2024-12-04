package com.interview.preparation.multi_threading.questions.barrier;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Demo {
  public static void main(String[] args) throws InterruptedException {
    Barrier barrier = new Barrier(2);

    Thread t1 = Thread.ofPlatform().name("t1").unstarted(
        () -> {
          log.info("Thread 1 is waiting");
          try {
            barrier.await();
          } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
          }
          log.info("Thread 1 is released");
        }
    );

    Thread t2 =  Thread.ofPlatform().name("t2").unstarted(
        () -> {
          log.info("Thread 2 is waiting");
          try {
            barrier.await();
//            barrier.await();
          } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
          }
          log.info("Thread 2 is released");
        }
    );

    t1.start();
    t2.start();
  }
}
