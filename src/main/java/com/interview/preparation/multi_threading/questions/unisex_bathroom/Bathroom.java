package com.interview.preparation.multi_threading.questions.unisex_bathroom;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Bathroom {
  private final int bound;
  private int male;
  private int female;

  public Bathroom(int bound) {
    this.male = 0;
    this.female = 0;
    this.bound = bound;
  }

  public synchronized void maleEnter() throws InterruptedException {
    while (female > 0 || male == bound) {
      log.info("Male Thread Waiting: Male has reached the limit or Female is in bathroom");
      this.wait();
    }

    male++;
    this.notifyAll();
    log.info("Male Entered. No of employees in bathroom: {}", male);
  }

  public synchronized void maleExit() throws InterruptedException {
    if (male == 0) {
      throw new IllegalStateException("No male in bathroom");
    }
    male--;
    this.notifyAll();
    log.info("Male Exited");
  }

  public synchronized void femaleEnter() throws InterruptedException {
    while (male > 0 || female == bound) {
      log.info("Female Thread Waiting: Female has reached the limit or Male is in bathroom");
      this.wait();
    }

    female++;
    this.notifyAll();
    log.info("Female Entered. No of employees in bathroom: {}", female);
  }

  public synchronized void femaleExit() throws InterruptedException {
    if (female == 0) {
      throw new IllegalStateException("No female in bathroom");
    }
    female--;
    this.notifyAll();
    log.info("Female Exited");
  }
}
