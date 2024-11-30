package com.interview.preparation.multi_threading.questions.foo_and_bar;

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
    String threadName = Thread.currentThread().getName();
    System.out.println(threadName + ": " + "FOO");
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
    String threadName = Thread.currentThread().getName();
    System.out.println(threadName + ": " + "BAR");
    this.isFoo = true;
    this.notifyAll();
  }
}
