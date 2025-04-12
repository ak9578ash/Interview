package com.interview.preparation.designpatterns.strategy;

import com.interview.preparation.designpatterns.strategy.paymentstrategyimpl.UpiPaymentStrategy;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// https://www.digitalocean.com/community/tutorials/strategy-design-pattern-in-java-example-tutorial
@Slf4j
@NoArgsConstructor
public class PaymentProcess {
  public void makePayment(PaymentStrategy paymentStrategy) {
    log.info(paymentStrategy.makePayment());
  }
}
