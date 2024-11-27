package com.interview.preparation.multi_threading.executor_service.example1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Demo {
  public static void main(String[] args) {
    ExecutorService executorService =
        new ThreadPoolExecutor(2, 2, 0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>());


    Runnable runnableTask = () -> {
      log.info("Runnable is running");
    };

    Callable<String> callableTask = () -> {
      log.info(Thread.currentThread().getName());
      TimeUnit.MILLISECONDS.sleep(300);
      return "Task's execution";
    };

    List<Callable<String>> callableTasks = new ArrayList<>();
    callableTasks.add(callableTask);
    callableTasks.add(callableTask);
    callableTasks.add(callableTask);


    executorService.execute(runnableTask);

    try {
      List<Future<String>> callableTasksFuture = executorService.invokeAll(callableTasks);
      for (Future<String> future : callableTasksFuture) {
        log.info(future.get());
      }
    } catch (ExecutionException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }

    shutdownExecutorService(executorService);
  }

  static void shutdownExecutorService(ExecutorService executorService) {
    log.info("Shutting down executor service");
    executorService.shutdown();
    try {
      if (!executorService.awaitTermination(800, TimeUnit.MILLISECONDS)) {
        executorService.shutdownNow();
      }
    } catch (InterruptedException e) {
      executorService.shutdownNow();
      Thread.currentThread().interrupt();
    }
    log.info("Executor service is shutdown");
  }
}
