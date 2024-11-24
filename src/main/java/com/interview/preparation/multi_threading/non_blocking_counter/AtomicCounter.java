package com.interview.preparation.multi_threading.non_blocking_counter;

import java.util.concurrent.atomic.AtomicLong;

public class AtomicCounter {
  private final AtomicLong count ;

  public AtomicCounter() {
    this.count = new AtomicLong(0);
  }

  public void inc() {
    boolean updated = false;
    while(!updated){
      long prevCount = this.count.get();
      updated = this.count.compareAndSet(prevCount, prevCount + 1);
    }
  }

  public long count() {
    return this.count.get();
  }
}
