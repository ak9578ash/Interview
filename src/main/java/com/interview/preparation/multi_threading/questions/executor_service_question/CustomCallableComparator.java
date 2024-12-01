package com.interview.preparation.multi_threading.questions.executor_service_question;

import java.util.Comparator;

public class CustomCallableComparator implements Comparator<CustomCallable> {
  @Override
  public int compare(CustomCallable o1, CustomCallable o2) {
    if (o1.getExecutedAt() > o2.getExecutedAt()) {
      return 1;
    } else if (o1.getExecutedAt() < o2.getExecutedAt()) {
      return -1;
    } else {
      return 0;
    }
  }
}