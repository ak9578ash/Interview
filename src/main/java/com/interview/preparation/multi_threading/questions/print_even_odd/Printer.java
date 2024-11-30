package com.interview.preparation.multi_threading.questions.print_even_odd;

public class Printer {
  private int num = 1;

  public synchronized void printEven(int limit) {
    while (num <= limit) {
      while (num % 2 != 0) { // for spurious wakeup
        try {
          this.wait();
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
        }
      }
      String threadName = Thread.currentThread().getName();
      System.out.println(threadName + ": " + num);
      num++;
      this.notifyAll();
    }
  }

  public synchronized void printOdd(int limit) {
    while (num <= limit) {
      while (num % 2 == 0) { // for spurious wakeup
        try {
          this.wait();
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
        }
      }
      String threadName = Thread.currentThread().getName();
      System.out.println(threadName + ": " + num);
      num++;
      this.notifyAll();
    }
  }
}
