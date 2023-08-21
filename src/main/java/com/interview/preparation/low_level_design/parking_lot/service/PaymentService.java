package com.interview.preparation.low_level_design.parking_lot.service;

import com.interview.preparation.low_level_design.parking_lot.model.parking.Payment;
import com.interview.preparation.low_level_design.parking_lot.repository.PaymentRepository;

public class PaymentService {
    private final PaymentRepository paymentRepository ;

    public PaymentService(PaymentRepository paymentRepository){
        this.paymentRepository = paymentRepository;
    }
    public Payment makePayment(String ticketNumber , Payment payment){
        return paymentRepository.makePayment(ticketNumber,payment);
    }
}
