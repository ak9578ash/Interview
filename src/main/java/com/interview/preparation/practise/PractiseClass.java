package com.interview.preparation.practise;

public class PractiseClass {
  public static void main(String[] args) {
    TestRunnable testRunnable = new TestRunnable();

    Thread th1 = Thread.ofVirtual().name("Thread1").start(testRunnable);

    Thread th2 = Thread.ofVirtual().name("Thread2").start(testRunnable);

    try {
      th1.wait();
      th1.join();
      th2.join();
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }

  }
}

