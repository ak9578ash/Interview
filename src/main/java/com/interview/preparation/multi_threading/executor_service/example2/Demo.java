package com.interview.preparation.multi_threading.executor_service.example2;

import static java.util.concurrent.TimeUnit.SECONDS;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Demo {
  public static void main(String[] args) {
    ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);

    Runnable beeper = () -> {
      log.info("beep");
    };

    ScheduledFuture<?> beeperHandleFuture = scheduler.scheduleAtFixedRate(beeper, 1, 1, SECONDS);

    scheduler.schedule(
        () -> {
          beeperHandleFuture.cancel(true);
          shutdownExecutorService(scheduler);
        }, 10, SECONDS
    );
  }

  static void shutdownExecutorService(ExecutorService executorService) {
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

