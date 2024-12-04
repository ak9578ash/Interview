package com.interview.preparation.multi_threading.questions.barrier;

public class Barrier {
  private final int totalThreads;
  private int count;

  private int released;

  public Barrier(int totalThreads) {
    this.totalThreads = totalThreads;
    this.count = 0;
    this.released = 0;
  }

  public synchronized void await() throws InterruptedException {
    while (count == totalThreads) {
      this.wait();
    }

    count++;

    if (count == totalThreads) {
      this.notifyAll();
      released = totalThreads;
    } else {
      while (count < totalThreads) {
        this.wait();
      }
    }

    released--;
    if (released == 0) {
      count = 0;
      this.notifyAll();
    }
  }
}
