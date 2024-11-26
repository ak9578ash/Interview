package com.interview.preparation.multi_threading.fork_join_pool.recursive_action_example;

import java.util.concurrent.ForkJoinPool;

public class Demo {
  public static void main(String[] args) {
    ForkJoinPool commonPool = ForkJoinPool.commonPool();
    CustomRecursiveAction customRecursiveAction = new CustomRecursiveAction("akash");

    commonPool.execute(customRecursiveAction);
    customRecursiveAction.join();

  }
}
