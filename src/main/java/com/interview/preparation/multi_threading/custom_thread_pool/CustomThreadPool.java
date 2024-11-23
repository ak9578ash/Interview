package com.interview.preparation.multi_threading.custom_thread_pool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class CustomThreadPool {

  private final BlockingQueue taskQueue;
  private final List<PoolThreadRunnable> runnables;
  private boolean isStopped = false;

  public CustomThreadPool(int noOfThreads, int maxNoOfTasks) {
    this.taskQueue = new ArrayBlockingQueue<>(maxNoOfTasks);
    this.runnables = new ArrayList<>();

    for (int i = 0; i < noOfThreads; i++) {
      PoolThreadRunnable poolThreadRunnable = new PoolThreadRunnable(taskQueue);
      runnables.add(poolThreadRunnable);
    }

    Thread.Builder threadBuilder = Thread.ofPlatform().name("ThreadPool-", 0);
    for (PoolThreadRunnable runnable : runnables) {
      threadBuilder.start(runnable);
    }
  }

  public synchronized void execute(Runnable task) {
    if (this.isStopped) {
      throw new IllegalStateException("ThreadPool is stopped");
    }

    this.taskQueue.offer(task);
  }

  public synchronized void stop() {
    this.isStopped = true;
    for (PoolThreadRunnable runnable : runnables) {
      runnable.doStop();
    }
  }

  public synchronized void waitUntilAllTasksFinished() {
    while (!this.taskQueue.isEmpty()) {
      try {
        Thread.sleep(1);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }
  }
}