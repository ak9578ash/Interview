package com.interview.preparation.multi_threading.sum.example_3;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Demo {
  public static void main(String[] args) {
    ArraySum arraySum = new ArraySum();
    List<Integer> l1 = List.of(1,2,3);
    List<Integer>l2 = List.of(4,5,6);
    List<Integer>l3 = List.of(7,8,9);

    AtomicInteger count = new AtomicInteger(0);
    Thread th1 = Thread.ofPlatform().start(
        () -> arraySum.sum(l1, count)
    );

    Thread th2 = Thread.ofPlatform().start(
        () -> arraySum.sum(l2, count)
    );

    Thread th3 = Thread.ofPlatform().start(
        () -> arraySum.sum(l3, count)
    );

    try {
      th1.join();
      th2.join();
      th3.join();
    }catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }

    System.out.println("Final Sum: " + count);
  }
}
