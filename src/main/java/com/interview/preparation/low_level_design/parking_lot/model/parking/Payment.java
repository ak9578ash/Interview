package com.interview.preparation.low_level_design.parking_lot.model.parking;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class Payment {
    private String id;
    private String ticketId;
    private Double amount;
    private PaymentStatus status;
    private LocalDateTime transactionTime;

    public Payment(String ticketId , Double amount){
        this.id = UUID.randomUUID().toString();
        this.ticketId = ticketId;
        this.amount = amount;
        this.status = PaymentStatus.PENDING;
    }
}
