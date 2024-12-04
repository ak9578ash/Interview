package com.interview.preparation.multi_threading.questions.priniting_number_series;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Demo {
  public static void main(String[] args) {
    StringBuilder sb = new StringBuilder();
    NumberSeries numberSeries = new NumberSeries(sb);

    Thread t1 = Thread.ofPlatform().name("ZeroThread")
        .unstarted(new NumberSeriesHelper(numberSeries, 6, "printZero"));

    Thread t2 = Thread.ofPlatform().name("EvenThread")
        .unstarted(new NumberSeriesHelper(numberSeries, 6, "printEven"));

    Thread t3 = Thread.ofPlatform().name("OddThread")
        .unstarted(new NumberSeriesHelper(numberSeries, 6, "printOdd"));

    t1.start();
    t2.start();
    t3.start();

    try {
      t1.join();
      t2.join();
      t3.join();
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }

    log.info("Final Series: {}", sb);
  }
}
