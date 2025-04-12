package com.interview.preparation.designpatterns.strategy;

import com.interview.preparation.designpatterns.strategy.paymentstrategyimpl.UpiPaymentStrategy;
import lombok.extern.slf4j.Slf4j;

// https://www.digitalocean.com/community/tutorials/strategy-design-pattern-in-java-example-tutorial
@Slf4j
public class PaymentProcess {
  private final PaymentStrategy paymentStrategy;

  public PaymentProcess(PaymentStrategy paymentStrategy) {
    this.paymentStrategy = paymentStrategy;
  }

  // default payment strategy
  public PaymentProcess() {
    this.paymentStrategy = new UpiPaymentStrategy();
  }

  public void makePayment() {
    log.info(paymentStrategy.makePayment());
  }
}
