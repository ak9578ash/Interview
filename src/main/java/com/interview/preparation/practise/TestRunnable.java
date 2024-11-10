package com.interview.preparation.practise;

public class TestRunnable implements Runnable {
  private ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>();

  @Override
  public synchronized void run() {
    threadLocal.set((int) (Math.random() * 100D));
    System.out.println(threadLocal.get());
  }
}
