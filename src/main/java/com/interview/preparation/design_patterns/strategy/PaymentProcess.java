package com.interview.preparation.design_patterns.strategy;

import com.interview.preparation.design_patterns.strategy.model.UpiPaymentStrategy;

// https://www.digitalocean.com/community/tutorials/strategy-design-pattern-in-java-example-tutorial
public class PaymentProcess {
    private final PaymentStrategy paymentStrategy;

    public PaymentProcess(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    // default payment strategy
    public PaymentProcess(){
        this.paymentStrategy = new UpiPaymentStrategy();
    }

    public void makePayment(){
        System.out.println(paymentStrategy.makePayment());
    }
}
