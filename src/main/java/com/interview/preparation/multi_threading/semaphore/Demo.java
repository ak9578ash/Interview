package com.interview.preparation.multi_threading.semaphore;


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
                counter.incrementCounter();
                semaphore.release();
              } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
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
                semaphore.release();
              } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
              }
            }
        );

    try {
      th1.join();
      th2.join();
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }

    System.out.println("Final Counter Value: " + counter.getCounter());
  }
}
