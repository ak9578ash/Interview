package com.interview.preparation.multi_threading.custom_thread_pool;

import java.util.concurrent.BlockingQueue;

public class PoolThreadRunnable implements Runnable {

  private final BlockingQueue taskQueue;
  private Thread thread;
  private boolean isStopped;

  public PoolThreadRunnable(BlockingQueue queue) {
    this.taskQueue = queue;
    this.thread = null;
    this.isStopped = false;
  }

  public void run() {
    this.thread = Thread.currentThread();
    while (!isStopped()) {
      try {
        Runnable runnable = (Runnable) taskQueue.take();
        runnable.run();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        //log or otherwise report exception,
        //but keep pool thread alive.
      }
    }
  }

  public synchronized void doStop() {
    isStopped = true;
    //break pool thread out of dequeue() call.
    this.thread.interrupt();
  }

  public synchronized boolean isStopped() {
    return isStopped;
  }
}
