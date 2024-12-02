package com.interview.preparation.multi_threading.semaphore.example1;

public class Demo {
  public static void main(String[] args) {
    CustomBlockingQueueWithBoundedSemaphore
        customBlockingQueueWithSemaphore = new CustomBlockingQueueWithBoundedSemaphore();

    Thread producerThread1 = Thread.ofPlatform()
        .name("producerThread1")
        .unstarted(
            () -> {
              try {
                customBlockingQueueWithSemaphore.enqueue(1);
              } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
              }
            }
        );

    Thread producerThread2 = Thread.ofPlatform()
        .name("producerThread2")
        .unstarted(
            () -> {
              try {
                customBlockingQueueWithSemaphore.enqueue(2);
              } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
              }
            }
        );

    Thread producerThread3 = Thread.ofPlatform()
        .name("producerThread3")
        .unstarted(
            () -> {
              try {
                customBlockingQueueWithSemaphore.enqueue(3);
              } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
              }
            }
        );


    Thread consumerThread1 = Thread.ofPlatform()
        .name("consumerThread1")
        .unstarted(
            () -> {
              try {
                customBlockingQueueWithSemaphore.dequeue();
              } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
              }
            }
        );

    Thread consumerThread2 = Thread.ofPlatform()
        .name("consumerThread2")
        .unstarted(
            () -> {
              try {
                customBlockingQueueWithSemaphore.dequeue();
              } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
              }
            }
        );


    producerThread1.start();
    producerThread2.start();
    producerThread3.start();

    try{
      Thread.sleep(5000);
    }catch (InterruptedException e) {
        Thread.currentThread().interrupt();
    }

    consumerThread1.start();
    consumerThread2.start();

    try{
      producerThread1.join();
      producerThread2.join();
      producerThread3.join();
      consumerThread1.join();
      consumerThread2.join();
    }catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }
}
