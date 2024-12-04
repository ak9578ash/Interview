package com.interview.preparation.multi_threading.questions.fizz_buzz;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FizzBuzz {
  private final StringBuilder sb;
  private final int limit;
  private int n;

  public FizzBuzz(int limit, StringBuilder sb) {
    this.n = 1;
    this.sb = sb;
    this.limit = limit;
  }

  public synchronized void printFizz() throws InterruptedException {
    while (n <= limit) {
      if (n % 3 == 0 && n % 5 != 0) {
        log.info("Fizz");
        sb.append("Fizz ");
        n++;
        this.notifyAll();
      } else {
        this.wait();
      }
    }
  }

  public synchronized void printBuzz() throws InterruptedException {
    while (n <= limit) {
      if (n % 3 != 0 && n % 5 == 0) {
        log.info("Buzz");
        sb.append("Buzz ");
        n++;
        this.notifyAll();
      } else {
        this.wait();
      }
    }
  }

  public synchronized void printFizzBuzz() throws InterruptedException {
    while (n <= limit) {
      if (n % 3 == 0 && n % 5 == 0) {
        log.info("FizzBuzz");
        sb.append("FizzBuzz ");
        n++;
        this.notifyAll();
      } else {
        this.wait();
      }
    }
  }

  public synchronized void printNum() throws InterruptedException {
    while (n <= limit) {
      if (n % 3 != 0 && n % 5 != 0) {
        log.info(String.valueOf(n));
        sb.append(n).append(" ");
        n++;
        this.notifyAll();
      } else {
        this.wait();
      }
    }
  }
}

