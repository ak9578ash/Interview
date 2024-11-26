package com.interview.preparation.multi_threading.fork_join_pool.recursive_task_example;

import java.util.concurrent.ForkJoinPool;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Demo {
  public static void main(String[] args) {
    ForkJoinPool commonPool = ForkJoinPool.commonPool();
    CustomRecursiveTask customRecursiveTask = new CustomRecursiveTask("akash");
    commonPool.execute(customRecursiveTask);

    System.out.println("Outcome of ForkJoinPool: " + customRecursiveTask.join());
  }
}