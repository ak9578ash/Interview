package com.interview.preparation.multi_threading.custom_thread_pool;

public class Demo {
  public static void main(String[] args) throws Exception {

    CustomThreadPool threadPool = new CustomThreadPool(3, 10);

    for (int i = 0; i < 10; i++) {
      int taskNo = i;
      threadPool.execute(
          () -> {
            String message = Thread.currentThread().getName() + ": Task " + taskNo;
            System.out.println(message);
          }
      );
    }

    threadPool.waitUntilAllTasksFinished();
    threadPool.stop();
  }
}