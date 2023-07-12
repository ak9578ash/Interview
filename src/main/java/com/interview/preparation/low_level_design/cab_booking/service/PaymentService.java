package com.interview.preparation.low_level_design.cab_booking.service;

import com.interview.preparation.low_level_design.cab_booking.exception.BadRequestException;
import com.interview.preparation.low_level_design.cab_booking.model.account.User;
import com.interview.preparation.low_level_design.cab_booking.utils.CabLockProvider;
import com.interview.preparation.low_level_design.cab_booking.model.Payment;
import com.interview.preparation.low_level_design.cab_booking.repository.PaymentRepository;

import java.util.ArrayList;
import java.util.Collection;

public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final Integer allowedRetry ;
    private final CabLockProvider cabLockProvider;
    public PaymentService(PaymentRepository paymentRepository, Integer allowedRetry, CabLockProvider cabLockProvider) {
        this.paymentRepository = paymentRepository;
        this.allowedRetry = allowedRetry;
        this.cabLockProvider = cabLockProvider;
    }

    public Payment makePayment(Payment payment , User user){
        return paymentRepository.addPayment(payment , user);
    }

    public void addToPaymentFailure(Payment payment){
        paymentRepository.addToFailurePayment(payment);
    }

    public void processFailedPayment(Payment payment , User user) throws BadRequestException {
        if(!payment.getTrip().getUser().equals(user)){
            throw new BadRequestException();
        }
        Integer failureCount = paymentRepository.getPaymentFailureCount(payment);
        if(failureCount > allowedRetry){
            cabLockProvider.unlockCabs(payment.getTrip().getCab(), user.getId());
        }
    }
}
