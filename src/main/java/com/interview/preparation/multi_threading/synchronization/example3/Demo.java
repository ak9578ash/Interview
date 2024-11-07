package com.interview.preparation.multi_threading.synchronization.example3;

public class Demo {
  public static void main(String[] args) throws InterruptedException {
    Counter counter = new Counter();

    Thread th1 = Thread.ofVirtual().start(
        () -> {
          counter.incrementCounter();
          System.out.println("Thread 1:" + counter.getCounter());
        }
    );

    Thread th2 = Thread.ofVirtual().start(
        () -> {
          counter.incrementCounter();
          System.out.println("Thread 2:" + counter.getCounter());
        }
    );

    th1.join();
    th2.join();
  }
}
