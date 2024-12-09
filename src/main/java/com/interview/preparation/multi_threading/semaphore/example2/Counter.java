package com.interview.preparation.multi_threading.semaphore.example2;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Counter {
  private Integer count = 0;

  public void incrementCounter() {
    for (int i = 0; i < 1_000_000; i++) {
      this.count++;
    }
    log.info(String.valueOf(count));
  }

  public Integer getCounter() {
    return this.count;
  }
}
