package com.interview.preparation.multi_threading.questions.executor_service_question.deferred_task_executor;

import com.interview.preparation.multi_threading.questions.executor_service_question.CustomCallable;
import com.interview.preparation.multi_threading.questions.executor_service_question.CustomCallableComparator;
import java.util.PriorityQueue;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DeferredTaskExecutor {
  private final PriorityQueue<CustomCallable> taskQueue;

  public DeferredTaskExecutor() {
    this.taskQueue = new PriorityQueue<>(new CustomCallableComparator());
  }

  public synchronized void execute() throws Exception {
    while (true) {
      while (taskQueue.isEmpty()) {
        log.info("Queue is empty. Waiting for task to be added");
        this.wait();
      }

      while (!taskQueue.isEmpty()) {
        long sleepFor = findSleepDuration();

        if (sleepFor <= 0) {
          break;
        }
        this.wait(sleepFor);
      }

      if (!taskQueue.isEmpty()) {
        CustomCallable task = taskQueue.poll();
        try {
          log.info(
              " ExecutedAt: " + (System.currentTimeMillis() / 1000) +
              " ScheduledAt: " + (task.getExecutedAt() / 1000) +
              " Task: " + task.getCallable().call()
          );
        } catch (Exception e) {
          log.error("Error executing callable task");
        }
      }
    }
  }

  public synchronized void registerCallable(CustomCallable task) {
    taskQueue.offer(task);
    this.notifyAll();
  }

  private long findSleepDuration() {
    if (taskQueue.isEmpty()) {
      return Long.MAX_VALUE;
    }
    long currentTime = System.currentTimeMillis();
    return taskQueue.peek().getExecutedAt() - currentTime;
  }
}
