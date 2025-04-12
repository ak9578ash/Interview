package com.interview.preparation.designpatterns.strategy;

import com.interview.preparation.designpatterns.strategy.paymentstrategyimpl.CreditCardPaymentStrategy;

public class StrategyMain {
    public static void main(String[] args) {
        PaymentProcess paymentProcess = new PaymentProcess();
        paymentProcess.makePayment();
    }
}
