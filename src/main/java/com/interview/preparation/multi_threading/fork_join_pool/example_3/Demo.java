package com.interview.preparation.multi_threading.fork_join_pool.example_3;

import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class Demo {
  public static void main(String[] args) {
    List<Integer> testList = List.of(
        1, 2, 3, 4, 5, 6, 7, 8, 9, 10
    );

    CustomRecursiveTaskForSum customRecursiveTaskForSum = new CustomRecursiveTaskForSum(testList);
    ForkJoinPool forkJoinPool = new ForkJoinPool(3);

    forkJoinPool.execute(customRecursiveTaskForSum);
    System.out.println("Final Outcome of ForkJoinPool: " + customRecursiveTaskForSum.join());
  }
}
