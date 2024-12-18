package com.interview.preparation.multi_threading.count_down_latch.example1;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Demo {
  public static void main(String[] args) {
    CountDownLatch countDownLatch = new CountDownLatch(3);

    Worker worker1 = new Worker(countDownLatch);
    Worker worker2 = new Worker(countDownLatch);
    Worker worker3 = new Worker(countDownLatch);

    Thread th1 = Thread.ofPlatform().name("WorkerThread1").unstarted(worker1);
    Thread th2 = Thread.ofPlatform().name("WorkerThread2").unstarted(worker2);
    Thread th3 = Thread.ofPlatform().name("WorkerThread3").unstarted(worker3);

//    List<Thread> threadList = List.of(th1, th2, th3);
//    for(int i=0;i<3;i++){
//      threadList.get(i).start();
//    }

    th1.start();
    th2.start();
    th3.start();

    try {
      log.info("Main thread is waiting");
      countDownLatch.await();
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
    log.info("All the workers have completed their work");
  }
}
