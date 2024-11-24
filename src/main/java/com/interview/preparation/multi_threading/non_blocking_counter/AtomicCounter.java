package com.interview.preparation.multi_threading.non_blocking_counter;

import java.util.concurrent.atomic.AtomicLong;

/**
 * This locking technique is also called Optimistic Locking
 * <a href="https://jenkov.com/tutorials/java-concurrency/non-blocking-algorithms.html">Ref: </a>
 */
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
