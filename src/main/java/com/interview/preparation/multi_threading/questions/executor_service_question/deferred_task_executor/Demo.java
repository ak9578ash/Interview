package com.interview.preparation.multi_threading.questions.executor_service_question.deferred_task_executor;

import com.interview.preparation.multi_threading.questions.executor_service_question.CallableTaskProducer;
import com.interview.preparation.multi_threading.questions.executor_service_question.CustomCallable;

public class Demo {
  public static void main(String[] args) {
    DeferredTaskExecutor scheduledThreadPool = new DeferredTaskExecutor();

    CustomCallable c1 = new CustomCallable(() -> "LATE_TASK", 8);
    CustomCallable c2 = new CustomCallable(() -> "EARLY_TASK", 1);

    Thread lateThread = Thread.ofPlatform()
        .name("lateThread")
        .unstarted(new CallableTaskProducer(c1, scheduledThreadPool));

    Thread earlyThread = Thread.ofPlatform()
        .name("earlyThread")
        .unstarted(new CallableTaskProducer(c2, scheduledThreadPool));


    Thread customCallableConsumerThread = Thread.ofPlatform()
        .name("customCallableConsumerThread")
        .unstarted(new CallableTaskConsumer(scheduledThreadPool));


    customCallableConsumerThread.start();

    lateThread.start();
    try {
      Thread.sleep(3000);
    }catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
    earlyThread.start();

    try {
      lateThread.join();
      earlyThread.join();
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }
}
