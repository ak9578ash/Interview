package com.interview.preparation.low_level_design.cab_booking.repository;

import com.interview.preparation.low_level_design.cab_booking.model.Payment;
import com.interview.preparation.low_level_design.cab_booking.model.account.User;

import com.interview.preparation.low_level_design.cab_booking.service.PaymentService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaymentRepository {
  public static Map<String, Payment> paymentMap = new HashMap<>();
  public static Map<Payment, Integer> paymentFailureMap = new HashMap<>();

  public Payment addPayment(Payment payment) {
    paymentMap.putIfAbsent(payment.getTrip().getId(), payment);
    return payment;
  }

  public Payment getPaymentByTripId(String tripId) {
    return paymentMap.get(tripId);
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
