package com.interview.preparation.low_level_design.parking_lot.repository;

import com.interview.preparation.low_level_design.parking_lot.model.parking.Payment;
import com.interview.preparation.low_level_design.parking_lot.model.parking.PaymentStatus;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class PaymentRepository {
    public static Map<String  , Payment>paymentMap = new HashMap<>();

    public Payment makePayment(String ticketNumber , Payment payment){
        paymentMap.put(ticketNumber,payment);
        payment.setStatus(PaymentStatus.SUCCESS);
        payment.setTransactionTime(LocalDateTime.now());
        return payment;
    }
}
