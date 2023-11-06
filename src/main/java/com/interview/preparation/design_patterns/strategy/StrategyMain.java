package com.interview.preparation.design_patterns.strategy;

import com.interview.preparation.design_patterns.strategy.model.CreditCardPaymentStrategy;

public class StrategyMain {
    public static void main(String[] args) {
        PaymentProcess paymentProcess = new PaymentProcess(new CreditCardPaymentStrategy());
        paymentProcess.makePayment();
    }
}
