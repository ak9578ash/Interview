package com.interview.preparation.multi_threading.mutex;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Demo {
  public static void main(String[] args) {
    Counter counter = new Counter();
    CustomMutex customMutex = new CustomMutex();

    Thread th1 = Thread
        .ofPlatform()
        .name("Thread1")
        .start(
            () -> {
              try {
                customMutex.take();
                counter.incrementCounter();
              } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
              } finally {
                customMutex.release();
              }
            }
        );

    Thread th2 = Thread
        .ofPlatform()
        .name("Thread2")
        .start(
            () -> {
              try {
                customMutex.take();
                counter.incrementCounter();
                Thread.sleep(2000);
              } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
              } finally {
                customMutex.release();
              }
            }
        );

    Thread th3 = Thread
        .ofPlatform()
        .name("Thread3")
        .start(
            () -> {
              try {
                customMutex.take();
                counter.incrementCounter();
              } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
              } finally {
                customMutex.release();
              }
            }
        );

    try {
      th1.join();
      th2.join();
      th3.join();
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }

    log.info("Final Counter Value: " + counter.getCounter());
  }
}
