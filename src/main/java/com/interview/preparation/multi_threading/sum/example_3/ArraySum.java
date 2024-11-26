package com.interview.preparation.multi_threading.sum.example_3;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ArraySum {
  public synchronized void sum(List<Integer> array, AtomicInteger count) {
    for (int i = 0; i < array.size(); i++) {
      count.addAndGet(array.get(i));
    }
  }
}
