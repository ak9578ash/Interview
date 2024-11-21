package com.interview.preparation.multi_threading.thread_pool;

public class WorkerThread implements Runnable {
  private final String message;

  public WorkerThread(String s) {
    this.message = s;
  }

  @Override
  public void run() {
    System.out.println(Thread.currentThread().getName() + " (Start) message = " + message);
    processMessage();//call processMessage method that sleeps the thread for 2 seconds
    System.out.println(Thread.currentThread().getName() + " (End)");//prints thread name
  }

  private void processMessage() {
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }
}
