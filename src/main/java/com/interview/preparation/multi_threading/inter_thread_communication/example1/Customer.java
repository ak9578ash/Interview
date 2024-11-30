package com.interview.preparation.multi_threading.inter_thread_communication.example1;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Customer {
  private int balance;

  public Customer(int balance) {
    this.balance = balance;
  }

  public synchronized void withdraw(int withdrawAmount) throws InterruptedException {
    log.info("going to withdraw...");
    while (balance < withdrawAmount) {
      log.info("Less balance; waiting for deposit...");
      this.wait();
    }
    this.balance -= withdrawAmount;
    log.info("withdraw completed...");
  }

  public synchronized void deposit(int amount) {
    log.info("going to deposit...");
    this.balance += amount;
    log.info("deposit completed... ");
    notifyAll();
  }
}