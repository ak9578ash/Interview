package com.interview.preparation.multi_threading.questions.priniting_number_series;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NumberSeries {
  private int n;
  private boolean isZero;

  private StringBuilder sb;

  public NumberSeries(StringBuilder sb) {
    this.n = 1;
    this.isZero = true;
    this.sb = sb;
  }

  public synchronized void printZero() {
    while (!isZero) {
      try {
        wait();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }

    log.info("0");
    sb.append("0");
    isZero = false;
    this.notifyAll();
  }

  public synchronized void printEven() {
    while (isZero || n % 2 != 0) {
      try {
        wait();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }
    log.info("{}", n);
    sb.append(n);
    n++;
    isZero = true;
    this.notifyAll();
  }

  public synchronized void printOdd() {
    while (isZero || n % 2 == 0) {
      try {
        wait();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }
    log.info("{}", n);
    sb.append(n);
    n++;
    isZero = true;
    this.notifyAll();
  }
}
