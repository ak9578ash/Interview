package com.interview.preparation.designpatterns.strategy.paymentstrategyimpl;

import com.interview.preparation.designpatterns.strategy.PaymentStrategy;

public class NetBankingPaymentStrategy implements PaymentStrategy {
    @Override
    public String makePayment() {
        return "payment via net banking";
    }
}
