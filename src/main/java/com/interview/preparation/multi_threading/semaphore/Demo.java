package com.interview.preparation.multi_threading.semaphore;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Demo {
  public static void main(String[] args) {
    Counter counter = new Counter();
    BoundedSemaphore semaphore = new BoundedSemaphore(1);

    Thread th1 = Thread
        .ofPlatform()
        .name("Thread1")
        .start(
            () -> {
              try {
                semaphore.take();
                Thread.sleep(2000);
                counter.incrementCounter();
              } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
              } finally {
                try {
                  semaphore.release();
                } catch (InterruptedException e) {
                  Thread.currentThread().interrupt();
                }
              }
            }
        );

    Thread th2 = Thread
        .ofPlatform()
        .name("Thread2")
        .start(
            () -> {
              try {
                semaphore.take();
                counter.incrementCounter();
                Thread.sleep(2000);
              } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
              } finally {
                try {
                  semaphore.release();
                } catch (InterruptedException e) {
                  Thread.currentThread().interrupt();
                }
              }
            }
        );

    Thread th3 = Thread
        .ofPlatform()
        .name("Thread3")
        .start(
            () -> {
              try {
                semaphore.take();
                counter.incrementCounter();
              } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
              } finally {
                try {
                  semaphore.release();
                } catch (InterruptedException e) {
                  Thread.currentThread().interrupt();
                }
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
