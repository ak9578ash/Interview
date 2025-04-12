package com.interview.preparation.designpatterns.strategy;

import com.interview.preparation.designpatterns.strategy.paymentstrategyimpl.CreditCardPaymentStrategy;
import com.interview.preparation.designpatterns.strategy.paymentstrategyimpl.UpiPaymentStrategy;

public class StrategyMain {
    public static void main(String[] args) {
        PaymentProcess paymentProcess = new PaymentProcess();
        paymentProcess.makePayment(new UpiPaymentStrategy());
        paymentProcess.makePayment(new CreditCardPaymentStrategy());
    }
}
