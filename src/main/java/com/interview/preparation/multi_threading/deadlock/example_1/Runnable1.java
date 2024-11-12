package com.interview.preparation.multi_threading.deadlock.example_1;

public class Runnable1 implements Runnable {
  private final Resource1 r1;
  private final Resource2 r2;

  public Runnable1(Resource1 r1, Resource2 r2) {
    this.r1 = r1;
    this.r2 = r2;
  }

  @Override
  public void run() {
    synchronized (r1) {
      System.out.println(Thread.currentThread().getName() + ": "+"locked resource 1");
      r1.print();
       try {
          Thread.sleep(100);
       } catch (InterruptedException e) {
          throw new RuntimeException(e);
       }
       synchronized (r2) {
        System.out.println("Thread 1: locked resource 2");
        r2.print();
      }
    }
  }
}