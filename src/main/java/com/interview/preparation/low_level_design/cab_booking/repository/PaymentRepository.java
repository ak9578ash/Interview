package com.interview.preparation.low_level_design.cab_booking.repository;

import com.interview.preparation.low_level_design.cab_booking.model.Payment;
import com.interview.preparation.low_level_design.cab_booking.model.account.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaymentRepository {
    public static Map<String, List<Payment>> paymentMap = new HashMap<>();
    public static Map<Payment, Integer> paymentFailureMap = new HashMap<>();

    public Payment addPayment(Payment payment, User user) {
        if (!paymentMap.containsKey(user.getId())) {
            paymentMap.put(user.getId(), new ArrayList<>());
        }
        paymentMap.get(user.getId()).add(payment);
        return payment;
    }

    public void addToFailurePayment(Payment payment) {
        if (!paymentFailureMap.containsKey(payment)) {
            paymentFailureMap.put(payment, 0);
        }
        Integer currentFailure = paymentFailureMap.get(payment);
        Integer finalFailure = currentFailure + 1;

        paymentFailureMap.put(payment, finalFailure);
    }

    public Integer getPaymentFailureCount(Payment payment) {
        return paymentFailureMap.get(payment);
    }

}
