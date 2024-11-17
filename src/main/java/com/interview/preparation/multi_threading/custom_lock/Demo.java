package com.interview.preparation.multi_threading.custom_lock;

import java.util.concurrent.Semaphore;

public class Demo {
  public static void main(String[] args) {
    CustomLockWithReentrance lock = new CustomLockWithReentrance();
    Thread th1 = Thread.ofPlatform().unstarted(
        () -> {
          lock.lock();
          lock.lock();
          try {
            System.out.println("Thread1 acquired lock");
            // some lines of code
          } finally {
            lock.unlock();
            lock.unlock();
          }
        }
    );

    Thread th2 = Thread.ofPlatform().unstarted(
        () -> {
          lock.lock();
          System.out.println("Thread2 acquired lock");
          lock.unlock();
        }
    );

    th1.start();
    th2.start();
  }
}
