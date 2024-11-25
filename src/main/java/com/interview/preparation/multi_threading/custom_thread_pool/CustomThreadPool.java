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

    for (PoolThreadRunnable runnable : runnables) {
      Thread.ofPlatform().start(runnable);
    }
  }

  public synchronized void execute(Runnable task) {
    if (this.isStopped) {
      throw new IllegalStateException("ThreadPool is stopped");
    }

    this.taskQueue.offer(task);
  }

  /**
   * shutdownNow() attempts to terminate all actively executing tasks and halts the processing of waiting tasks
   */
  public synchronized void shutdownNow() {
    this.isStopped = true;
    for (PoolThreadRunnable runnable : runnables) {
      runnable.doStop();
    }
  }

  /**
   * shutdown() waits until all the submitted tasks finish executing
   */
  public synchronized void shutdown() {
    while (!this.taskQueue.isEmpty()) {
      try {
        Thread.sleep(1);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }
  }

  /**
   * awaitTermination() wait until all tasks have completed execution or execution-timeout occurred
   * @param executionDurationInMillis time to wait for the termination of tasks
   */
  public synchronized void awaitTermination(int executionDurationInMillis) {
    long startTime = System.currentTimeMillis();
    while (!this.taskQueue.isEmpty()) {
      long elapsedTime = System.currentTimeMillis() - startTime;
      if (elapsedTime >= executionDurationInMillis) {
        break;
      }
      try {
        Thread.sleep(1);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }
  }
}