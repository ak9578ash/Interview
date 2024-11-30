package com.interview.preparation.multi_threading.fork_join_pool.example4;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Demo {
  public static void main(String[] args) {
    ForkJoinPool commonPool = new ForkJoinPool(5);
    List<Integer> tempArray = new ArrayList<>(List.of(11, 123, 0, -1, -2));

    CustomRecursiveTaskForArraysSorting sortArrayRecursiveTask = new CustomRecursiveTaskForArraysSorting(tempArray);
    commonPool.execute(sortArrayRecursiveTask);

    log.info("Outcome of ForkJoinPool: " + sortArrayRecursiveTask.join());
  }
}
