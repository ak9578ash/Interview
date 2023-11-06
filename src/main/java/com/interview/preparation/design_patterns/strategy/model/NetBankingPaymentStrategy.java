package com.interview.preparation.design_patterns.strategy.model;

import com.interview.preparation.design_patterns.strategy.PaymentStrategy;

public class NetBankingPaymentStrategy implements PaymentStrategy {
    @Override
    public String makePayment() {
        return "payment vai net banking";
    }
}
