package com.interview.preparation.multi_threading.questions.executor_service_question.custom_schedule_thread_pool;

import com.interview.preparation.multi_threading.questions.executor_service_question.CustomCallable;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class Demo {
  public static void main(String[] args) {
    CustomScheduleThreadPool customScheduleThreadPool = new CustomScheduleThreadPool(4, 10);

    CustomCallable c1 = new CustomCallable(() -> "LATE_TASK", 8);
    CustomCallable c2 = new CustomCallable(() -> "EARLY_TASK", 1);
    CustomCallable c3 = new CustomCallable(() -> "INTERMEDIATE_TASK", 3);
    CustomCallable c4 = new CustomCallable(() -> "RANDOM_TASK", 2);

    Thread lateThread = Thread.ofPlatform()
        .name("lateThread")
        .unstarted(
            () -> {
              customScheduleThreadPool.execute(c1);
            }
        );

    Thread earlyThread = Thread.ofPlatform()
        .name("earlyThread")
        .unstarted(
            () -> {
              customScheduleThreadPool.execute(c2);
            }
        );

    Thread intermediateThread = Thread.ofPlatform()
        .name("intermediateThread")
        .unstarted(
            () -> {
              customScheduleThreadPool.execute(c3);
            }
        );

    Thread randomThread = Thread.ofPlatform()
        .name("randomThread")
        .unstarted(
            () -> {
              customScheduleThreadPool.execute(c4);
            }
        );

    lateThread.start();
//    try {
//      Thread.sleep(3000);
//    } catch (InterruptedException e) {
//      Thread.currentThread().interrupt();
//    }
    earlyThread.start();
    intermediateThread.start();
    randomThread.start();

    try {
      lateThread.join();
      earlyThread.join();
      intermediateThread.join();
      randomThread.join();
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }

    customScheduleThreadPool.awaitTermination(2000);
    customScheduleThreadPool.shutdownNow();
  }
}
