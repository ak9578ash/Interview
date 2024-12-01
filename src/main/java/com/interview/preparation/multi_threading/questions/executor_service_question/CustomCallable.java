package com.interview.preparation.multi_threading.questions.executor_service_question;

import java.util.concurrent.Callable;
import lombok.Getter;

@Getter
public class CustomCallable {
  private final Callable<String> callable;
  private final Long executedAt;

  public CustomCallable(Callable<String> callable, int scheduleAfter) {
    this.callable = callable;
    this.executedAt = System.currentTimeMillis() + scheduleAfter * 1000L;
  }
}