package com.interview.preparation.multi_threading.deadlock.prevention.timeout_backoff;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Demo {
  public static void main(String[] args) {
    Lock lock1 = new ReentrantLock();
    Lock lock2 = new ReentrantLock();

    Runnable1 runnable1 = new Runnable1(lock1, lock2);
    Runnable2 runnable2 = new Runnable2(lock1, lock2);

    Thread thread1 = Thread.ofPlatform().name("Thread1").unstarted(runnable1);
    Thread thread2 = Thread.ofPlatform().name("Thread2").unstarted(runnable2);

    thread1.start();
    thread2.start();

  }
}
