package com.interview.preparation.multi_threading.questions.executor_service_question.custom_schedule_thread_pool;

import com.interview.preparation.multi_threading.questions.executor_service_question.CustomCallable;
import com.interview.preparation.multi_threading.questions.executor_service_question.CustomCallableComparator;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class CustomScheduleThreadPool {
  private final BlockingQueue<CustomCallable> taskQueue;
  private final List<PoolThreadCallable> callables;
  private boolean isStopped;

  public CustomScheduleThreadPool(int noOfThreads, int maxNoOfTasks) {
    this.taskQueue = new PriorityBlockingQueue<>(maxNoOfTasks, new CustomCallableComparator());
    this.callables = new ArrayList<>();
    this.isStopped = false;

    for (int i = 0; i < noOfThreads; i++) {
      PoolThreadCallable poolThreadCallable = new PoolThreadCallable(taskQueue);
      callables.add(poolThreadCallable);
    }

    for(PoolThreadCallable callable: callables) {
      Thread.ofPlatform().start(callable);
    }
  }

  public synchronized void execute(CustomCallable task) {
    if (this.isStopped) {
      throw new IllegalStateException("ThreadPool is stopped");
    }
    this.taskQueue.offer(task);
    synchronized (this.taskQueue) {
      this.taskQueue.notifyAll();
    }
  }

  /**
   * shutdownNow() attempts to terminate all actively executing tasks and halts the processing of waiting tasks
   */
  public synchronized void shutdownNow() {
    this.isStopped = true;
    for (PoolThreadCallable callable : callables) {
      callable.doStop();
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
