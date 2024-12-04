package com.interview.preparation.multi_threading.questions.foo_and_bar;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Printer {
  private boolean isFoo;

  public Printer() {
    this.isFoo = true;
  }

  public synchronized void printFoo() {
    while(!isFoo) {
      try{
        this.wait();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }

    log.info("FOO");
    this.isFoo = false;
    this.notifyAll();
  }

  public synchronized void printBar() {
    while(isFoo) {
      try{
        this.wait();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }

    log.info("BAR");
    this.isFoo = true;
    this.notifyAll();
  }
}
