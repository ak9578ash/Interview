package com.interview.preparation.multi_threading.fork_join_pool.example_3;

import java.util.List;
import java.util.concurrent.ForkJoinPool;

/**
 * Implements a multithreaded approach to summing an array using the Fork/Join framework.
 */
public class Demo {
  public static void main(String[] args) {
    List<Integer> testList = List.of(
        1, 2, 3, 4, 5, 6, 7, 8, 9, 10
    );

    CustomRecursiveTaskForArraySum customRecursiveTaskForSum = new CustomRecursiveTaskForArraySum(testList);
    ForkJoinPool forkJoinPool = new ForkJoinPool(2);

    forkJoinPool.execute(customRecursiveTaskForSum);
    System.out.println("Final Outcome of ForkJoinPool: " + customRecursiveTaskForSum.join());
  }
}