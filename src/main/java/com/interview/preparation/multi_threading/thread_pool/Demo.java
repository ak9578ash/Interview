package com.interview.preparation.multi_threading.thread_pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Demo {
  public static void main(String[] args) {
    ExecutorService executor = Executors.newFixedThreadPool(2);
    try {
      for (int i = 0; i < 10; i++) {
        executor.execute(
            new WorkerThread("" + i)
        );
      }
    }
    finally {
      executor.shutdown();
    }
    System.out.println("Finished all threads");
  }
}
