package com.interview.preparation.multi_threading.executor_service.example1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Demo {
  public static void main(String[] args) {
    ThreadFactory threadFactory = Thread.ofVirtual().name("DemoVirtualThread-", 0).factory();

    ExecutorService executorService =
        new ThreadPoolExecutor(2, 2, 0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(), threadFactory);


    Runnable runnableTask = () -> {
      try {
        Thread.sleep(10);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        return ;
      }
      log.info("Runnable is running");
    };

    Callable<String> callableTask = () -> {
      log.info("Callable is running");
      return "Task's execution";
    };

    List<Callable<String>> callableTasks = new ArrayList<>();
    callableTasks.add(callableTask);
    callableTasks.add(callableTask);
    callableTasks.add(callableTask);


    try {
      executorService.invokeAll(callableTasks);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }

    executorService.execute(runnableTask);

    shutdownExecutorService(executorService);
  }

  static void shutdownExecutorService(ExecutorService executorService) {
    log.info("Shutting down executor service");
    executorService.shutdown();
    try {
      if (!executorService.awaitTermination(1000, TimeUnit.MILLISECONDS)) {
        log.info("ShutdownNow is executed");
        executorService.shutdownNow();
      }
    } catch (InterruptedException e) {
      executorService.shutdownNow();
      Thread.currentThread().interrupt();
    }
    log.info("Executor service is shutdown");
  }
}
