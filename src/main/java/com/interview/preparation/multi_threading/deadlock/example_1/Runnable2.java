package com.interview.preparation.multi_threading.deadlock.example_1;

public class Runnable2 implements Runnable {
  private final Resource1 r1;
  private final Resource2 r2;

  public Runnable2(Resource1 r1, Resource2 r2) {
    this.r1 = r1;
    this.r2 = r2;
  }

  @Override
  public void run() {
    synchronized (r2) {
      System.out.println(Thread.currentThread().getName() + ": "+"locked resource 2");
      r2.print();
      synchronized (r1) {
        System.out.println("Thread 2: locked resource 1");
        r1.print();
      }
    }
  }
}