package com.interview.preparation.multi_threading.executor_service.example3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Demo {
  public static void main(String[] args) {
    CustomThreadFactory customThreadFactory = new CustomThreadFactory();
    ExecutorService executorService = Executors.newThreadPerTaskExecutor(customThreadFactory);

    Runnable task1 = () -> {
      log.info("Task 1 is running");
    };

    Runnable task2 = () -> {
      log.info("Task 2 is running");
    };

    Runnable task3 = () -> {
      log.info("Task 3 is running");
    };

    Runnable task4 = () -> {
      log.info("Task 4 is running");
    };

    executorService.execute(task1);
    executorService.execute(task2);
    executorService.execute(task3);
    executorService.execute(task4);

    shutdownExecutor(executorService);
  }

  private static void shutdownExecutor(ExecutorService executorService){
    log.info("Shutting down executor service");
    executorService.shutdown();
    try {
      if (!executorService.awaitTermination(800, TimeUnit.MILLISECONDS)) {
        log.info("ShutdownNow is called");
        executorService.shutdownNow();
      }
    } catch (InterruptedException e) {
      executorService.shutdownNow();
      Thread.currentThread().interrupt();
    }
    log.info("Executor service is shutdown");
  }
}
